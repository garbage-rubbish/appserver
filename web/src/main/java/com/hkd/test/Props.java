package com.hkd.test;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Props {
    @Range( min = 200,max = 400)
    @NotNull
    private Long id;
    @NotNull
    @Size(min = 3,max = 5)
    private String name;

}
