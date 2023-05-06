package ru.osminkin.sqlesson.repository.testDb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import ru.osminkin.sqlesson.model.testDb.Post;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Override
    @NonNull
    Optional<Post> findById(@NonNull Long id);
}
