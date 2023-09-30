package br.com.desafiocielo.desafio1.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecutiryConfig {

    SecurityFilter securityFilter;

    public SecutiryConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(AntPathRequestMatcher
                                .antMatcher(HttpMethod.POST, "/auth/login")).permitAll()
                        .requestMatchers(AntPathRequestMatcher
                                .antMatcher(HttpMethod.POST, "/auth/register")).permitAll()
                        .requestMatchers(AntPathRequestMatcher
                                .antMatcher(HttpMethod.POST, "/cliente/pessoa-fisica")).hasRole("ADMIN")
                        .requestMatchers(AntPathRequestMatcher
                                .antMatcher(HttpMethod.POST, "/cliente/pessoa-juridica")).hasRole("ADMIN")
                        .requestMatchers(AntPathRequestMatcher
                                .antMatcher(HttpMethod.GET, "/cliente/pessoa-juridica")).hasRole("USER")
                        .requestMatchers(AntPathRequestMatcher
                                .antMatcher(HttpMethod.GET, "/cliente/pessoa-juridica")).hasRole("USER")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager (
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
