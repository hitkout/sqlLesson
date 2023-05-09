package ru.osminkin.sqlesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import ru.osminkin.sqlesson.model.Tasks;

import java.util.List;
import java.util.Optional;

public interface TasksRepository extends JpaRepository<Tasks, Long> {
    @Override
    @NonNull
    Optional<Tasks> findById(@NonNull Long id);

    @Override
    @NonNull
    List<Tasks> findAll();

    @Query(value = "select * from tasks where id = (:taskId-1)", nativeQuery = true)
    Optional<Tasks> previousPageExists(@Param("taskId") Long taskId);

    @Query(value = "select * from tasks where id = (:taskId+1)", nativeQuery = true)
    Optional<Tasks> nextPageExists(@Param("taskId") Long taskId);
}
