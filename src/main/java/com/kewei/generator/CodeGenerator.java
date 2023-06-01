package com.kewei.generator;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * 代码生成器
 *
 * @author: 早起的年轻人
 * @since: 2023/3/6 15:28
 */
public class CodeGenerator {
    public static void main(String[] args) {

        //jdbc:mysql://localhost:3306/plc_tcp_db?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=GMT%2B8


        System.out.println("dddddd" + System.getProperty("user.dir") + "\\src\\main\\java\\com\\kewei\\generator");

        // 数据源配置
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/plc_tcp_db?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=GMT%2B8",
                "root", "12345678")
                .globalConfig(builder -> {
                    builder.author("hai.qiu")        // 设置作者
                            .enableSwagger()        // 开启 swagger 模式 默认值:false
                            .disableOpenDir()       // 禁止打开输出目录 默认值:true
                            .commentDate("yyyy-MM-dd") // 注释日期
                            .dateType(DateType.ONLY_DATE)   //定义生成的实体类中日期类型 DateType.ONLY_DATE 默认值: DateType.TIME_PACK
                            .outputDir(System.getProperty("user.dir") + "\\src\\main\\java\\com\\kewei\\generator")// 指定输出目录
                    ;
                })
                .packageConfig(builder -> {
                    //builder.parent("com.keiwei.plcsys")
                    //.moduleName("plcsys")
                    builder.entity("entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .xml("mapper.xml")
                            .controller("controller");
                }).strategyConfig(builder -> {
            builder.addInclude("plc_tcp_info") // 设置需要生成的表名
                    //.addTablePrefix("t_", "c_") // 设置过滤表前缀
                    .mapperBuilder()
                    .superClass(BaseMapper.class)
                    .enableBaseResultMap()
                    .enableBaseColumnList()
                    .formatMapperFileName("%sMapper")
                    .formatXmlFileName("%sXml")
                    .enableFileOverride();//删除已存在的
        }).templateConfig(builder -> {
            builder.disable(TemplateType.ENTITY)
                    .entity("/templates/entity.java")
                    .service("/templates/service.java")
                    .serviceImpl("/templates/serviceImpl.java")
                    .mapper("/templates/mapper.java")
                    .controller("/templates/controller.java")
                    .xml("/templates/mapper.xml")
            ;
        })
                .injectionConfig(builder -> {

                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }
}
