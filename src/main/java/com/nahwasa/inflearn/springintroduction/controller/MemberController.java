package com.nahwasa.inflearn.springintroduction.controller;

import com.nahwasa.inflearn.springintroduction.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired  // 이게 써있으면 스프링 컨테이너가 알아서 넣어줌. 생성자 1개일 땐 생략 가능.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }



}
