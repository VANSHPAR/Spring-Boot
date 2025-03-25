package Project.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       

        http.authorizeHttpRequests(auth -> auth.requestMatchers(WHITELIST)
        .permitAll()
        .anyRequest()
        .authenticated() );



        http.formLogin(formLogin -> formLogin.loginPage("/login")
        .loginProcessingUrl("/login")
        .usernameParameter("email")
        .passwordParameter("password")
        .defaultSuccessUrl("/",true)
        .failureUrl("/login?error")
        .permitAll()    );
       
        http.logout(logout -> logout.logoutUrl("/logout")
        .logoutSuccessUrl("/logout?success"));

        
       
       

        

        http.csrf(csrf -> csrf.disable());
        http.headers(headers -> headers.disable());


        return http.build();
    }
}