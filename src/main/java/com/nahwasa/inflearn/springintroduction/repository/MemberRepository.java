package com.nahwasa.inflearn.springintroduction.repository;

import com.nahwasa.inflearn.springintroduction.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository { // 일단 인터페이스로 작성해서 다른 방식들(JPA 등)로 쉽게 넘어갈 수 있도록 해둠.
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
