package com.chenw.user.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName: UserApplication
 * @Description: UserApplication
 * @Author ChenXiaoW
 * @Date 2023/01/05 - 21:50
 */
@EnableDiscoveryClient
@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);
        System.out.println("user service start success");
    }
}
