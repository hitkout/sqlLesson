package ru.osminkin.sqlesson.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.osminkin.sqlesson.model.Lessons;

import java.util.Optional;

public interface LessonsRepository extends JpaRepository<Lessons, Long> {

    @Override
    @NonNull
    Optional<Lessons> findById(@NonNull Long id);

}
