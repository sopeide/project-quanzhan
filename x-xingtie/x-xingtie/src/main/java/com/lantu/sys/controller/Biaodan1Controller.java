package com.lantu.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lantu.common.vo.Result;
import com.lantu.sys.entity.Biaodan1;
import com.lantu.sys.entity.User;
import com.lantu.sys.service.IBiaodan1Service;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzzzzz
 * @since 2023-11-06
 */
@RestController
@RequestMapping("/biaodan1")
public class Biaodan1Controller {
    @Autowired
    private IBiaodan1Service biaodan1Service;
    //private Result<List<Biaodan1>> 查询成功;
    //
    //@GetMapping("/all")
    //public Result<List<Biaodan1>> getAllBiaodan1() {
    //    List<Biaodan1> list = biaodan1Service.list();
    //    //System.out.println(list);
    //    return 查询成功;
    //}

    @GetMapping("/pist")
    public Result<Map<String,Object>> getBiaodan1Pist(@RequestParam(value = "biaodan1Name", required = false) String biaodan1Name,
                                                      @RequestParam(value = "biaodan1Value", required = false) String biaodan1Value,
                                                      @RequestParam(value = "pageNo",defaultValue = "1"  ) Long pageNo, //有多少页
                                                      @RequestParam(value = "pageSize" ,defaultValue = "10") Long pageSize) { //每页有多少条
        LambdaQueryWrapper<Biaodan1> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(biaodan1Name), Biaodan1::getBiaodan1Name,biaodan1Name);
        wrapper.eq(StringUtils.hasLength(biaodan1Value), Biaodan1::getBiaodan1Value, biaodan1Value);
        wrapper.orderByDesc(Biaodan1::getBiaodan1Id);

        Page<Biaodan1> page = new Page<>(pageNo, pageSize);
        biaodan1Service.page(page, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal()); // 总数
        data.put("rows", page.getRecords()); // 查询集

        return Result.success(data);
    }
}
