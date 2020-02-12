package com.alexcorp.springtemplate.controllers;

import com.alexcorp.springtemplate.domains.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    String main(@AuthenticationPrincipal User user, Model model) {

        return "main";
    }
}