package com.huige.mcapp.vo;

import java.io.Serializable;

/**
 * @author 张西超
 * @version 1.0
 * @email zhenxinAngel@vip.qq.com
 * @remark
 * @date 2018-7-3 16:36
 */
public class VoRs232Parameter implements Serializable {
    private String title;
    private Integer rs232Port;
    private String onCmd;
    private String offCmd;
    private Boolean rs232Enable =false;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRs232Port() {
        return rs232Port;
    }

    public void setRs232Port(Integer rs232Port) {
        this.rs232Port = rs232Port;
    }

    public String getOnCmd() {
        return onCmd;
    }

    public void setOnCmd(String onCmd) {
        this.onCmd = onCmd;
    }

    public String getOffCmd() {
        return offCmd;
    }

    public void setOffCmd(String offCmd) {
        this.offCmd = offCmd;
    }

    public Boolean getRs232Enable() {
        return rs232Enable;
    }

    public void setRs232Enable(Boolean rs232Enable) {
        this.rs232Enable = rs232Enable;
    }
}
