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
 * @since 2023-11-15
 */
@ApiModel(value = "Budget对象", description = "")
public class Budget implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dimName;

    private String dimZhName;

    private String max;

    private String budget;

    private String expense;

    public String getDimName() {
        return dimName;
    }

    public void setDimName(String dimName) {
        this.dimName = dimName;
    }
    public String getDimZhName() {
        return dimZhName;
    }

    public void setDimZhName(String dimZhName) {
        this.dimZhName = dimZhName;
    }
    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }
    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }
    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    @Override
    public String toString() {
        return "Budget{" +
            "dimName=" + dimName +
            ", dimZhName=" + dimZhName +
            ", max=" + max +
            ", budget=" + budget +
            ", expense=" + expense +
        "}";
    }
}
