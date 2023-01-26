package com.nahwasa.inflearn.springintroduction.service;

import com.nahwasa.inflearn.springintroduction.domain.Member;
import com.nahwasa.inflearn.springintroduction.repository.MemberRepository;
import com.nahwasa.inflearn.springintroduction.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원X
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // Optional로 받은 이유가 이런식으로 처리하기 위해서. orElse도 많이 씀.
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() { // Repository는 save, findByName 이런식의 명명인데,
                                        // 서비스는 좀 더 비지니스 로직적인 함수명을 써야 좋음. 서비스는 비지니스를 처리하는게 역할임.
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
