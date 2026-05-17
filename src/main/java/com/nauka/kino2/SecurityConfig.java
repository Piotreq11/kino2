package com.nauka.kino2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers.matchers;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("admin123").roles("ADMIN").build();
        return  new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Strony zaczynające się od /admin/ wymagają roli ADMIN
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // Cała reszta (strona główna, repertuar, kupno biletów, pliki CSS) jest otwarta dla każdego
                        .requestMatchers("/", "/repertuar", "/bilety/**", "/style.css").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        // Używamy domyślnego formularza logowania dostarczonego przez Springa
                        .defaultSuccessUrl("/admin/seanse", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }
}
