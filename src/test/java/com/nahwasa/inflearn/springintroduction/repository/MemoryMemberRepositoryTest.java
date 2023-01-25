package com.nahwasa.inflearn.springintroduction.repository;

import com.nahwasa.inflearn.springintroduction.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  // 이 부분으로 clear해주지 않으면 다른 테스트끼리 영향을 끼치게 됨. 예를들어 findAll 후 findByName 불리면 실패함.
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("nahwasa");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName() {
        Member member = new Member();
        member.setName("nahwasa1");
        repository.save(member);

        Member result = repository.findByName("nahwasa1").get();

        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("nahwasa1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("nahwasa2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}