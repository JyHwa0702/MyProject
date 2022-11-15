package pro.fir;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pro.fir.service.MemberService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class config {

    @Autowired
    private final MemberService memberService;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .formLogin()
                .loginPage("/members/login")
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .failureUrl("/members/login/error")

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                .logoutSuccessUrl("/");

                http.userDetailsService(memberService);


        return http.build();
    }



//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
//    } 밑에 AuthenticationManager (시큐리티 5.7이전 버전)


//    @Bean
//     AuthenticationManager authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
//        return builder.userDetailsService(memberService).passwordEncoder(passwordEncoder()).and().build();
//    }

    //    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//    } 밑에 WebSecurityCustomizer (시큐리티 5.7이전에는 이렇게 씀)
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web)->web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
