package com.jishou.medical.controller;

import com.jishou.medical.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public Result<String> hello(@RequestParam(required = false) String name) {
        String target = (name == null || name.isBlank()) ? "医疗系统" : name.trim();
        return Result.success("Hello, " + target + "，医疗系统初始化成功");
    }

    @GetMapping("/health")
    public Result<String> health() {
        return Result.success("UP");
    }
}
