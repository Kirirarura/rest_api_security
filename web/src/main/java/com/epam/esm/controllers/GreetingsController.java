package com.epam.esm.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world Controller
 */

@RestController
@RequestMapping("/")
public class GreetingsController {

    @GetMapping
    public List<String> greetings() {
        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("Spring");
        return list;
    }
}
