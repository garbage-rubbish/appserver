package cn.com.cybertech.sdly.model.other;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by huangkd on 2019/1/25.
 */
@Data
public class LoginUser {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
