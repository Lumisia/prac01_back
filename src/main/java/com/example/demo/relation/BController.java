package com.example.demo.relation;

import com.example.demo.common.model.BaseResponse;
import com.example.demo.relation.model.BDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/b")
public class BController {
    private final BService bService;

    @PostMapping("reg/{aIdx}")
    public ResponseEntity reg(@PathVariable Long idx, @RequestBody BDto.BReq dto) {
        BDto.BRes result = bService.reg(idx, dto);

        return ResponseEntity.ok(
                BaseResponse.success(result)
        );
    }


    @GetMapping("/read/{aIdx}")
    public ResponseEntity read(@PathVariable Long idx) {
        bService.read(idx);

        return ResponseEntity.ok(
                BaseResponse.success("ok")
        );
    }

    @GetMapping("/list")
    public ResponseEntity list() {
        bService.list();

        return ResponseEntity.ok(
                BaseResponse.success("ok")
        );
    }
}
