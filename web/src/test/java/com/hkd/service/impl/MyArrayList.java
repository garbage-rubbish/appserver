package com.hkd.service.impl;


import com.hkd.model.dto.UserInfo;
import io.reactivex.Flowable;

public class MyArrayList<E> implements Cloneable{

    private String name;

    private int age=10;

   UserInfo userInfo=new UserInfo();{
       userInfo.setId(11);
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Flowable.just("hello world").subscribe(System.out::println);
    }
}
