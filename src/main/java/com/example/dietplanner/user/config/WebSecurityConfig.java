package com.example.dietplanner.user.config;

import com.example.dietplanner.user.security.jwt.AuthEntryPointJwt;
import com.example.dietplanner.user.security.jwt.AuthTokenFilter;
import com.example.dietplanner.user.security.services.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    UserDetailsServiceImpl userDetailsService;

    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter(userDetailsService);
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests().anyRequest().permitAll();
//        http.cors().and().csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .authorizeRequests().antMatchers("/").permitAll().and()
//                .authorizeRequests().antMatchers("/home").permitAll().and()
//                .authorizeRequests().antMatchers("/login").permitAll().and()
//                .authorizeRequests().antMatchers("/api/auth/**").permitAll()
//                .antMatchers("/api/test/**").permitAll()
//                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}



//https://stackoverflow.com/questions/27170772/where-to-put-static-files-such-as-css-in-a-spring-boot-project

//    Apart from placing it anywhere beneath src/main/resources/static and not using @EnableWebMvc, you'll need to authorize access to your js or css folder especially if you have spring-boot-security in you classpath. You'll add something like this:
//
//@Configuration
//@EnableWebSecurity
//public class MainSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/home", "/js/**", "/css/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//    }
//}
//
//    Then in your HTML:
//
//<link rel="stylesheet"  href="/css/bootstrap.min.css">
//<script src="/js/bootstrap.min.js"></script>
//

















