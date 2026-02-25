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
    @OneToMany(mappedBy = "a")
    private List<B> b_list;
}
