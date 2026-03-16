package com.example.sensordemo;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class UAVWebSocketClient extends WebSocketClient {

    public UAVWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Log.d("WebSocket", "连接已建立");
    }

    @Override
    public void onMessage(String message) {
        Log.d("WebSocket", "收到消息: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.d("WebSocket", "连接关闭: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        Log.e("WebSocket", "连接错误: ", ex);
    }
}
