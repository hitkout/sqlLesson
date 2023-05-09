package ru.osminkin.sqlesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.osminkin.sqlesson.model.FinishLesson;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface FinishLessonRepository extends JpaRepository<FinishLesson, Long> {

    @Query(value = "select * from finish_lesson where user_id = :userId and lesson_id = :lessonId", nativeQuery = true)
    Optional<FinishLesson> isCompletedLesson(@Param("userId") Long userId, @Param("lessonId") Long lessonId);

    @Transactional
    @Modifying
    @Query(value = "insert into finish_lesson(user_id, lesson_id) values(:userId, :lessonId)", nativeQuery = true)
    void saveLesson(@Param("userId") Long userId, @Param("lessonId") Long lessonId);

    @Query(value = "select * from finish_lesson where user_id = :userId", nativeQuery = true)
    List<FinishLesson> listOfCompletedLessons(@Param("userId") Long userId);
}
