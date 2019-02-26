package cn.com.cybertech.sdly.test;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by huangkd on 2019/1/24.
 */
@Data
public class Demo {

    /*@NotNull
    private Integer id;
    @Length(min = 1,max = 2)
    private String name;
    @NotBlank
    private String depart;

    @Valid
    @NotNull
    private Demo1 demo;*/

    private List<String> a;

}
