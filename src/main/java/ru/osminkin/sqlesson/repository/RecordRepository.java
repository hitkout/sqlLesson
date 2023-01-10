package ru.osminkin.sqlesson.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.osminkin.sqlesson.model.Record;
import ru.osminkin.sqlesson.services.QueryService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record, Long> {
    @NonNull Optional<Record> findById(@NonNull Long id);

    @Query(value = "select * from comments c inner join users u on c.user_id = u.id where u.status = 'ACTIVE' and c.video_id = :videoId order by c.add_date", nativeQuery = true)
    Iterable<Record> findAllCommentsByNewVideoId(@Param("videoId") Long videoId);

    @Query(value = "select * from comments c inner join users u on c.user_id = u.id where u.status = 'ACTIVE' and c.video_id = :videoId order by c.add_date desc", nativeQuery = true)
    Iterable<Record> findAllCommentsByOldVideoId(@Param("videoId") Long videoId);

    @Transactional
    @Modifying
    @Query(value = "delete from comments where video_id = :videoId", nativeQuery = true)
    void deleteByVideoId(@Param("videoId") Long videoId);
}
