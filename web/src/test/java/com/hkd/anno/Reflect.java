package com.hkd.anno;

import com.hkd.model.po.TpUser;
import org.assertj.core.util.Lists;

import java.util.List;

public class Reflect {
    public static void main(String[] args) {
        Reflect reflect=new Reflect();
        List<TpUser> users = reflect.getUsers();

    }


    @Trans
    public List<TpUser> getUsers(){
        TpUser tpUser=new TpUser();
        tpUser.setId(1);
        tpUser.setMjjh("123451");
        TpUser tpUser1=new TpUser();
        tpUser.setId(2);
        tpUser.setMjjh("123456");
        TpUser tpUser2=new TpUser();
        tpUser.setId(3);
        tpUser.setMjjh("123451");
        return Lists.newArrayList(tpUser,tpUser1,tpUser2);
    }
}


