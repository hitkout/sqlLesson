package ru.osminkin.sqlesson.services;

import org.springframework.stereotype.Service;
import ru.osminkin.sqlesson.model.FinishLesson;
import ru.osminkin.sqlesson.model.FinishTask;
import ru.osminkin.sqlesson.model.User;
import ru.osminkin.sqlesson.repository.FinishLessonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinishLessonService {
    private final FinishLessonRepository finishLessonRepository;

    public FinishLessonService(FinishLessonRepository finishLessonRepository) {
        this.finishLessonRepository = finishLessonRepository;
    }

    public boolean isCompletedLesson(Long userId, Long lessonId){
        return finishLessonRepository.isCompletedLesson(userId, lessonId).isPresent();
    }

    public void saveLesson(Long userId, Long lessonId){
        finishLessonRepository.saveLesson(userId, lessonId);
    }

    public List<Long> listOfCompletedLessons(User user){
        List<Long> idOfCompletedLessons = new ArrayList<>();
        if (user != null) {
            List<FinishLesson> finishLessonsList = finishLessonRepository.listOfCompletedLessons(user.getId());
            idOfCompletedLessons = finishLessonsList.stream().map(finishLesson -> finishLesson.getLesson().getId()).collect(Collectors.toList());
        }
        return idOfCompletedLessons;
    }
}
