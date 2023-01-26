package com.nahwasa.inflearn.springintroduction.service;

import com.nahwasa.inflearn.springintroduction.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberService();

    @Test
    void 회원가입() {   // 테스트는 영어권 사람들이랑 일하는게 아니라면 한글로 적어도 상관없다고 함.
        // given - 뭔가가 주어졌는데
        Member member = new Member();
        member.setName("nahwasa");

        // when - 이거를 실행했을 때
        Long saveId = memberService.join(member);

        // then - 결과가 이게 나와야 해
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
    }

    @Test
    public void 중복_회원_예외() {
        Member member1 = new Member();
        member1.setName("nahwasa");

        Member member2 = new Member();
        member2.setName("nahwasa");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}