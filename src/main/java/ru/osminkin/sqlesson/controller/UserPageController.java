package ru.osminkin.sqlesson.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.osminkin.sqlesson.services.*;
import ru.osminkin.sqlesson.services.PhotoService;
import ru.osminkin.sqlesson.services.UserService;

@Controller
public class UserPageController {
    private final UserService userService;
    private final PhotoService photoService;

    public UserPageController(UserService userService,
                              PhotoService photoService) {
        this.userService = userService;
        this.photoService = photoService;
    }

    @GetMapping("/channel/{userId}")
    public String getChannel(@PathVariable("userId") long userId,
                             Authentication authentication,
                             Model model){
        model.addAttribute("user", userService.findUserById(userId));
        model.addAttribute("authUser", userService.getCurrentUser());
        model.addAttribute("photos", photoService.findAllPhotosByUserId(userId));
        model.addAttribute("userStatus", userService.getUserStatus(userId));
        return "channel/channelMain";
    }

    @PostMapping(value = "/channel/{userId}", params = "banUser")
    @PreAuthorize("hasRole('ADMIN')")
    public String postBanned(@PathVariable("userId") long userId) {
        userService.banUserById(userId);
        return "redirect:/channel/{userId}";
    }

    @PostMapping(value = "/channel/{userId}", params = "unbanUser")
    @PreAuthorize("hasRole('ADMIN')")
    public String postUnbanned(@PathVariable("userId") long userId) {
        userService.unbanUserById(userId);
        return "redirect:/channel/{userId}";
    }
}