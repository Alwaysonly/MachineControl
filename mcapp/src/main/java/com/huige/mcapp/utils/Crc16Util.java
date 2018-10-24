package com.huige.mcapp.utils;


import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * @Author Z.xichao
 * @Create 2018-6-22
 * @Comments
 */
public class Crc16Util {
    /**
     * 计算CRC16校验码
     *
     * @param bytes 字节数组
     * @return {@link String} 校验码
     */
    public static String getCRC(byte[] bytes) {
        int crc = 0x0000ffff;
        for (int i = 0; i < bytes.length; i++) {
            crc ^= ((int) bytes[i] & 0x000000ff);
            for (int j = 0; j < 8; j++) {
                if ((crc & 0x00000001) != 0) {
                    crc >>= 1;
                    crc ^= 0x0000a001;
                } else {
                    crc >>= 1;
                }
            }
        }
        //高低位互换，输出符合相关工具对Modbus CRC16的运算
        crc = ((crc & 0xff00) >> 8) | ((crc & 0x00ff) << 8);
        return String.format("%04X", crc);
    }


    public static Boolean ValidationCRC16(String data) {
        String validation = data.substring(data.length() - 4);
        String original = data.substring(0, data.length() - 4);
        try {
            return getCRC(Hex.decodeHex(original.toCharArray())).equals(validation);
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean ValidationCRC16(byte[] b) {
        String data = new String(Hex.encodeHex(b)).toUpperCase();
        String validation = data.substring(data.length() - 4);
        String original = data.substring(0, data.length() - 4);
        try {
            String valString = getCRC(Hex.decodeHex(original.toCharArray()));
            return valString.equals(validation);
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * int转16进制文本
     *
     * @param num
     * @return
     */
    public static String Integer2Hex(int num) {
        return String.format("%02x", num);
    }
}
