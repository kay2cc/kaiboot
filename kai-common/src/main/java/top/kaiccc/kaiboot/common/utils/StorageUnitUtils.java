package top.kaiccc.kaiboot.common.utils;

/**
 * 存储单位 转换工具栏
 */
public class StorageUnitUtils {
    private static final long UNIT = 1024;

    public static String getPrintSize(long size) {
        //如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < UNIT) {
            return size + "B";
        } else {
            size = size / UNIT;
        }
        //如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        //因为还没有到达要使用另一个单位的时候
        //接下去以此类推
        if (size < UNIT) {
            return size + "KB";
        } else {
            size = size / UNIT;
        }
        if (size < UNIT) {
            //因为如果以MB为单位的话，要保留最后1位小数，
            //因此，把此数乘以100之后再取余
            size = size * 100;
            return size / 100 + "." + size % 100 + "MB";
        } else {
            //否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / UNIT;
            return size / 100 + "." + size % 100 + "GB";
        }
    }
}
