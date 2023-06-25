package site.suimu.system.domain.vo;


import lombok.Data;

@Data
public class WsMsgReq {

    private String clientId;

    private Object message;

}
