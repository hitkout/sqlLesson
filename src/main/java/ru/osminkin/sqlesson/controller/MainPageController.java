package ru.osminkin.sqlesson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.osminkin.sqlesson.services.PhotoService;
import ru.osminkin.sqlesson.services.PointsService;
import ru.osminkin.sqlesson.services.TypeAndSortService;
import ru.osminkin.sqlesson.services.UserService;

@Controller
public class MainPageController {
    private final UserService userService;
    private final PointsService pointsService;

    public MainPageController(UserService userService,
                              PointsService pointsService) {
        this.userService = userService;
        this.pointsService = pointsService;
    }

    @GetMapping("")
    public String mainPage(Model model){
        model.addAttribute("authUser", userService.getCurrentUser());
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("points", pointsService.findAll());
        return "general/main";
    }
}
