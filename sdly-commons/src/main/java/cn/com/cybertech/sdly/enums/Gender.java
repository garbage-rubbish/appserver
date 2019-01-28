package cn.com.cybertech.sdly.enums;

/**
 * Created by huangkd on 2019/1/23.
 */
public enum Gender {

    MALE(1, "男"),
    FEMALE(2, "女");

    private int code;
    private String value;


    Gender(int code, String value){
        this.code=code;
        this.value=value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }}
