package Project.springboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import Project.springboot.util.constants.Privillages;


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
            "/posts/**"
           
    };
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       
        http.authorizeHttpRequests(auth -> auth.requestMatchers(WHITELIST)
        .permitAll() 
       .requestMatchers("/profile/**").authenticated()
       .requestMatchers("/admin/**").hasRole("ADMIN")
       .requestMatchers("/editor/**").hasAnyRole("ADMIN","EDITOR")
       .requestMatchers("/test").hasAuthority(Privillages.ACCESS_ADMIN_PANEL.getprivillage())
        );


        http.formLogin(formLogin -> formLogin.loginPage("/login")
        .loginProcessingUrl("/login")
        .usernameParameter("email")
        .passwordParameter("password")
        .defaultSuccessUrl("/",true)
        .failureUrl("/login?error")
        .permitAll()    );
       
        http.logout(logout -> logout.logoutUrl("/logout")
        .logoutSuccessUrl("/"));

      
       
       
        http.httpBasic(Customizer.withDefaults());
    

        

        http.csrf(csrf -> csrf.disable());
        http.headers(headers -> headers.disable());


        return http.build();
    }
}