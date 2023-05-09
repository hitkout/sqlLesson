package ru.osminkin.sqlesson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.osminkin.sqlesson.model.User;
import ru.osminkin.sqlesson.services.FinishLessonService;
import ru.osminkin.sqlesson.services.LessonsService;
import ru.osminkin.sqlesson.services.QueryService;
import ru.osminkin.sqlesson.services.UserService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/documentation")
public class AllDocumentationController {
    private final UserService userService;
    private final QueryService queryService;
    private final FinishLessonService finishLessonService;
    private final LessonsService lessonsService;
    private List<Map<String, Object>> resultQuery;
    private String resultFromDoc;

    public AllDocumentationController(UserService userService, QueryService queryService, FinishLessonService finishLessonService, LessonsService lessonsService) {
        this.userService = userService;
        this.queryService = queryService;
        this.finishLessonService = finishLessonService;
        this.lessonsService = lessonsService;
    }

    @GetMapping("")
    public String documentationPage(Model model){
        User user = userService.getCurrentUser();
        model.addAttribute("authUser", user);
        model.addAttribute("user", user);
        model.addAttribute("lessons", lessonsService.findAll());
        model.addAttribute("finishLessons", finishLessonService.listOfCompletedLessons(user));
        return "general/documentation";
    }

    @GetMapping("/{current_page}")
    public ModelAndView sqlPage(@PathVariable("current_page") String currentPage,
                                Model model){
        model.addAttribute("authUser", userService.getCurrentUser());
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("resultQuery", resultQuery);
        model.addAttribute("resultFromDoc", resultFromDoc);
        model.addAttribute("lessons", lessonsService.findAll());
        model.addAttribute("finishLessons", finishLessonService.listOfCompletedLessons(userService.getCurrentUser()));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("general/documentation/" + currentPage);
        resultQuery = null;
        return modelAndView;
    }

    @PostMapping(value = "/{current_page}", params = "sqlQuery")
    public ModelAndView postSendQuery(@PathVariable("current_page") String current_page,
                                      @RequestParam("sqlQuery") String sqlQuery,
                                      @RequestParam("result") String result) {
        resultQuery = queryService.getRecordByRoll(sqlQuery, false);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + current_page);
        this.resultFromDoc = result;
        return modelAndView;
    }

    @PostMapping(value = "/{current_page}", params = "saveLesson")
    public String postSaveLesson(@PathVariable("current_page") String current_page) {
        User user = userService.getCurrentUser();
        if (user != null){
            Long userId = userService.getCurrentUser().getId();
            Long lessonId = Long.parseLong(current_page);
            if (!finishLessonService.isCompletedLesson(userId, lessonId)){
                finishLessonService.saveLesson(userId, lessonId);
            }
        }
        return "redirect:" + current_page;
    }
}
