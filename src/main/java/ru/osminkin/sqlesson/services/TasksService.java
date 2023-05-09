package ru.osminkin.sqlesson.services;

import org.springframework.stereotype.Service;
import ru.osminkin.sqlesson.model.Tasks;
import ru.osminkin.sqlesson.repository.TasksRepository;

import java.util.List;

@Service
public class TasksService {
    private final TasksRepository tasksRepository;

    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public List<Tasks> getAllTasks() {
        return tasksRepository.findAll();
    }

    public Tasks getById(Long taskId){
        return tasksRepository.getById(taskId);
    }

    public boolean previousPageExists(Long taskId){
        return tasksRepository.previousPageExists(taskId).isPresent();
    }

    public boolean nextPageExists(Long taskId){
        return tasksRepository.nextPageExists(taskId).isPresent();
    }
}
