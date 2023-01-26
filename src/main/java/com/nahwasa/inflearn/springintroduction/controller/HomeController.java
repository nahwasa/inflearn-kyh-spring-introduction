package com.nahwasa.inflearn.springintroduction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")    // 이게 없었다면 index.html로 자동으로 갔었는데, 이게 들어갔고 이게 더 우선순위가 높아서 home.html로 감.
    public String home() {
        return "home";
    }
}
