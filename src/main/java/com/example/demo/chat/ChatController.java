package com.example.demo.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class ChatController {

    // WebSocketConfig에서 설정해둔 /app 뒤에 /test로 메세지를 보내면
    //          해당 메서드를 실행하면서 return값을 메세지로 보냄.
    @MessageMapping("/test")
    // test라는 토픽을 구독한 클라이언트에게 메세지 전송
    @SendTo("/topic/test")
    public String test() {
        System.out.println("test");

        return "zzzz";
    }
}
