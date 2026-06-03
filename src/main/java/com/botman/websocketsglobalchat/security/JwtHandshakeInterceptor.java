package com.botman.websocketsglobalchat.security;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.net.URI;
import java.util.Map;

import static org.springframework.boot.context.properties.source.ConfigurationPropertyName.isValid;

//this will be the 2nd step
//it will ensure that each WebSocket connection is tied to a valid, authenticated user
public class JwtHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes
                                   ) throws Exception {
        URI uri = request.getURI();
        String token = extractTokenFromUri(uri);// I need to implement this method

        if(isValid(token)){
            String username = extractUsernameFromToken(token);
            attributes.put("username", username);
            return true;
        }
    }

    private String extractTokenFromUri(URI uri) {
    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response,
                               WebSocketHandler wsHandler,
                               Exception exception){
        //nada aqui
    }
}
