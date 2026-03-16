package com.lantu.config;

import com.lantu.sys.websocket.DroneWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class   WebSocketConfig implements WebSocketConfigurer {

    private final DroneWebSocketHandler droneWebSocketHandler;

    public WebSocketConfig(DroneWebSocketHandler droneWebSocketHandler) {
        this.droneWebSocketHandler = droneWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(droneWebSocketHandler, "/ws/drone")
                .setAllowedOrigins("*"); // 跨域设置：允许所有来源
        System.out.println("WebSocket 处理器已注册在路径：/ws/drone");
    }

}
