package com.projeto.loja.projeto.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.*;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
       return httpSecurity
               .csrf(AbstractHttpConfigurer::disable)
               .headers(headers -> headers
                       .cacheControl(HeadersConfigurer.CacheControlConfig::disable)
                       .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
               .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
               .authorizeHttpRequests(authorize -> {
                   authorize.requestMatchers("/funcionarios/**").hasRole("ADMIN");
                   authorize.anyRequest().permitAll();
               })
               .build();
    }
}
