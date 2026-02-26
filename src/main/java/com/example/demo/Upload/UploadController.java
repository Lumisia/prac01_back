package com.example.demo.Upload;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/upload")
@RequiredArgsConstructor
@RestController
public class UploadController {

    // 의존성 주입할 객체가 프로젝트 내에 여러개 존재할 때
    // @Qualifier로 특정 객체를 지정하거나
    // 컴포넌트 객체에 @Primary를 달아두면 해당 객체가 주입된다.
    //      @Qualifier(value = "cloudUploadService")
    private final UploadService uploadService;

    @PostMapping
    public ResponseEntity upload(List<MultipartFile> fileList) {



    }
}
