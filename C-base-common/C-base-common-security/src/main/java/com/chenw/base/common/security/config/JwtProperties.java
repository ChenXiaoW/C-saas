package com.chenw.base.common.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName: JwtConfig
 * @Description: JwtConfig
 * @Author ChenXiaoW
 * @Date 2023/02/01 - 21:18
 */

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private Long expiration = 60*60*24*7L;

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public Long getExpiration() {
        return expiration;
    }
}
