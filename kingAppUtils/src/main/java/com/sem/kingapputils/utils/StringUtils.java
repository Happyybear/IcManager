package com.sem.kingapputils.utils;

import android.text.TextUtils;

import java.util.Locale;

/**
 * @ProjectName: VQCStation
 * @Package: com.sem.kingapputils.utils
 * @ClassName: StringUtils
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/5/31 15:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/31 15:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class StringUtils {
    /**
     * 获取路径最后一个
     * @param path path
     * @return
     */
    public static String getLastPath(String path){
        if (!TextUtils.isEmpty(path)){
            String[] split = path.split("/");
            if (!KArrayUtils.isEmpty(split)){
                return split[split.length-1];
            }
        }
        return "";
    }

    public static Boolean isEmpty(String str){
        return TextUtils.isEmpty(str);
    }

    public static String formatInt(int intValue){
        return String.format(Locale.CHINA,"%d",intValue);
    }

    public static String formatLong(long intValue){
        return String.format(Locale.CHINA,"%d",intValue);
    }

    public static String formatFloat(Float intValue){
        return String.format(Locale.CHINA,"%f",intValue);
    }

    public static String formatFloat(Float intValue, String radix){
        return String.format(Locale.CHINA,radix,intValue);
    }

    public static String formatDouble(Double intValue, String radix){
        return String.format(Locale.CHINA,radix,intValue);
    }

    /**
     * 格式化数字字符多余的0
     * @param value
     * @return
     */
    public static String formatFloatString(String value){
        String result = value;
        if (RegexUtils.checkNumber(result)) {
            float valueFloat = Float.parseFloat(result);
            if(((int)valueFloat) != 0){
                result = result.replaceFirst("^0*", "");
            }else {
                result = result.replaceFirst("^0*", "0");
            }
            if(valueFloat - (int)valueFloat != 0.f){
                result = result.replaceAll("0*$", "");
            }else {
                result = result.replaceAll(".0*$", "");
            }
        }
        return result;
    }

    public static String numberValue(String s, String replace){
        if(!RegexUtils.checkNumber(s)){
            return replace;
        }
        return s;
    }
    /** 格式化数字字符 去掉小数点后多余的0
     * @param string value
     * @return formatValue
     */
    public static String formatZero(String string){
        if (!RegexUtils.checkNumber(string)){
            return string;
        }
        String s = string;
        char sign = 0;
        if (s.charAt(0) == '-' || s.charAt(0) == '+'){
            sign = string.charAt(0);
            string = string.substring(1);
        }

        if (string.indexOf(".")>0){
            //去掉后面无用的零
            s = string.replaceAll("0+$", "");
            //如小数点后面全是零则去掉小数点
            s = s.replaceAll("[.]$", "");
        }
        s = s.replaceAll("^0+", "");
        s = s.replaceAll("^\\.", "0.");

        if (StringUtils.isEmpty(s)){
            // 处理字符串"0",会被格式化位""的情况
            s = "0";
        }

        if (sign > 0){
            s = String.format("%c%s",sign,s);
        }
        return s;
    }

    /**
     * 格式化字符串，去掉所有的space
     * @param s 输入
     * @return
     */
    public static String formatSpace(String s){
        if (isEmpty(s)) {
            return s;
        }
        return s.replaceAll("\\s*", "");
    }

    public static String[] split(String s, String rex){
        if (isEmpty(s)) {
            return null;
        }
        return  s.split(rex);
    }

}
