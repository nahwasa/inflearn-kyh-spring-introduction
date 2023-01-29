package com.nahwasa.inflearn.springintroduction.domain;

import jakarta.persistence.*;

@Entity // JPA가 관리하는 엔티티가 됨.
public class Member {

    @Id // PK 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // DB가 알아서 생성해주는 경우 IDENTITY. pk 부분 없이 insert된다. DB에 auto increment 걸려있는 경우 사용.
    // default는 GenerationType.AUTO. 이 경우 커넥션이 2개가 필요하므로 주의.
    private Long id;

//    @Column(name = "username")  DB에 컬럼명이 username일 경우 이런식으로.
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
