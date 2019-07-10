package com.sheygam.springauthenticationauthorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity()
public class SecurityConfig {

    @Configuration
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            System.out.println(http.authorizeRequests());
            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/user**").authenticated()
                    .and()
                    .httpBasic();
        }

    }

    @Bean
    UserDetailsService authentication(){
        UserDetails john = User.builder()
                .username("john@mail.com")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails jack = User.builder()
                .username("jack@mail.com")
                .password(passwordEncoder().encode("StrongPassword"))
                .roles("USER","ADMIN")
                .build();
        return new InMemoryUserDetailsManager(john,jack);
    }

    //PasswordEncoder needs for encode passwords
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
