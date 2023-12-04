package com.example.dungeons_dragons.controllers;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebPageController {

    @RequestMapping(value="/")
    public String HolaMundo() {
        return "index";
    }

    @RequestMapping(value="/avatares")
    public String Avatares() {
        return "avatares";
    }

    @RequestMapping(value="/details")
    public String Details() {
        return "details";
    }
}
;