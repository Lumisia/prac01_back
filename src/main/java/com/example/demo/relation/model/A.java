package com.example.demo.relation.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class A {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String a01;

    // 외래키 속성 지정
    // 관계의 주인이 누구인지 지정
    // FetchType 기본값이 Lazy : 연관관계의 엔티티의 값을 값이 사용될 떄 SQL을 한 번 더 실행해서 가져온다.
    // FetchType 기본값이 Eager : 현재 엔티티를 조회하면서 연관관계의 엔티티도 같이 가져온다.
    @OneToMany(mappedBy = "a", fetch = FetchType.LAZY)
    private List<B> b_list;
}
