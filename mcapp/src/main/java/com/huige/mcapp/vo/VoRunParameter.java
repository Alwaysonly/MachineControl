package com.huige.mcapp.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author 张西超
 * @version 1.0
 * @email zhenxinAngel@vip.qq.com
 * @remark
 * @date 2018-7-3 16:32
 */
public class VoRunParameter implements Serializable {
    public static final String RUN_PARAMETER = "RUN_PARAMETER";
    private VoRunParameterGlobal runParameterGlobal;
    private List<VoRelayParameter> relayParameters;
    private List<VoRs232Parameter> rs232Parameters;

    public static String getRunParameter() {
        return RUN_PARAMETER;
    }

    public VoRunParameterGlobal getRunParameterGlobal() {
        return runParameterGlobal;
    }

    public void setRunParameterGlobal(VoRunParameterGlobal runParameterGlobal) {
        this.runParameterGlobal = runParameterGlobal;
    }

    public List<VoRelayParameter> getRelayParameters() {
        return relayParameters;
    }

    public void setRelayParameters(List<VoRelayParameter> relayParameters) {
        this.relayParameters = relayParameters;
    }

    public List<VoRs232Parameter> getRs232Parameters() {
        return rs232Parameters;
    }

    public void setRs232Parameters(List<VoRs232Parameter> rs232Parameters) {
        this.rs232Parameters = rs232Parameters;
    }
}
