package ru.osminkin.sqlesson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.osminkin.sqlesson.services.QueryService;
import ru.osminkin.sqlesson.services.UserService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/lessons")
public class AllLessonsController {
    private final UserService userService;
    private final QueryService queryService;
    private List<Map<String, Object>> resultQuery;
    private String resultFromDoc;

    public AllLessonsController(UserService userService, QueryService queryService) {
        this.userService = userService;
        this.queryService = queryService;
    }

    @GetMapping("")
    public String lessonsPage(Model model){
        model.addAttribute("authUser", userService.getCurrentUser());
        model.addAttribute("user", userService.getCurrentUser());
        return "general/lessons/lessons";
    }

    @GetMapping("/{current_page}")
    public ModelAndView sqlPage(@PathVariable("current_page") String current_page,
                                Model model){
        model.addAttribute("authUser", userService.getCurrentUser());
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("resultQuery", resultQuery);
        model.addAttribute("resultFromDoc", resultFromDoc);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("general/lessons/" + current_page);
        resultQuery = null;
        return modelAndView;
    }

    @PostMapping(value = "/{current_page}", params = "sqlQuery")
    public ModelAndView postSendQuery(@PathVariable("current_page") String current_page,
                                      @RequestParam("isChangeQuery") boolean isChangeQuery,
                                      @RequestParam("sqlQuery") String sqlQuery,
                                      @RequestParam("result") String result) {
        resultQuery = queryService.getRecordByRoll(sqlQuery, isChangeQuery);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + current_page);
        this.resultFromDoc = result;
        return modelAndView;
    }
}
