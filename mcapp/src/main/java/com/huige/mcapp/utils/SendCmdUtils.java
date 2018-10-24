package com.huige.mcapp.utils;

import android.util.Log;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendCmdUtils {

    private static final String TAG = SendCmdUtils.class.getSimpleName();

    /**
     * 继电线圈闪开 协议udp
     *
     * @param relayHost      继电主机地址
     * @param relayPort      继电主机端口号
     * @param index          线圈索引
     * @param relayFlashTime 闪开时间
     * @return
     */
    public static synchronized Boolean CallRelayWriteSingle(String relayHost, Integer relayPort, int index, Integer relayFlashTime) {
        DatagramSocket dSocket = null;
        try {
            byte[] cmd = CommandGenerate.GenerateFlashSingle(index, false, relayFlashTime);
            Log.i(TAG, "建立socket udp连接");
            dSocket = new DatagramSocket(relayPort);
            dSocket.setSoTimeout(1000);
            Log.i(TAG, "建立socket udp连接成功");
            InetAddress serverAddress = InetAddress.getByName(relayHost);
            byte[] refData = new byte[8];
            DatagramPacket ref = new DatagramPacket(refData, 8);
            DatagramPacket pack = new DatagramPacket(cmd, cmd.length, serverAddress, relayPort);
            Log.i(TAG, "发送指令====>" + new String(Hex.encodeHex(cmd)).toUpperCase());
            dSocket.send(pack);
            dSocket.receive(ref);
            Log.i(TAG, "发送指令完成:" + new String(Hex.encodeHex(ref.getData())).toUpperCase());
            dSocket.close();
            Log.i(TAG, "连接关闭");
//          return Crc16Util.ValidationCRC16(ref.getData());
            return StringUtils.isNoneBlank(new String(Hex.encodeHex(ref.getData())));
        } catch (DecoderException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (dSocket != null) {
                dSocket.close();
            }
        }

    }


    /**
     * 继电线圈全部闪开 协议udp
     *
     * @param relayHost      继电主机地址
     * @param relayPort      继电主机端口号
     * @param relayFlashTime 闪开时间
     * @return
     */
    public static synchronized Boolean CallRelayWriteAll(String relayHost, Integer relayPort, Integer relayFlashTime) {
        DatagramSocket dSocket = null;
        try {
            Log.i(TAG, "建立socket udp连接");
            dSocket = new DatagramSocket(relayPort);
            dSocket.setSoTimeout(1000);
            Log.i(TAG, "建立socket udp连接成功");
            InetAddress serverAddress = InetAddress.getByName(relayHost);
            byte[] cmdon = CommandGenerate.GenerateWriteMultiple(true);

            byte[] refOnData = new byte[8];
            DatagramPacket refOn = new DatagramPacket(refOnData, 8);

            DatagramPacket packon = new DatagramPacket(cmdon, cmdon.length, serverAddress, relayPort);
            Log.i(TAG, "发送指令====>" + new String(Hex.encodeHex(cmdon)).toUpperCase());
            dSocket.send(packon);
            dSocket.receive(refOn);
            Log.i(TAG, "发送指令完成:" + new String(Hex.encodeHex(refOn.getData())).toUpperCase());

            Thread.sleep(relayFlashTime * 100);

            byte[] cmdoff = CommandGenerate.GenerateWriteMultiple(false);

            byte[] refOffData = new byte[8];
            DatagramPacket refOff = new DatagramPacket(refOffData, 8);

            DatagramPacket packoff = new DatagramPacket(cmdoff, cmdoff.length, serverAddress, relayPort);
            Log.i(TAG, "发送指令====>" + new String(Hex.encodeHex(cmdoff)).toUpperCase());
            dSocket.send(packoff);
            dSocket.receive(refOff);
            Log.i(TAG, "发送指令完成:" + new String(Hex.encodeHex(refOff.getData())).toUpperCase());
            dSocket.close();
            Log.i(TAG, "连接关闭");
//          return Crc16Util.ValidationCRC16(refOn.getData()) && Crc16Util.ValidationCRC16(refOff.getData());
            return StringUtils.isNoneBlank(new String(Hex.encodeHex(refOn.getData()))) &&
                    StringUtils.isNoneBlank(new String(Hex.encodeHex(refOff.getData())));
        } catch (DecoderException | IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (dSocket != null) {
                dSocket.close();
            }
        }
    }

    /**
     * 发送16进制指令 协议udp
     *
     * @param rs232Host 协议主机地址
     * @param onCmd     发送指令
     * @param rs232Port 协议主机端口号
     * @throws DecoderException
     * @throws IOException
     */
    public static synchronized void CallSendCmd(String rs232Host, String onCmd, Integer rs232Port) throws DecoderException, IOException {
        Log.i(TAG, "建立socket udp连接");
        DatagramSocket dSocket = new DatagramSocket(rs232Port);
        Log.i(TAG, "建立socket udp连接成功");
        InetAddress serverAddress = InetAddress.getByName(rs232Host);
        byte[] cmd = Hex.decodeHex(onCmd.replace(" ", "").toCharArray());
        DatagramPacket packon = new DatagramPacket(cmd, cmd.length, serverAddress, rs232Port);
        Log.i(TAG, "发送指令====>" + new String(Hex.encodeHex(cmd)).toUpperCase());
        dSocket.send(packon);
        Log.i(TAG, "发送指令完成:" + new String(Hex.encodeHex(cmd)).toUpperCase());
        dSocket.close();
        Log.i(TAG, "连接关闭");
    }
}
