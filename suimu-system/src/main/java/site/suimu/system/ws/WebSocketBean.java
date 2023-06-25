package site.suimu.system.ws;

import lombok.Data;
import org.springframework.web.socket.WebSocketSession;


/**
 * websocket 包装类
 */
@Data
public class WebSocketBean {


    /**
     * webSocketSession
     */
    private WebSocketSession webSocketSession;

    /**
     * 客户端编码
     */
    private int clientId;
}
