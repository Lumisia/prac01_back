package com.example.demo.relation;

import com.example.demo.relation.model.A;
import com.example.demo.relation.model.B;
import com.example.demo.relation.model.BDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BService {
    private final BRepository bRepository;

    public BDto.BRes reg(Long aIdx, BDto.BReq dto) {
        B b = dto.toEntity(aIdx);
        b = bRepository.save(b);

        return BDto.BRes.form(b);
    }

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
