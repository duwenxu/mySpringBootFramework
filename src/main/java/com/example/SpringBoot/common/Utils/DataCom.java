package com.example.SpringBoot.common.Utils;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.regex.Pattern;

public class DataCom {
    public static double formatDouble(double d, int pointLen) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        //去掉NumberFormat千分位逗号   避免出现56,589.2176导致无法转为double
        nf.setGroupingUsed(false);
        // 保留3位小数
        nf.setMaximumFractionDigits(pointLen);
        // 如果不需要四舍五入，可以使用RoundingMode.DOWN
        nf.setRoundingMode(RoundingMode.HALF_UP);
        return Double.parseDouble(nf.format(d));
    }

    // 判断整数 Number.isInteger();
    public static boolean isInteger(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    // 判断浮点数（double和float）
    public static boolean isDouble(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$");
        return pattern.matcher(str).matches();
    }

    // 获取小数位数
    public static int getPointLen(String a) {
        String s = String.valueOf(a);
        String[] pointStr = s.split("\\.");
        return pointStr[1].toCharArray().length;
    }

    // 判断是否为科学记数法
    public static boolean isScientNota(String a) {
        char[] arr = a.toCharArray();
        for (char c : arr) {
            if (c == 'E') {
                return true;
            }
        }
        return false;
    }

    // 对于位数较多的double型数据避免使用科学记数法
    // BigDecimal bd = new BigDecimal(a);
    // data=bd.toPlainString(); 科学计数转为普通数


}
