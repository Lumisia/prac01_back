package com.example.demo.relation;

import com.example.demo.relation.model.A;
import com.example.demo.relation.model.B;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BService {
    private final BRepository bRepository;
    public void read(Long idx) {
        B b = bRepository.findById(idx).orElseThrow();

        System.out.println("B");
    }
    public void list() {
        List<B> list = bRepository.findAll();

        for(B b : list) {
            System.out.print(b.getIdx() + " " + b.getA());
        }
        System.out.println("");
    }
}
