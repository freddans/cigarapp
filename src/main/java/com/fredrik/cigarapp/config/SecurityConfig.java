package com.fredrik.cigarapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    // User Creation
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

        // InMemoryUserDetailsManager
        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("admin"))
                .roles("ADMIN", "USER")
                .build();

        UserDetails user = User.withUsername("user")
                .password(encoder.encode("user"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authConfig -> {
                    authConfig.requestMatchers("/auth/user/**").authenticated();
                    authConfig.requestMatchers("/auth/admin/**").authenticated();
                    authConfig.requestMatchers("/auth/site/**").authenticated();
                    authConfig.requestMatchers("/contactpost/**").authenticated();
                    authConfig.requestMatchers("/deleteContactPost/**").permitAll();
                })
                .formLogin(withDefaults());

        http
                .authorizeHttpRequests(auth2Config -> {
                    auth2Config.requestMatchers("/").permitAll();
                    auth2Config.requestMatchers("/css/**").permitAll();
                    auth2Config.requestMatchers("/javascript/**").permitAll();
                    auth2Config.requestMatchers("/images/**").permitAll();
                    auth2Config.requestMatchers("/icons/**").permitAll();
                    auth2Config.requestMatchers("/auth/welcome").permitAll();
                    auth2Config.requestMatchers("/cigardb").permitAll();
                    auth2Config.requestMatchers("/saveContactPost").permitAll();
                    auth2Config.requestMatchers("/member/add").permitAll();

                    auth2Config.requestMatchers("/member/all").permitAll();

                    auth2Config.requestMatchers("/addWithLog").permitAll();
                    auth2Config.requestMatchers("/cigarsWithLog").permitAll();
                    auth2Config.requestMatchers("/new/editcigar/**").permitAll();
                    auth2Config.requestMatchers("/delete/**").permitAll();
                    auth2Config.requestMatchers("/deleteWithLogger/**").permitAll();
                })
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    // Password Encoding
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}