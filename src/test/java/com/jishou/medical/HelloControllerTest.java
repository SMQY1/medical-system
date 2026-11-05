package com.jishou.medical;

import com.jishou.medical.common.Result;
import com.jishou.medical.controller.HelloController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HelloControllerTest {

    private final HelloController controller = new HelloController();

    @Test
    void helloShouldReturnGreeting() {
        Result<String> result = controller.hello("董洋");
        assertEquals(200, result.getCode());
        assertTrue(result.getData().contains("董洋"));
    }

    @Test
    void helloShouldUseDefaultNameWhenBlank() {
        Result<String> result = controller.hello("  ");
        assertEquals(200, result.getCode());
        assertTrue(result.getData().contains("医疗系统"));
    }

    @Test
    void healthShouldReturnUp() {
        assertEquals("UP", controller.health().getData());
    }
}
