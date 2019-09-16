package com.hkd.constants;

/**
 * Created by huangkd on 2019/1/24.
 */
public class Constants {

    public static final String TOKEN_HEADER="Authorization";
    public static final String TOKEN_SECRET="18xn80lccbfdhz";
    public static final int TOKEN_EXPIRATION_TIME=30;//分钟
    public static final String TOKEN_CLAIMS_USERNAME="username";
    public static final String TOKEN_CLAIMS_CREAT_TIME="createTokenTime";
    public static final String TOKEN_CLAIMS_ROLES="roles";

    public static final String REDIS_LOGIN_USER_KEY = "login_user";
    public static final String TOKEN_EXCEPTION_HEADER="TOKEN_EXCEPTION";
}
