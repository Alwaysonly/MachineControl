package com.huige.mc.utils;

import net.wimpi.modbus.util.BitVector;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import static com.huige.mc.utils.Crc16Util.Integer2Hex;

/**
 * @Author Z.xichao
 * @Create 2018-6-22
 * @Comments
 */
public class CommandGenerate {

    public static final String FLASH = "10";
    public static final String _0F = "0F";
    public static final String READ = "01";
    public static final String WRITE_SINGLE = "05";
    public static final String WRITE_MULTIPLE = "15";

    private static final String DEVICE_ADDRESS = "FE";
    private static final String COMMAND_SINGLE_ON = "FF00";
    private static final String COMMAND_SINGLE_OFF = "0000";

    /**
     * 生成单个线圈控制命令
     *
     * @param index     继电器地址
     * @param operation 操作（开|关）
     * @return {@link Byte} 命令字节
     */
    public static byte[] GenerateWtiteSingle(int index, boolean operation) throws DecoderException {
        String command = DEVICE_ADDRESS;
        command += WRITE_SINGLE + "00" + Integer2Hex(index);
        command += operation ? COMMAND_SINGLE_ON : COMMAND_SINGLE_OFF;
        return processingResult(command);
    }

    /**
     * 生成多个线圈控制命令
     *
     * @param allOpen 全开或者全关 如果为null 按照 index 执行
     * @return {@link Byte} 命令字节
     */
    public static byte[] GenerateWriteMultiple(Boolean allOpen) throws DecoderException {
        BitVector index = new BitVector(16);
        if (allOpen) {
            index.setBytes(new byte[]{(byte) 0xff, (byte) 0xff});
        }
        return GenerateWriteMultiple(index);
    }

    public static byte[] GenerateWriteMultiple(BitVector index) throws DecoderException {
        if (index == null) index = new BitVector(16);
        String command = DEVICE_ADDRESS;
        command += _0F;
        command += "0000001002";
        command += new String(Hex.encodeHex(index.getBytes()));
        return processingResult(command);
    }

    /**
     * 生成单个闪操作控制命令
     *
     * @param index     继电器地址
     * @param flashSign 闪操作标志（闪闭|闪开）
     * @param time      间隔时长
     * @return {@link Byte} 命令字节
     */
    public static byte[] GenerateFlashSingle(int index, boolean flashSign, int time) throws DecoderException {
        String command = DEVICE_ADDRESS;
        command += FLASH + "00" + Integer2Hex(++index * 5 - 2);
        command += "00020400";
        command += flashSign ? "02" : "04";
        command += "00" + Integer2Hex(time);
        return processingResult(command);
    }

    /**
     * 增加校验位
     * @param command
     * @return
     * @throws DecoderException
     */
    private static byte[] processingResult(String command) throws DecoderException {
        command += Crc16Util.getCRC(Hex.decodeHex(command.toCharArray()));
        return Hex.decodeHex(command.toCharArray());
    }
}
