package com.huige.mcapp.vo;

import java.io.Serializable;

/**
 * @author 张西超
 * @version 1.0
 * @email zhenxinAngel@vip.qq.com
 * @remark
 * @date 2018-7-3 16:33
 */
public class VoRunParameterGlobal implements Serializable {
    private String relayHost = "192.168.1.232";
    private Integer relayPort = 10000;
    private Integer relayFlashTime = 2;
    private String rs232Host = "192.168.1.233";
    private Boolean relayEnable = false;
    private Boolean rs232Enable = false;
    private String bankName = "北京慧格创新科技有限公司";

    public String getRelayHost() {
        return relayHost;
    }

    public void setRelayHost(String relayHost) {
        this.relayHost = relayHost;
    }

    public Integer getRelayPort() {
        return relayPort;
    }

    public void setRelayPort(Integer relayPort) {
        this.relayPort = relayPort;
    }

    public Integer getRelayFlashTime() {
        return relayFlashTime;
    }

    public void setRelayFlashTime(Integer relayFlashTime) {
        this.relayFlashTime = relayFlashTime;
    }

    public String getRs232Host() {
        return rs232Host;
    }

    public void setRs232Host(String rs232Host) {
        this.rs232Host = rs232Host;
    }

    public Boolean getRelayEnable() {
        return relayEnable;
    }

    public void setRelayEnable(Boolean relayEnable) {
        this.relayEnable = relayEnable;
    }

    public Boolean getRs232Enable() {
        return rs232Enable;
    }

    public void setRs232Enable(Boolean rs232Enable) {
        this.rs232Enable = rs232Enable;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
