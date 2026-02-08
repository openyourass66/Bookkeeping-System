package com.zhang;

import com.zhang.Utils.PasswordUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookkeepingDemoApplicationTests {
    @Test
    void passwordTest() {
        String password = "123456";
        System.out.println(PasswordUtil.encodePassword(password));
    }
}
