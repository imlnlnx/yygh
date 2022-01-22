package com.ln.yygh.hosp;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

import java.util.Random;

/**
 * @Description:
 * @Author: Euphratica
 * @Date: 2022/01/22
 */
public class Demo {

    @Test
    public void testException() {
        try {
            int i = 1/0;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            System.out.println("111 " + ex.getLocalizedMessage() + " 111");
        }
    }

    @RepeatedTest(10)
    public void test() {
        String str = System.currentTimeMillis() + new Random().nextInt(1000) + "";
        System.out.println(DigestUtils.md5DigestAsHex(str.getBytes()));
    }
}
