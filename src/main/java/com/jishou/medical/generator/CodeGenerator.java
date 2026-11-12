package com.jishou.medical.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

/**
 * MyBatis-Plus 代码生成器：一键生成 5 张表的 entity / mapper / service / serviceImpl。
 * <p>
 * 使用方式：在 IDEA 中直接运行本类的 main 方法，生成结果输出到 src/main/java。
 * 生成策略：主键自增、开启字段注解、逻辑删除字段 deleted、自动填充 create_time / update_time，
 * 不生成 Controller 与 XML（Controller 由实验按业务手工编写，BaseMapper 已满足单表 CRUD）。
 */
public class CodeGenerator {

    private static final String URL =
            "jdbc:mysql://localhost:3306/medical_db?useUnicode=true&characterEncoding=utf8"
                    + "&serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Aa*123456";

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");

        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                .globalConfig(builder -> builder
                        .author("董洋")
                        .outputDir(projectPath + "/src/main/java")
                        .dateType(DateType.TIME_PACK)
                        .commentDate("yyyy-MM-dd")
                        .disableOpenDir())
                .packageConfig(builder -> builder
                        .parent("com.jishou.medical")
                        .entity("entity")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/src/main/resources/mapper")))
                .strategyConfig(builder -> builder
                        .addInclude("department", "doctor", "patient", "registration", "prescription")
                        .addTablePrefix("t_")
                        // 实体策略
                        .entityBuilder()
                        .enableTableFieldAnnotation()
                        .logicDeleteColumnName("deleted")
                        .idType(com.baomidou.mybatisplus.annotation.IdType.AUTO)
                        .addTableFills(new Column("create_time", com.baomidou.mybatisplus.annotation.FieldFill.INSERT))
                        .addTableFills(new Column("update_time", com.baomidou.mybatisplus.annotation.FieldFill.INSERT_UPDATE))
                        .enableFileOverride()
                        // Mapper 策略
                        .mapperBuilder()
                        .enableBaseResultMap()
                        .enableFileOverride()
                        // Service 策略
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")
                        .enableFileOverride()
                        // 不生成 Controller
                        .controllerBuilder()
                        .disable()
                        .enableFileOverride())
                .templateEngine(new VelocityTemplateEngine())
                .execute();

        System.out.println("代码生成完成，输出目录：" + projectPath + "/src/main/java");
    }
}
