package com.example.dungeons_dragons.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebPageController {

    @RequestMapping(value="/")
    public String HolaMundo() {
        return "index";
    }
}