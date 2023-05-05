package com.zerobase.zerobasestudy.util.validation;

public class StaticUtils {

    /** Double 자료형 검증 */
    public static Double isValidDouble(String arg) {
        try{
            Double value = Double.parseDouble(arg);
            return value;
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("잘못된 필드 = " + e);
        }
    }

    /** Long 자료형 검증 */
    public static Long isValidLong(String arg) {
        try{
            Long value = Long.parseLong(arg);
            return value;
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("잘못된 필드 = " + e);
        }
    }

    /** int 자료형 검증 */
    public static Integer isValidInt(String arg) {
        try{
            Integer value = Integer.parseInt(arg);
            return value;
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("잘못된 필드 = " + e);
        }
    }
}
