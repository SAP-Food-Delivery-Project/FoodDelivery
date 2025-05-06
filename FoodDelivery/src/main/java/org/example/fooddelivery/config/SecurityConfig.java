package org.example.fooddelivery.config;

import lombok.AllArgsConstructor;
import org.example.fooddelivery.services.contracts.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true
)
@AllArgsConstructor
public class SecurityConfig {

    private final UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests
                        (
                                authz -> authz
                                        .requestMatchers(HttpMethod.POST, "/api/foodproducers").hasRole("employee")
                                        .requestMatchers(HttpMethod.PUT, "/api/foodproducers/{id}").hasRole("employee")
                                        .requestMatchers(HttpMethod.DELETE, "/api/foodproducers/{id}").hasRole("employee")
                                        .requestMatchers(HttpMethod.GET, "/api/orders/turnover").hasRole("employee")
                                        .requestMatchers(HttpMethod.GET, "/api/suppliers/incomes").hasRole("employee")
                                        .requestMatchers(HttpMethod.POST, "/api/supplier/bonuses").hasRole("employee")
                                        .anyRequest().authenticated()
                        )

                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

}
