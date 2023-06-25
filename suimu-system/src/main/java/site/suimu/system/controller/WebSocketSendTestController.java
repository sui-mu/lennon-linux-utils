package site.suimu.system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import site.suimu.common.core.controller.BaseController;
import site.suimu.common.core.domain.AjaxResult;
import site.suimu.common.utils.StringUtils;
import site.suimu.system.domain.vo.WsMsgReq;
import site.suimu.system.ws.MyWebsocketHandler;
import site.suimu.system.ws.WebSocketBean;

@RestController
@RequestMapping("/test/ws")
public class WebSocketSendTestController extends BaseController {


    @Autowired
    MyWebsocketHandler myWebsocketHandler;

    @PostMapping("send")
    public AjaxResult send2Client(@RequestBody WsMsgReq wsMsgReq) throws Exception {
        System.out.println(myWebsocketHandler.getMap());
        WebSocketBean webSocketBean = myWebsocketHandler.getWebSocketBeanByClientId(wsMsgReq.getClientId());
        if (StringUtils.isNotNull(webSocketBean)) {
            WebSocketSession session = webSocketBean.getWebSocketSession();
            session.sendMessage(new TextMessage(wsMsgReq.getMessage().toString()));
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }

    }
}
