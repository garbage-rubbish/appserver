package com.hkd.reflect;

import org.aopalliance.aop.Advice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class App {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method test = Bp.class.getDeclaredMethod("test", Integer.class);
        Bp bp=new Bp("ss");
        Class<? extends Bp> aClass = bp.getClass();
        Type[] genericInterfaces = bp.getClass().getGenericInterfaces();
        test.invoke(bp,1);
    }
}

class Bp implements Advice {
    private String s;

    public Bp(String s){
        this.s=s;

    }

    public void test(Integer integer){
        System.out.println(s+integer);
    }
}