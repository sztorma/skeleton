package com.sztorma.skeleton.securtiy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    private static final String AUTH_PATH = "/api/authenticate";

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeHttpRequests((authz) -> authz
                .antMatchers(AUTH_PATH).permitAll()
                .anyRequest().denyAll())
                .httpBasic(Customizer.withDefaults());

        addHttpAuthFilter(http);
        return http.build();
    }

    // TODO integration test for authentication
    /**
     * adding Auth filter with auth URL
     * 
     * @param http
     * @throws Exception
     */
    private void addHttpAuthFilter(HttpSecurity http) throws Exception {
        JwtAuthenticationFilter authFilter = new JwtAuthenticationFilter(
                authenticationManager(authenticationConfiguration));
        authFilter.setFilterProcessesUrl(AUTH_PATH);
        http.addFilter(authFilter);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
