package com.zhang.Utils;

public class CurrentHolder {
    private static final ThreadLocal<Long> CURRENT_LOCAL = new ThreadLocal<>();
    public static void setCurrentId(Long empId){
        CURRENT_LOCAL.set(empId);
    }
    public static Long getCurrentId(){
        return CURRENT_LOCAL.get();
    }
    public static void remove(){
        CURRENT_LOCAL.remove();
    }
}
