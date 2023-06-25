package site.suimu.system.mqtt;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @author zhangs lennon
 */
@Component
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttMessageSender {

    /**
     * 定义重载方法，用于消息发送
     * @param payload 消息主体
     */
    void sendToMqtt(String payload);

    /**
     * 指定topic进行消息发送
     * @param topic 消息主题
     * @param payload 消息主体
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, String payload);

    /**
     * 指定topic进行消息发送
     * @param topic 消息主题
     * @param qos (Quality of Service) 消息质量
     *            At most once (0)
     *            At least once (1)
     *            Exactly once (2)
     * @param payload 消息主体
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);


}
