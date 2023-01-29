package com.nahwasa.inflearn.springintroduction.repository;

import com.nahwasa.inflearn.springintroduction.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * JPA
 * - JPA는 기존의 반복 코드는 물론이고(순수 JDBC때 짰던 거), 기본적인 SQL도 JPSA가 직접 만들어서 실행해준다.(JdbcTemplate 보다도 간단!)
 * - JPA를 사용하면, SQL과 데이터 중심의 설계에서 객체 중심의 설계로 패러다임을 전환할 수 있다.
 * - JPA를 사용하면 개발 생산성을 크게 높일 수 있다.
 *
 * JPA는 인터페이스임. 구현체로 hibernate, eclipse 등 구현체들이 있음. starter-data-jpa에서 기본으로 쓰는건 hibernate
 * JPA는 ORM 기술. 객체와 릴레이션(테이블)을 매핑한다.
 */
@Transactional  // 트랜잭션 필수.
public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em; // JPA는 EntityManager로 사용함.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);  // persist : 영구 저장하다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);  // PK인 경우 이런식으로 쉽게 가능.
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        /*
         JPQL. 객체지향 쿼리 언어
         객체를 대상으로 쿼리를 날리면 알아서 SQL이 생성됨.
         */
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
