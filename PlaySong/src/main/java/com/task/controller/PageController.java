package com.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/setsong")
    public String showSetSongPage() {
        return "setsong";  // Maps to /WEB-INF/views/setsong.jsp
    }
}
