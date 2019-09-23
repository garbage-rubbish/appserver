package com.hkd.test;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Item {
    @NotNull
    @Size(min = 2,max = 6)
    private String name;
    @NotNull
    @Range(min = 1,max = 200)
    private Long id;
    @Valid
    private Props prop;
}
