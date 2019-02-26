package cn.com.cybertech.sdly.enums;

/**
 * Created by huangkd on 2019/2/26.
 */
public enum  GenderEnum implements BaseCodeValueEnum {
    MALE(1,"男"),FEMALE(2,"女");
    private int code;
    private String desc;

    GenderEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
