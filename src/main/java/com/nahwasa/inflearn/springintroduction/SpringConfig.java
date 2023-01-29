package com.nahwasa.inflearn.springintroduction;

import com.nahwasa.inflearn.springintroduction.repository.JdbcMemberRepository;
import com.nahwasa.inflearn.springintroduction.repository.MemberRepository;
import com.nahwasa.inflearn.springintroduction.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * @Service랑 @Repository 쓰지 않고 직접 스프링 컨테이너에 등록하는 방법임.
     * @Controller는 이런식으로 대체 안됨.
     * 일반적으로 그냥 어노테이션 써서 하는게 편함.
     * 실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용한다.
     * 그리고 정형화되지 않거나, 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다.
     * (예를들어 현재 MemoryMemberRepository()를 쓰고 있는데, 실제 DB 연결해서 바꿔치기할 때 기존 코드 변경 없이 아래쪽 설정만 바꾸면 끝)
     */
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {    // 다른 부분 손대지 않고 여기에 Bean만 어떤걸로 만들지만 수정함.
//        return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}
