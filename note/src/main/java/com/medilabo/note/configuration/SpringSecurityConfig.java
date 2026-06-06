package com.medilabo.note.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    /**
     * Bean of the default user saved in memory.
     *
     * @return The default user.
     */
    @Bean
    public UserDetailsService users() {
        UserDetails user = User
                .withUsername("note")
                .password(passwordEncoder().encode("note123"))
                .roles("SERVICE")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    /**
     * Bean of the password encoder.
     *
     * @return A encode.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}