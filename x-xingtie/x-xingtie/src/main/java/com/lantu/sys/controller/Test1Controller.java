package com.lantu.sys.controller;

import com.lantu.sys.entity.Budget;
import com.lantu.sys.entity.Test1;
import com.lantu.sys.mapper.BudgetMapper;
import com.lantu.sys.mapper.Test1Mapper;
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
 *
 * @since 2023-11-23
 */
@RestController
public class Test1Controller {

    @Autowired
    private Test1Mapper test1Mapper;

    @GetMapping("/test1")
    public List<Test1> query(){
        List<Test1> list = test1Mapper.selectList(null);
        System.out.println(list);
        return list;
    }
}
