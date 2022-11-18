package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
//                .headers().frameOptions().disable()
//                .and()
                .authorizeRequests()
                .antMatchers("/","/auth/**","/post/search/**").permitAll();
//                .antMatchers("/api/v1/**").hasRole(Role.
//                        USER.name())
//                .anyRequest().authenticated()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")
//                .and()
//                .oauth2Login()
//                .userInfoEndpoint();
//                .userService(customOAuth2UserService);

        return http.build();
//                .formLogin()
//                .loginPage("/members/login")
//                .defaultSuccessUrl("/")
//                .usernameParameter("email")
//                .failureUrl("/members/login/error")
//
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
//                .logoutSuccessUrl("/");


    }
}
