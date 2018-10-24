package com.huige.mcapp.vo;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * @author 张西超
 * @version 1.0
 * @email zhenxinAngel@vip.qq.com
 * @remark
 * @date 2018-7-3 16:38
 */
public class VoRelayParameter implements Serializable {
    private Boolean relayEnable =false;
    private String relayTitle;
    private String relayIcon;
    transient private Integer index;

    public Boolean getRelayEnable() {
        return relayEnable;
    }

    public void setRelayEnable(Boolean relayEnable) {
        this.relayEnable = relayEnable;
    }

    public String getRelayTitle() {
        return relayTitle;
    }

    public void setRelayTitle(String relayTitle) {
        this.relayTitle = relayTitle;
    }

    public String getRelayIcon() {
        return relayIcon;
    }

    public void setRelayIcon(String relayIcon) {
        this.relayIcon = relayIcon;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
