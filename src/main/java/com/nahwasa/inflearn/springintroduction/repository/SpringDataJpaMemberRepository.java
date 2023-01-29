package com.nahwasa.inflearn.springintroduction.repository;

import com.nahwasa.inflearn.springintroduction.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 스프링 데이터 JPA
 * - 순수 jdbc -> JdbcTemplate -> Raw JPA를 거쳐서 이번엔 데이터 JPA
 * - 이젠 리포지토리에 구현 클래스 없이 인터페이스 만으로 개발을 완료할 수 있음.
 * - 반복 개발해온 기본 CRUD 기능도 스프링 데이터 JPA가 모두 제공함.
 * - 스프링부트와 JPA라는 기반 위에 스프링 데이터 JPA라는 프레임워크까지 더하면 단순하고 반복적인 부분들이 확연하게 줄어들어서
 *   개발자는 핵심 비지니스 로직을 개발하는데 더 집중할 수 있음.
 *
 * 스프링 데이터 JPA는 JPA를 편리하게 쓰도록 도와주는 것이므로, JPA를 모르고 익히면 운영에서 만나는 여러 문제를 해결하기 힘들어져서 안됨.
 * JpaRepository를 넣어두면, 스프링 데이터 JPA가 알아서 스프링 컨테이너에 만들어서 등록해줌.
 *
 * 기존 MemberRepository 에 있떤 findById, save 같은거가 기본적으로 JpaRepository에서 제공됨. 페이징 기능도 자동 제공됨.
 *
 * 실무에서는 JPA와 스프링 데이터 JPA를 기본으로 사용하고, 복잡한 동적 쿼리는 Querydsl이라는 라이브러리를 사용하면 됨.
 * Querydsl 사용 시 쿼리도 자바 코드로 안전하게 작성 가능하고, 동적 쿼리도 편리하게 작성할 수 있음.
 * 이 조합으로도 해결하기 어려운 쿼리는 JPA가 제공하는 네이티브 쿼리(직접 SQL 작성)를 사용하거나,
 * 이전에 학습한 스프링 JdbcTemplate 사용하거나 MyBatis를 함께 사용.
 */
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
    // 얘는 JpaRepository에 공통으로 제공되지 않음.
    // findByColumn 이런식의 규칙으로 함수명을 짜두면 스프링 데이터 JPA가 알아서
    // select m from Member m where m.column = ? 와 같은 형태로 JPQL을 짜줌.
    // findByNameAndId(String name, Long id) 처럼도 가능.
    // 즉, 인터페이스 이름만으로 개발이 끝남.
}
