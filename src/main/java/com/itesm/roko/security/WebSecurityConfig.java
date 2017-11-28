package com.itesm.roko.security;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity(debug = true)
@Configuration
@ConditionalOnProperty(prefix = "auth0", value = "secret", matchIfMissing = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Value("${https://roko.auth0.com/}")
    private String issuer;

    @Value("${https://roko.auth0.com/userinfo}")
    private String audience;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("Configuring for Jwt token with iss {} & aud {}", issuer, audience);

        JwtWebSecurityConfigurer.forRS256(audience, issuer)
                .configure(http)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/v1/**").permitAll();
                //.antMatchers(HttpMethod.GET,"/v1/partidos").fullyAuthenticated();
    }
}