package cn.sfcoder.apiinterface;


import cn.sfcoder.apiinterfacesdk.client.ApiClient;
import cn.sfcoder.apiinterfacesdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ApiInterfaceApplicationTests {

    @Resource
    private ApiClient yuApiClient;

    @Test
    void contextLoads() {
        String result = yuApiClient.getNameByGet("yupi");
        User user = new User();
        user.setUsername("yupi");
        String usernameByPost = yuApiClient.getUsernameByPost(user);
        System.out.println(result);
        System.out.println(usernameByPost);
    }

}
