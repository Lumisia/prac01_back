package com.example.demo.config;

import com.example.demo.config.interceptor.CheckRoomAuthInterceptor;
import com.example.demo.config.interceptor.JwtHandshakeInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;


@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//    private final WebSocketHandler webSocketHandler;
    private final JwtHandshakeInterceptor jwtHandshakeInterceptor;
    private final CheckRoomAuthInterceptor checkRoomAuthInterceptor;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
//                .addInterceptors(jwtHandshakeInterceptor)
                .setAllowedOrigins("*");
                //.withSockJs()
        // 웹 브라우저에서 WS 프로토콜을 지원하지 않는 경우
        // WS 대신에 HTTP로 통신할 수 있게 해주는 라이브러리를 사용할 때
    }

//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(checkRoomAuthInterceptor);
//    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 웹소켓에 이런 주소로 시작하는 메세지를 보내면 브로커에게 보낸다는 의미
        // 구독자가 메세지를 받을 경로의 시작 부분
        registry.enableSimpleBroker("/topic");
        // 클라이언트가 메세지를 보낼 때 사용할 주소의 시작 부분
        registry.setApplicationDestinationPrefixes("/app");
        // 특정 사용자에게 메세지를 보낼 때 사용할 주소의 시작 부분
        registry.setUserDestinationPrefix("/user");
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/ws-stomp", "/user/login");
    }

    //    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(webSocketHandler, "/ws")
//                .setAllowedOrigins("*")
//                .addInterceptors(jwtHandshakeInterceptor);
//    }
}