package com.nahwasa.inflearn.springintroduction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; // viewResolver가 templates 폴더에서 찾아서 반환
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template"; // viewResolver가 templates 폴더에서 찾아서 반환
    }

    @GetMapping("hello-string")
    @ResponseBody   //response의 BODY 부분에 리턴값을 직접 넣어주겠다는 의미.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // 그냥 문자열을 바디에 넣어 리턴 (기본문자는 HttpMessageConverter구현한 StringHttpMessageConverter가 수행)
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;   // 위처럼 스트링이면 그냥 반환하는데, 객체가 오면 기본적으로 json으로 바꿔서 리턴.
                        // (HttpMessageConverter구현한 MappingJackson2HttpMessage가 수행)
                        // (byte 처리 등등 기타 여러 HttpMessageConverter 구현체가 기본으로 등록되어 있음 -> 바꿀순있는데 실무에서 딱히 손댈일 없음)
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
