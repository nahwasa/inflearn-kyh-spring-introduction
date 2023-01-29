package com.nahwasa.inflearn.springintroduction.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * AOP가 필요한 상황
 * - 회원 관련 모든 메소드의 호출 시간을 측정하고 싶다면?
 *      = MemberService의 모든 메소드에 System.currentTimeMillis로 시작과 끝 지점 시간을 받아서 측정! 근데 메소드가 1000개면? 다 해야지.
 * - 공통 관심 사항(cross-cutting concern) vs 핵심 관심 사항(core concern)
 *      = 회원가입, 회원 조회에 시간을 측정하는 기능은 핵심 관심 사항이 아니다.
 *      = 시간을 측정하는 로직은 공통 관심 사항이다.
 *      = 시간을 측정하는 로직과 핵심 비즈니스의 로직이 섞여서 유지보수가 어렵다.
 *      = 시간을 측정하는 로직을 별도의 공통 로직으로 만들기 매우 어렵다.
 *      = 시간을 측정하는 로직을 변경할 때 모든 로직을 찾아가면서 변경해야 한다.
 * - 회원 가입 시간, 회원 조회 시간을 측정하고 싶다면?
 *
 *
 * AOP (Aspect Oriented Programming)
 * - 위와 같은 문제를 해결할 수 있음.
 * - 공통 관심 사항과 핵심 관심 사항을 분리!
 *
 */
@Aspect // AOP 씀을 나타냄.
@Component
public class TimeTraceAop {

    @Around("execution(* com.nahwasa.inflearn.springintroduction..*(..))")  // 전부 적용!
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
            /*
            함수 호출 시 프록시라는 가짜 클래스를 호출하게 함. 그리고 proceed 호출 시 실제 클래스를 호출함.

            기존 : 실제 HelloController -> 실제 MemberService -> 실제 MemberRepository
            AOP 적용 : 프록시 HelloController -> 실제 HelloController -> 프록시 MemberService
                        -> 실제 MemberService -> 프록시 MemberRepository -> 실제 MemberRepository

            스프링은 이런식으로 프록시 방식의 AOP 사용.
            컴파일 타임 때 코드를 넣어주는 기술도 있음.
             */
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }

    }
}
