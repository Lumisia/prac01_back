package com.example.demo.relation;

import com.example.demo.common.model.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/a")
public class AController {
    private final AService aService;

    @GetMapping("/read")
    public ResponseEntity read(@RequestParam Long idx) {
        aService.read(idx);

        return ResponseEntity.ok(
                BaseResponse.success("ok")
        );
    }
    @GetMapping("/list")
    public ResponseEntity list() {
        aService.list();

        return ResponseEntity.ok(
                BaseResponse.success("ok")
        );
    }
}
