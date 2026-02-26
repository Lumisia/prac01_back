package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class UploadUtil {
    @Value("${upload.path}")
    private String defaultUploadPath;

    public String makeFolder() {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = date.replace("/", File.separator);

        File uploadPath = new File(defaultUploadPath + File.separator + folderPath);

        if(!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        return uploadPath.getPath();
    }
}
