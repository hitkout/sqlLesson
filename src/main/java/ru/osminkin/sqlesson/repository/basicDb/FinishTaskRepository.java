package ru.osminkin.sqlesson.repository.basicDb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import ru.osminkin.sqlesson.model.basicDb.FinishTask;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface FinishTaskRepository extends JpaRepository<FinishTask, Long> {
    @Override
    @NonNull
    List<FinishTask> findAll();

    @Query(value = "select * from finish_task where user_id = :userId and task_id = :taskId", nativeQuery = true)
    Optional<FinishTask> isFinishTask(@Param("userId") Long userId, @Param("taskId") Long taskId);

    @Transactional
    @Modifying
    @Query(value = "insert into finish_task(user_id, task_id) values(:userId, :taskId)", nativeQuery = true)
    void saveTask(@Param("userId") Long userId, @Param("taskId") Long taskId);

    @Query(value = "select * from finish_task where user_id = :userId", nativeQuery = true)
    List<FinishTask> findByUserId(@Param("userId") Long userId);
}
