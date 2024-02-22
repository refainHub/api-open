package cn.sfcoder.apiinterfacesdk;

import cn.sfcoder.apiinterfacesdk.client.ApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: refain
 * @Description:
 * @Date: 2024/2/17 22:39
 * @Version: 1.0
 */
@Configuration
@ConfigurationProperties("api.client")
@Data
@ComponentScan
public class ApiClientConfig {
    private String accessKey;
    private String secretKey;

    @Bean
    public ApiClient yuApiClient() {
        return new ApiClient(accessKey, secretKey);
    }

}
