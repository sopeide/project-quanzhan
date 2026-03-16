package com.lantu.sys.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lantu.sys.entity.DroneData;
import com.lantu.sys.service.DroneDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@Slf4j
public class DroneWebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final DroneDataService droneDataService;

    // 用于保存所有连接的客户端
    private static final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    public DroneWebSocketHandler(DroneDataService droneDataService) {
        this.droneDataService = droneDataService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
        log.info("【WebSocket连接建立】会话ID: {}", session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
        log.info("【WebSocket连接关闭】会话ID: {}", session.getId());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        sessions.remove(session);
        log.error("【WebSocket传输错误】会话ID: {}, 错误: {}", session.getId(), exception.getMessage());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        String payload = message.getPayload();
        log.info("📩【接收到消息】原始内容: {}", payload);

        try {
            DroneData data = objectMapper.readValue(payload, DroneData.class);
            log.info("✅【解析成功】pitch={}, roll={}, yaw={}, lat={}, lng={}, alt={}",
                    data.getPitch(), data.getRoll(), data.getYaw(),
                    data.getLatitude(), data.getLongitude(), data.getAltitude());

            // 1. 保存到数据库
            droneDataService.saveDroneData(data);
            log.info("✅【已保存至数据库】");

            // 2. 推送给所有前端客户端
            String json = objectMapper.writeValueAsString(data);
            for (WebSocketSession client : sessions) {
                if (client.isOpen()) {
                    client.sendMessage(new TextMessage(json));
                }
            }
            log.info("📤【已推送前端】");

        } catch (Exception e) {
            log.error("❌【JSON解析或推送失败】: {}", e.getMessage());
        }
    }
}
