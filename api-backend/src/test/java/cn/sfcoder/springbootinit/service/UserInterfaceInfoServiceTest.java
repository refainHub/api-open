package cn.sfcoder.springbootinit.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: refain
 * @Description:
 * @Date: 2024/2/20 22:00
 * @Version: 1.0
 */
@SpringBootTest
class UserInterfaceInfoServiceTest {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;
    @Test
    void invokeCount() {
        boolean res= userInterfaceInfoService.invokeCount(1L,1L);
        Assertions.assertTrue(res);
    }
}