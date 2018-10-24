package com.huige.mc.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.huige.mc.R;
import com.huige.mc.vo.VoServerLinkParameter;

import org.apache.commons.lang3.StringUtils;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_setting)
public class SettingActivity extends BaseActivity {
    public static final String SERVER_LINK_PARM = "SERVER_LINK_PARM";

    @ViewInject(R.id.et_host)
    private EditText et_host;
    @ViewInject(R.id.et_port)
    private EditText et_port;
    @ViewInject(R.id.et_lpid)
    private EditText et_lpid;
    @ViewInject(R.id.et_imei)
    private EditText et_imei;
    @ViewInject(R.id.et_app_code)
    private EditText et_app_code;

    private VoServerLinkParameter linkParameter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        init();
    }

    private void init() {

        @SuppressLint("HardwareIds")
        String imei = ((TelephonyManager) this.getSystemService(TELEPHONY_SERVICE)).getDeviceId();

        linkParameter = (VoServerLinkParameter) mACache.getAsObject(SERVER_LINK_PARM);
        if (linkParameter != null) {
            if (linkParameter.getHost() != null) {
                et_host.setText(linkParameter.getHost());
            }
            if (linkParameter.getPort() != null) {
                et_port.setText(linkParameter.getPort()+"");
            }
            if (linkParameter.getAppcode() != null) {
                et_app_code.setText(linkParameter.getAppcode());
            }
            if (linkParameter.getImei() != null) {
                et_imei.setText(linkParameter.getImei());
            } else {
                et_imei.setText(imei);
            }
            if (linkParameter.getLpid() != null) {
                et_lpid.setText(linkParameter.getLpid()+"");
            }
        } else {
            linkParameter = new VoServerLinkParameter();
            et_imei.setText(imei);
        }

    }


    @Event(R.id.bt_save)
    private void getEvent(View v) {
        switch (v.getId()) {
            case R.id.bt_save:
                try {
                    String serverHost = et_host.getText().toString();
                    int serverPort = Integer.parseInt(et_port.getText().toString());
                    String appcode = et_app_code.getText().toString();
                    String imei = et_imei.getText().toString();
                    int lpid = Integer.parseInt(et_lpid.getText().toString());
                    if (StringUtils.isBlank(serverHost)) {
                        Toast.makeText(this, "请填写主机地址", Toast.LENGTH_LONG).show();
                        return;
                    }
                    linkParameter.setHost(serverHost.trim());
                    linkParameter.setPort(serverPort);

                    if (StringUtils.isBlank(appcode)) {
                        Toast.makeText(this, "请填写软件版本", Toast.LENGTH_LONG).show();
                        return;
                    }
                    linkParameter.setAppcode(appcode.trim());

                    if (StringUtils.isBlank(imei)) {
                        Toast.makeText(this, "请填写设备编号", Toast.LENGTH_LONG).show();
                        return;
                    }
                    linkParameter.setImei(imei.trim());

                    linkParameter.setLpid(lpid);
                    mACache.put(SERVER_LINK_PARM, linkParameter);
                    Toast.makeText(this, "保存成功", Toast.LENGTH_LONG).show();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "保存失败,请检查数据是否合法。", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
