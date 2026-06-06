package com.medilabo.front.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security configuration to set authentication and authorization parameters on specific pages of the app.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    /**
     * Bean of the authentication rules.
     *
     * @param http The parameter used to configure the web security.
     * @return The authentication configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/css/**").permitAll(); // ← Bootstrap accessible sans login
                    auth.anyRequest().authenticated();
                })
                .formLogin(form -> form
                        .defaultSuccessUrl("/list", true)
                )
                .build();
    }

    /**
     * Bean of the default user saved in memory.
     *
     * @return The default user of the app.
     */
    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER").build();
        return new InMemoryUserDetailsManager(user);
    }

    /**
     * Bean of the password encoder.
     *
     * @return A encode.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
