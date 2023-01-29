package com.nahwasa.inflearn.springintroduction.service;

import com.nahwasa.inflearn.springintroduction.domain.Member;
import com.nahwasa.inflearn.springintroduction.repository.MemberRepository;
import com.nahwasa.inflearn.springintroduction.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 스프링 컨테이너까지 올려서 테스트하는 것 보다, 단위 테스트가 좀 더 좋은 테스트일 확률이 높다고 함.
 */
@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행한다.
@Transactional  // 테스트 시작 전에 트랜잭션 걸고, 각 테스트마다 rollback 해줌. 지우면 db에 들어가므로 다른 테스트에 영향 끼침.
                // commit은 TransactionManager를 통해 가능하고, 해당 테스트위에 @Commit 혹은 @Rollback(value = false)해서 테스트별로 조정도 가능.
                // Service 같은데 붙었을 땐 당연히 rollback 하는게 아니고, 테스트에 붙었을때만 그렇게 동작함.
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService; // 테스트의 경우 그냥 Autowired로 제일 편한 방식으로 해봐도 괜찮다고 함.
    @Autowired MemberRepository memberRepository;   // 테스트의 경우 운영DB로 하지 않고, 별도 테스트DB 또는 로컬의 DB에서 함.

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("nahwasa");

        // when
        Long saveId = memberService.join(member);

        // then
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
}