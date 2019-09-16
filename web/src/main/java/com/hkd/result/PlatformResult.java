package com.hkd.result;

import com.hkd.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by huangkd on 2019/1/23.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlatformResult<T> implements Result {
    private static final long serialVersionUID = -6406922540005550779L;

    private int code;
    private String message;
    private T data;
    private String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public static PlatformResult success() {
        PlatformResult result = new PlatformResult();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static <T> PlatformResult<T> success(T data) {
        PlatformResult<T> result = new PlatformResult<>();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static <T> PlatformResult<T> failure(ResultCode resultCode) {
        PlatformResult<T> result = new PlatformResult<>();
        result.setResultCode(resultCode);
        return result;
    }

    public static <T> PlatformResult<T> failure(ResultCode resultCode, T data) {
        PlatformResult<T> result = new PlatformResult<>();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }


    private void setResultCode(ResultCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

}
