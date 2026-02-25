package com.example.demo.relation;

import com.example.demo.relation.model.A;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AService {
    private final ARepository aRepository;

    public void read(Long idx) {
        A a = aRepository.findById(idx).orElseThrow();

        System.out.println("A");
    }
    public void list() {
        List<A> list = aRepository.findAll();

        for(A a : list) {
            System.out.println(a.getIdx() + " " + a.getA01());
        }
        System.out.println("");
    }
}
