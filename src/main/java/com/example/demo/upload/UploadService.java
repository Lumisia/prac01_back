package com.example.demo.Upload;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadService {

    public List<String> upload(List<MultipartFile> fileList);
}
