package cn.com.cybertech.sdly.enums;

/**
 * Created by huangkd on 2019/1/23.
 */
public enum ResultCode {

    //操作成功
    SUCCESS(0, "ok"),

    PARAM_IS_INVALID(10001, "参数无效"),

    USERNAME_PASSWORD_ERROR(20001,"用户名或密码错误"),
    USERNAME_NOT_EXIST(20002,"用户名不存在"),
    UN_AUTH_USER(20003,"用户未认证，拒绝访问"),
    PERMISSIONS_INSUFFICIENT(20004,"用户权限不足"),

    TOKEN_EXPIRED(20007,"token 过期"),
    PARSE_TOKEN_ERROR(20006,"token解析失败"),

    NOT_SUPPORT_REQ_METHOD(40001,"不支持的http方法"),

    SYSTEM_INNER_ERROR(50001, "内部服务错误"), ADD_RECORD_FAIL(40002, "添加数据失败"), UPLOAD_FILE_FAIL(40003, "上传文件错误");
    private int code;
    private String message;


    ResultCode(int code,String message){
        this.code=code;
        this.message=message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
