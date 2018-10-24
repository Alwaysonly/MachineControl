package com.huige.mc.vo;

import java.io.Serializable;

/**
 * @author 张西超
 * @version 1.0
 * @email zhenxinAngel@vip.qq.com
 * @remark
 * @date 2018-6-25 15:28
 */
public class VoServerLinkParameter implements Serializable {
    private String host;
    private Integer port;
    private Integer lpid;
    private String imei;
    private String appcode;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getLpid() {
        return lpid;
    }

    public void setLpid(Integer lpid) {
        this.lpid = lpid;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getAppcode() {
        return appcode;
    }

    public void setAppcode(String appcode) {
        this.appcode = appcode;
    }

    @Override
    public String toString() {
        return "VoServerLinkParameter{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", lpid=" + lpid +
                ", imei='" + imei + '\'' +
                ", appcode='" + appcode + '\'' +
                '}';
    }
}
