package com.lantu.sys.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author zzzzzz
 * @since 2023-11-23
 */
@ApiModel(value = "Test1对象", description = "")
public class Test1 implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Test1{" +
            "name=" + name +
            ", value=" + value +
        "}";
    }
}
