package site.suimu.system.domain;


import lombok.Data;

@Data
public class MqttMsg {

    private String topic;

    private String payload;
}
