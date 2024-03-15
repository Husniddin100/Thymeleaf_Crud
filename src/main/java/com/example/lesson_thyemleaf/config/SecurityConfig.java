package com.example.lesson_thyemleaf.config;

import com.example.lesson_thyemleaf.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.UUID;

@Configuration
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public AuthenticationProvider authenticationProvider() {
        // authentication
//        String password = UUID.randomUUID().toString();
        System.out.println("User Pasword mazgi: 123456");

        UserDetails user = User.builder()
                .username("user")
                .password("{noop}" + "123456")
                .roles("USER")
                .build();

        // authentication (login,password)
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;

    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // authorization
        http.authorizeHttpRequests()
                .requestMatchers("/greating", "/time").permitAll()
                .requestMatchers("/auth/*").permitAll()
                .requestMatchers("/css/*").permitAll()
                .requestMatchers("/bootstrap").permitAll()
                .requestMatchers("/webjars/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin(httpSecurityFormLoginConfigurer ->
                        httpSecurityFormLoginConfigurer
                                .loginPage("/auth/go-to-loginPage")
                                .loginProcessingUrl("/loginProcessUrl").permitAll()
                                .defaultSuccessUrl("/student/list", true)
                                .failureUrl("/auth/go-to-loginPage?error=true")
                ).logout(httpSecurityLogoutConfigurer ->
                        httpSecurityLogoutConfigurer
                                .logoutUrl("/auth/go-to-logOutPage")
                                .logoutSuccessUrl("/greating")
                );


        /*http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                    authorizationManagerRequestMatcherRegistry
                            .anyRequest()
                            .authenticated();
                }).formLogin(httpSecurityFormLoginConfigurer ->
                httpSecurityFormLoginConfigurer.
                );*/
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                String md5 = MD5Util.getMd5(rawPassword.toString());
                return md5.equals(encodedPassword);
            }
        };
    }


}
