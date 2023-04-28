//package ru.osminkin.sqlesson.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import ru.osminkin.sqlesson.services.QueryService;
//import ru.osminkin.sqlesson.services.UserService;
//
//import java.util.*;
//
//@Controller
//public class DocumentationPageController {
//    private final UserService userService;
//    private final QueryService queryService;
//    private List<Map<String, Object>> resultQuery;
//    private String resultFromDoc;
//
//    public DocumentationPageController(UserService userService, QueryService queryService) {
//        this.userService = userService;
//        this.queryService = queryService;
//    }
//
//    @GetMapping("/documentation")
//    public String documentationPage(Model model){
//        model.addAttribute("authUser", userService.getCurrentUser());
//        model.addAttribute("user", userService.getCurrentUser());
//        return "general/documentation";
//    }
//
//    @GetMapping("/documentation/sql")
//    public String sqlPage(Model model){
//        model.addAttribute("authUser", userService.getCurrentUser());
//        model.addAttribute("user", userService.getCurrentUser());
//        model.addAttribute("resultQuery", resultQuery);
//        model.addAttribute("resultFromDoc", resultFromDoc);
//        resultQuery = null;
//        return "general/sql";
//    }
//
//    @PostMapping(value = "/documentation/sql", params = "sqlQuery")
//    public String postSendQuery(@RequestParam("sqlQuery") String sqlQuery,
//                                @RequestParam("result") String result) {
//        resultQuery = queryService.getRecordByRoll(sqlQuery);
//        this.resultFromDoc = result;
//        return "redirect:/documentation/sql";
//    }
//}
