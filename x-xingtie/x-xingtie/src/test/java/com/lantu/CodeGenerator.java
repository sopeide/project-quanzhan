package com.lantu;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.awt.*;
import java.nio.charset.MalformedInputException;
import java.util.Collections;

/**
 * @Author itmei
 * @Date 2023/10/9 20:29
 * @description:
 * @Title: CodeGenerator
 * @Package com.lantu
 */
public class CodeGenerator {
    public static void main(String[] args) {
        String url = "jdbc:mysql:///cainiao";
        String username = "root";
        String password = "123456";
        String author = "zzzzzz";
        String outputDir = "C:\\Users\\xiaoy\\Desktop\\菜鸟\\x-xingtie\\x-xingtie\\src\\main\\java";
        String basePackage = "com.lantu";
        String moduleName = "sys";
        String mapperLocation = "C:\\Users\\xiaoy\\Desktop\\菜鸟\\x-xingtie\\x-xingtie\\src\\main\\resources\\mapper\\" + moduleName;
        String tableName = "x_user,x_menu,x_role,x_role_menu,x_user_role,x_biaodan1,x_budget,test1";
        String tablePrefix = "x_";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            //.fileOverride() // 覆盖已生成文件
                            .outputDir(outputDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(basePackage) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableName) // 设置需要生成的表名
                            .addTablePrefix(tablePrefix); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
