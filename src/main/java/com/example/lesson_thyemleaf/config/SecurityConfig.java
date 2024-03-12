package com.example.lesson_thyemleaf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


public class SecurityConfig {
    @Bean
    public AuthenticationProvider authenticationProvider() {
        System.out.println("User Pasword mazgi: 123456");

        UserDetails user = User.builder()
                .username("user")
                .password("{noop}" + "123456")
                .roles("USER")
                .build();

        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(new InMemoryUserDetailsManager(user));
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // authorization
        http.authorizeHttpRequests()
                .requestMatchers("/greating", "/time").permitAll()
                .requestMatchers("/auth/*").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin(httpSecurityFormLoginConfigurer ->
                        httpSecurityFormLoginConfigurer
                                .loginPage("/auth/go-to-loginPage")
                                .loginProcessingUrl("/loginProcessUrl").permitAll()
                                .defaultSuccessUrl("/student/list",true)
                );
        return http.build();
    }

}
