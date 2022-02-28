package com.mavericsystems.customer.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerRetryClientConfig{

    @Bean
    public Retryer clientServiceRetryer(){
        return new FeignClientRetryer();
    }
//    @Bean
//    public Retryer retryer() {
//        return new Retryer.Default();
//    }

}
