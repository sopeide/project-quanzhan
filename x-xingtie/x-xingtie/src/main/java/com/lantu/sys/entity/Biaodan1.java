package com.lantu.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 *
 * </p>
 *
 * @author zzzzzz
 * @since 2023-11-06
 */
@TableName("x_biaodan1")
@ApiModel(value = "Biaodan1对象", description = "")
public class Biaodan1 implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer biaodan1Id;

    private String biaodan1Name;

    private Double biaodan1Value;

    public Integer getBiaodan1Id() {
        return biaodan1Id;
    }

    public void setBiaodan1Id(Integer biaodan1Id) {
        this.biaodan1Id = biaodan1Id;
    }
    public String getBiaodan1Name() {
        return biaodan1Name;
    }

    public void setBiaodan1Name(String biaodan1Name) {
        this.biaodan1Name = biaodan1Name;
    }
    public Double getBiaodan1Value() {
        return biaodan1Value;
    }

    public void setBiaodan1Value(Double biaodan1Value) {
        this.biaodan1Value = biaodan1Value;
    }

    @Override
    public String toString() {
        return "Biaodan1{" +
            "biaodan1Id=" + biaodan1Id +
            ", biaodan1Name=" + biaodan1Name +
            ", biaodan1Value=" + biaodan1Value +
        "}";
    }
}
