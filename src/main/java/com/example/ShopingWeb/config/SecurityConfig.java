package com.example.ShopingWeb.config;

import com.example.ShopingWeb.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // http
        //     .authorizeHttpRequests(auth -> auth
        //         .requestMatchers("/register", "/login", "/css/**").permitAll()
        //         .requestMatchers("/admin/**").hasRole("ADMIN")
        //         .anyRequest().authenticated()
        //     )
        //     .formLogin(form -> form
        //         .loginPage("/login")
        //         .defaultSuccessUrl("/dashboard", true)
        //         .permitAll()
        //     )
        //     .logout(logout -> logout
        //         .logoutSuccessUrl("/login?logout")
        //         .permitAll()
        //     );

        http
    .authorizeHttpRequests(auth -> auth
        // Public URLs
        .requestMatchers("/register", "/login", "/css/**").permitAll()
        
        // Only ADMIN can access these URLs
        .requestMatchers(
            "/products/create",
            "/products/save",
            "/products/edit/**",
            "/products/delete/**"
        ).hasRole("ADMIN")
        
        // Both USER and ADMIN can view products
        .requestMatchers("/products/**").hasAnyRole("USER","ADMIN")
        
        .anyRequest().authenticated()
    )
    .formLogin(form -> form
        .loginPage("/login")
        .defaultSuccessUrl("/products", true)
        .permitAll()
    )
    .logout(logout -> logout
        .logoutSuccessUrl("/login?logout")
        .permitAll()
    );


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
