package ru.osminkin.sqlesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import ru.osminkin.sqlesson.model.Points;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface PointsRepository extends JpaRepository<Points, Long> {
    @Override
    @NonNull
    @Query(value = "select * from points order by point desc", nativeQuery = true)
    List<Points> findAll();

    @Transactional
    @Modifying
    @Query(value = "insert into points(user_id, point) values(:userId, :point)", nativeQuery = true)
    void savePoints(@Param("userId") Long userId, @Param("point") Long point);

    @Transactional
    @Modifying
    @Query(value = "update points set point = point + :point where user_id = :userId", nativeQuery = true)
    void updatePoints(@Param("userId") Long userId, @Param("point") Long point);

    @Query(value = "select * from points where user_id = :userId", nativeQuery = true)
    Optional<Points> isUserHasPoints(@Param("userId") Long userId);
}
