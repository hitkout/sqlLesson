package ru.osminkin.sqlesson.services;

import org.springframework.stereotype.Service;
import ru.osminkin.sqlesson.model.Lessons;
import ru.osminkin.sqlesson.repository.LessonsRepository;

import java.util.List;

@Service
public class LessonsService {
    private final LessonsRepository lessonsRepository;

    public LessonsService(LessonsRepository lessonsRepository) {
        this.lessonsRepository = lessonsRepository;
    }

    public List<Lessons> findAll(){
        return lessonsRepository.findAll();
    }
}
