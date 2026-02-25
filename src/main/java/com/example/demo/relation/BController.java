package com.example.demo.relation;

import com.example.demo.common.model.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/b")
public class BController {
    private final BService bService;

    @GetMapping("/read")
    public ResponseEntity read(@RequestParam Long idx) {
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
