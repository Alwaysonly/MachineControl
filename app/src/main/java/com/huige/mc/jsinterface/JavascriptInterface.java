package com.huige.mc.jsinterface;

import android.util.Log;

import com.huige.mc.utils.CommandGenerate;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author 张西超
 * @version 1.0
 * @email zhenxinAngel@vip.qq.com
 * @remark 提供给js的接口
 * @date 2018-6-26 12:37
 */
public class JavascriptInterface {

    private static final String TAG = JavascriptInterface.class.getSimpleName();

    /**
     * 继电线圈闪开 协议udp
     *
     * @param data JSON格式 key: host主机地址 port端口号 index线圈地址 time闪开时间（单位0.1s）
     */
    @android.webkit.JavascriptInterface
    public synchronized String CallRelayWriteSingle(String data) {
        DatagramSocket dSocket = null;
        try {
            JSONObject object = new JSONObject(data);
            byte[] cmd = CommandGenerate.GenerateFlashSingle(object.getInt("index"), false, object.getInt("time"));
            Log.i(TAG, "建立socket udp连接");
            dSocket = new DatagramSocket(object.getInt("port"));
            dSocket.setSoTimeout(1000);
            Log.i(TAG, "建立socket udp连接成功");
            InetAddress serverAddress = InetAddress.getByName(object.getString("host"));
            byte[] refData = new byte[8];
            DatagramPacket ref = new DatagramPacket(refData, 8);
            DatagramPacket pack = new DatagramPacket(cmd, cmd.length, serverAddress, object.getInt("port"));
            Log.i(TAG, "发送指令====>" + new String(Hex.encodeHex(cmd)).toUpperCase());
            dSocket.send(pack);
            dSocket.receive(ref);
            Log.i(TAG, "发送指令完成:" + new String(Hex.encodeHex(ref.getData())).toUpperCase());
            dSocket.close();
            Log.i(TAG, "连接关闭");
//            return Crc16Util.ValidationCRC16(ref.getData()) ? "1" : "0";
            return StringUtils.isNoneBlank(new String(Hex.encodeHex(ref.getData()))) ? "1" : "0";
        } catch (JSONException | DecoderException | IOException e) {
            e.printStackTrace();
            return "0";
        } finally {
            if (dSocket != null) {
                dSocket.close();
            }
        }

    }


    /**
     * 继电线圈全部闪开 协议udp
     *
     * @param data JSON格式 key: host主机地址 port端口号 time闪开时间（单位0.1s）
     */
    @android.webkit.JavascriptInterface
    public synchronized String CallRelayWriteAll(String data) {
        DatagramSocket dSocket = null;
        try {
            JSONObject object = new JSONObject(data);
            Log.i(TAG, "建立socket udp连接");
            dSocket = new DatagramSocket(object.getInt("port"));
            dSocket.setSoTimeout(1000);
            Log.i(TAG, "建立socket udp连接成功");
            InetAddress serverAddress = InetAddress.getByName(object.getString("host"));
            byte[] cmdon = CommandGenerate.GenerateWriteMultiple(true);

            byte[] refOnData = new byte[8];
            DatagramPacket refOn = new DatagramPacket(refOnData, 8);

            DatagramPacket packon = new DatagramPacket(cmdon, cmdon.length, serverAddress, object.getInt("port"));
            Log.i(TAG, "发送指令====>" + new String(Hex.encodeHex(cmdon)).toUpperCase());
            dSocket.send(packon);
            dSocket.receive(refOn);
            Log.i(TAG, "发送指令完成:" + new String(Hex.encodeHex(refOn.getData())).toUpperCase());

            Thread.sleep(object.getInt("time") * 100);

            byte[] cmdoff = CommandGenerate.GenerateWriteMultiple(false);

            byte[] refOffData = new byte[8];
            DatagramPacket refOff = new DatagramPacket(refOffData, 8);

            DatagramPacket packoff = new DatagramPacket(cmdoff, cmdoff.length, serverAddress, object.getInt("port"));
            Log.i(TAG, "发送指令====>" + new String(Hex.encodeHex(cmdoff)).toUpperCase());
            dSocket.send(packoff);
            dSocket.receive(refOff);
            Log.i(TAG, "发送指令完成:" + new String(Hex.encodeHex(refOff.getData())).toUpperCase());
            dSocket.close();
            Log.i(TAG, "连接关闭");
//            return Crc16Util.ValidationCRC16(refOn.getData()) && Crc16Util.ValidationCRC16(refOff.getData()) ? "1" : "0";
            return StringUtils.isNoneBlank(new String(Hex.encodeHex(refOn.getData()))) &&
                    StringUtils.isNoneBlank(new String(Hex.encodeHex(refOff.getData()))) ? "1" : "0";
        } catch (JSONException | DecoderException | IOException | InterruptedException e) {
            e.printStackTrace();
            return "0";
        } finally {
            if (dSocket != null) {
                dSocket.close();
            }
        }
    }


    /**
     * 发送16进制指令 协议udp
     *
     * @param data JSON格式 key: host主机地址 port端口号 cmd指令(16进制字符串)
     * @throws DecoderException
     * @throws IOException
     * @throws InterruptedException
     * @throws JSONException
     */
    @android.webkit.JavascriptInterface
    public synchronized void CallSendCmd(String data) throws DecoderException, IOException, InterruptedException, JSONException {
        JSONObject object = new JSONObject(data);
        Log.i(TAG, "建立socket udp连接");
        DatagramSocket dSocket = new DatagramSocket(object.getInt("port"));
        Log.i(TAG, "建立socket udp连接成功");
        InetAddress serverAddress = InetAddress.getByName(object.getString("host"));
        byte[] cmd = Hex.decodeHex(object.getString("cmd").replace(" ", "").toCharArray());
        DatagramPacket packon = new DatagramPacket(cmd, cmd.length, serverAddress, object.getInt("port"));
        Log.i(TAG, "发送指令====>" + new String(Hex.encodeHex(cmd)).toUpperCase());
        dSocket.send(packon);
        Log.i(TAG, "发送指令完成:" + new String(Hex.encodeHex(cmd)).toUpperCase());
        dSocket.close();
        Log.i(TAG, "连接关闭");
    }
}
