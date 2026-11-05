package com.unnathi.cicd_app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HelloControllerTest {

    @Test
    void homeShouldContainHello() {
        HelloController controller = new HelloController();
        assertTrue(controller.home().contains("Hello"));
    }
}
