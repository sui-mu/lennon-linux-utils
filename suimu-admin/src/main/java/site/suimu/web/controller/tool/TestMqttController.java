package site.suimu.web.controller.tool;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.suimu.common.core.controller.BaseController;
import site.suimu.common.core.domain.AjaxResult;
import site.suimu.system.domain.MqttMsg;
import site.suimu.system.mqtt.MqttMessageSender;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/test/mqtt")
@Slf4j
public class TestMqttController extends BaseController {


    @Autowired
    MqttMessageSender mqttMessageSender;


    public int count = 0;


    public List<MqttMsg> msgList = new ArrayList<>();


    @PostMapping("send")
    public AjaxResult pushMqttMsg(@RequestBody MqttMsg mqttMsg) {
        mqttMsg.setPayload(mqttMsg.getPayload() + ++count);
        log.info("mqttMsg : {}", mqttMsg);
        try {
            mqttMessageSender.sendToMqtt(mqttMsg.getTopic(), 2, mqttMsg.getPayload());
        } catch (Exception e) {
            msgList.add(mqttMsg);
            log.error("list Size: {}, msg: {}", msgList.size(), e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }


}
