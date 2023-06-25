package site.suimu.system.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Bean
    public MyWebSocketInterceptor webSocketInterceptor(){return new MyWebSocketInterceptor();}

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){return new ServerEndpointExporter();}

    @Autowired
    MyWebsocketHandler myWebsocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(myWebsocketHandler, "/websocket").addInterceptors(webSocketInterceptor());
    }
}

