package dev.bug.bugblog.config;

import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return
                http
                .formLogin()
                .and()
                .authorizeExchange()
                .pathMatchers("/admin/**").hasRole("ADMIN")
                .anyExchange().permitAll()
                .and()
                .build();
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        User.UserBuilder builder = User.withDefaultPasswordEncoder();
        UserDetails admin = builder
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();
        return new MapReactiveUserDetailsService(admin);
    }
}
