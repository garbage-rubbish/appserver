package cn.com.cybertech.sdly.test;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;


/**
 * Created by huangkd on 2019/1/24.
 */
@Data
public class Demo1 {

    @NotBlank
    private String name;

}
