package ru.osminkin.sqlesson.services;

import org.springframework.stereotype.Service;
import ru.osminkin.sqlesson.model.FinishTask;
import ru.osminkin.sqlesson.model.Tasks;
import ru.osminkin.sqlesson.model.User;
import ru.osminkin.sqlesson.repository.FinishTaskRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FinishTaskService {
    private final FinishTaskRepository finishTaskRepository;

    public FinishTaskService(FinishTaskRepository finishTaskRepository) {
        this.finishTaskRepository = finishTaskRepository;
    }

    public boolean isFinishTask(Long userId, Long taskId){
        return finishTaskRepository.isFinishTask(userId, taskId).isPresent();
    }

    public void saveTask(Long userId, Long taskId){
        finishTaskRepository.saveTask(userId, taskId);
    }

    public List<FinishTask> findAll(){
        return finishTaskRepository.findAll();
    }

    public List<Long> findCompletedTasksByUserId(User user, List<Tasks> tasks){
        List<Long> completedTasks = new ArrayList<>();
        if (user != null) {
            List<FinishTask> finishTasksList = finishTaskRepository.findByUserId(user.getId());
            finishTasksList.forEach(value -> tasks.stream().map(Tasks::getId).filter(id -> value.getTask().getId().equals(id)).forEach(completedTasks::add));
        }
        return completedTasks;
    }
}
