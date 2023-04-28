package ru.osminkin.sqlesson.repository.testDb;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.osminkin.sqlesson.model.testDb.Manufacturer;

import java.util.Optional;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    @Override
    @NonNull
    Optional<Manufacturer> findById(@NonNull Long id);

}
