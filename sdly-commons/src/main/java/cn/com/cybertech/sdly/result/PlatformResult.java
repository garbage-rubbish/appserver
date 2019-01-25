package cn.com.cybertech.sdly.result;

import cn.com.cybertech.sdly.enums.ResultCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class PlatformResult implements Result {
    private static final long serialVersionUID = -6406922540005550779L;

    private int code;
    private String message;
    private Object data;

    public static PlatformResult success() {
        PlatformResult result = new PlatformResult();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static PlatformResult success(Object data) {
        PlatformResult result = new PlatformResult();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static PlatformResult failure(ResultCode resultCode) {
        PlatformResult result = new PlatformResult();
        result.setResultCode(resultCode);
        return result;
    }

    public static PlatformResult failure(ResultCode resultCode, Object data) {
        PlatformResult result = new PlatformResult();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }


    private void setResultCode(ResultCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

}
