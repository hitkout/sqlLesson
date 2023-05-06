package ru.osminkin.sqlesson.repository.basicDb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import ru.osminkin.sqlesson.model.basicDb.Tasks;

import java.util.List;
import java.util.Optional;

public interface TasksRepository extends JpaRepository<Tasks, Long> {
    @Override
    @NonNull
    Optional<Tasks> findById(@NonNull Long id);

    @Override
    @NonNull
    List<Tasks> findAll();
}
