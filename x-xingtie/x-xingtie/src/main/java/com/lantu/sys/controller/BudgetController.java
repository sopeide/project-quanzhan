package com.lantu.sys.controller;

import com.lantu.sys.entity.Budget;
import com.lantu.sys.mapper.BudgetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzzzzz
 * @since 2023-11-15
 */
@RestController
public class BudgetController {

    @Autowired
    private BudgetMapper budgetMapper;

    @GetMapping("/budget")
    public List<Budget> query(){
        List<Budget> list = budgetMapper.selectList(null);
        System.out.println(list);
        return list;
    }
}
