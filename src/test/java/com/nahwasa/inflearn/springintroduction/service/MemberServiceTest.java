package com.nahwasa.inflearn.springintroduction.service;

import com.nahwasa.inflearn.springintroduction.domain.Member;
import com.nahwasa.inflearn.springintroduction.repository.MemberRepository;
import com.nahwasa.inflearn.springintroduction.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        // 테스트끼리 영향을 끼치지 않게 하기위한 clear 호출을 위해 가져옴. 근데 MemberService에 있는거랑 다른 객체임(new 해서).
        // static으로 들어가있었어서 문제는 없었지만, 일반적으로 안좋은 구조임.

    @AfterEach
    public void afterEach() {   // 테스트끼리 영향을 끼치지 않도록.
        memberRepository.clearStore();
    }

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