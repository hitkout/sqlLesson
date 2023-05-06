package ru.osminkin.sqlesson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.osminkin.sqlesson.model.basicDb.User;
import ru.osminkin.sqlesson.services.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tasks")
public class AllTasksController {
    private static final Long POINT = 1L;
    private final UserService userService;
    private final QueryService queryService;
    private final FinishTaskService finishTaskService;
    private final TasksService tasksService;
    private final PointsService pointsService;
    private List<Map<String, Object>> resultQuery;
    private String resultFromDoc;
    private boolean isCorrectResult;

    public AllTasksController(UserService userService, QueryService queryService, FinishTaskService finishTaskService, TasksService tasksService, PointsService pointsService) {
        this.userService = userService;
        this.queryService = queryService;
        this.finishTaskService = finishTaskService;
        this.tasksService = tasksService;
        this.pointsService = pointsService;
    }

    @GetMapping("")
    public String tasksPage(Model model){
        model.addAttribute("authUser", userService.getCurrentUser());
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("tasks", tasksService.getAllTasks());
        model.addAttribute("finishTasksList", finishTaskService.findCompletedTasksByUserId(userService.getCurrentUser(), tasksService.getAllTasks()));
        System.out.println(finishTaskService.findCompletedTasksByUserId(userService.getCurrentUser(), tasksService.getAllTasks()));
        System.out.println(tasksService.getAllTasks());
        return "general/tasks/tasksMain";
    }

    @GetMapping("/{taskId}")
    public String sqlPage(@PathVariable("taskId") Long taskId,
                          Model model){
        model.addAttribute("authUser", userService.getCurrentUser());
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("resultQuery", resultQuery);
        model.addAttribute("resultFromDoc", resultFromDoc);
        model.addAttribute("task", tasksService.getById(taskId));
        model.addAttribute("isCorrectResult", isCorrectResult);
        isCorrectResult = false;
        resultQuery = null;
        return "general/tasks/task";
    }

    @PostMapping(value = "/{taskId}", params = "sqlQuery")
    public String postSendQuery(@PathVariable("taskId") Long taskId,
                                      @RequestParam("sqlQuery") String sqlQuery,
                                      @RequestParam("result") String result) {
        resultQuery = queryService.getRecordByRoll(sqlQuery, true);
        User user = userService.getCurrentUser();
        isCorrectResult = resultQuery.equals(queryService.getRecordByRoll(tasksService.getById(taskId).getTaskResult(), true));
        if (user != null) {
            Long userId = user.getId();
            if (!finishTaskService.isFinishTask(userId, taskId) && isCorrectResult){
                finishTaskService.saveTask(userId, taskId);
                if (pointsService.isUserHasPoints(userId)){
                    pointsService.updatePoints(userId, POINT);
                } else {
                    pointsService.savePoints(userId, POINT);
                }
            } else {
                System.out.println("NO");
            }
        }
        this.resultFromDoc = result;
        return "redirect:" + taskId;
    }
}
