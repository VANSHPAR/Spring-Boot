package Project.springboot.config;

import Project.springboot.Controller.AccountController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private static final String[] WHITELIST = {
            "/",
            "/login",
            "/register",
            "/db-console/**",
            "/css/**",
            "/fonts/**",
            "/images/**",
            "/js/**",
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       
        http
        .csrf(csrf -> csrf.disable()) // Disable CSRF for H2 console
        .headers(headers -> headers.frameOptions().disable()) // Allow H2 Console Frames
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(WHITELIST).permitAll() // Whitelist endpoints
            .anyRequest().authenticated() // All other requests require authentication
        );

        return http.build();
    }

}