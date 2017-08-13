package com.wuspace.util;

import java.net.InetAddress;

/**
 * Created by wu_chao on 17-5-16.
 * <p>
 * 生成 32 位 UUID 序列
 */
public class UUIDGenerator {

    private static final int IP;
    private static final int JVM = (int) (System.currentTimeMillis() >>> 8);
    private static short counter = (short) 0;

    public static int ipToInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
        }
        return result;
    }

    static {
        int ipadd;
        try {
            ipadd = ipToInt(InetAddress.getLocalHost().getAddress());
        } catch (Exception e) {
            ipadd = 0;
        }
        IP = ipadd;
    }

    public UUIDGenerator() {
    }

    public static int getIP() {
        return IP;
    }

    public static int getJVM() {
        return JVM;
    }

    public static short getCount() {
        synchronized (UUIDGenerator.class) {
            if (counter < 0)
                counter = 0;
            return counter++;
        }
    }

    public static short getHiTime() {
        return (short) (System.currentTimeMillis() >>> 32);
    }

    public static int getLoTime() {
        return (int) System.currentTimeMillis();
    }

    private final static String sep = "";

    public static String format(int intVal) {
        String formatted = Integer.toHexString(intVal);
        StringBuffer buf = new StringBuffer("00000000");
        buf.replace(8 - formatted.length(), 8, formatted);
        return buf.toString();
    }

    public static String format(short shortVal) {
        String formatted = Integer.toHexString(shortVal);
        StringBuffer buf = new StringBuffer("0000");
        buf.replace(4 - formatted.length(), 4, formatted);
        return buf.toString();
    }

    public static String generate() {
        return String.valueOf(new StringBuffer(36).append(format(getIP())).append(sep)
                .append(format(getJVM())).append(sep)
                .append(format(getHiTime())).append(sep)
                .append(format(getLoTime())).append(sep)
                .append(format(getCount())).toString());
    }
}
