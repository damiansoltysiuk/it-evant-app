package com.sda.iteventapp.security.jwt;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class JwtProperties {
    private String secretKey = "secret";
    private long validityInMilliseconds = 3600000;
}
