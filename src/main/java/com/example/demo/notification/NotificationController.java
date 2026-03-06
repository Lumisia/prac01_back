package com.example.demo.notification;

// 모바일 푸시 : OS(Android/IOS)에서 푸시 수신 기능을 제공,
//              어플을실행하지 않아도 핸드폰만 켜져있다면 알림 수신 가능
// 웹 푸시 : 웹 브라우저에서 푸시 수신 기능 제공,
//              웹 사이트에 접속해야 하는 것은 아니지만 웹 브라우저를 실행해야지만 알림 수신 가능

import com.example.demo.notification.model.NotificationEntityDto;
import lombok.RequiredArgsConstructor;
import org.jose4j.lang.JoseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/push")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/sub")
    private ResponseEntity subscribe(@RequestBody NotificationEntityDto.Subscribe dto) {

        notificationService.save(dto);

        return ResponseEntity.ok("성공");
    }

    @GetMapping("/send")
    public ResponseEntity send(@RequestBody NotificationEntityDto.Send dto) throws JoseException, GeneralSecurityException, IOException, ExecutionException, InterruptedException {
        notificationService.send(dto);

        return ResponseEntity.ok("성공");
    }
}
