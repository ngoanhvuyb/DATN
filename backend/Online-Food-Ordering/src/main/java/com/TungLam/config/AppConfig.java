//package com.TungLam.config;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//@Configuration
//@EnableWebSecurity
//public class AppConfig {
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(Authorize -> Authorize
//                        .requestMatchers("/api/admin/**").hasAnyRole("RESTAURANT_OWNER", "ADMIN")
//                        .requestMatchers("/api/**").authenticated()
//                        .anyRequest().permitAll()
//                )
//                .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
//                .csrf(csrf -> csrf.disable())
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()));
//        return http.build();
//    }
//
//    // Cấu hình CORS
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration cfg = new CorsConfiguration();
//        cfg.setAllowedOrigins(Collections.singletonList("http://localhost:3000")); // Đảm bảo frontend URL chính xác
//        cfg.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Các phương thức HTTP được phép
//        cfg.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Các header được phép
//        cfg.setExposedHeaders(Arrays.asList("Authorization")); // Cho phép các header này hiển thị trong phản hồi
//        cfg.setAllowCredentials(true); // Cho phép gửi cookie với yêu cầu
//        cfg.setMaxAge(3600L); // Thời gian CORS cache
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", cfg); // Áp dụng CORS cho tất cả các đường dẫn
//        return source;
//    }
//
//    // Bean mã hóa mật khẩu
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
package com.TungLam.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableWebSecurity

public class AppConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(Authorize -> Authorize
                        .requestMatchers("/api/admin/**").hasAnyRole("RESTAURANT_OWNER", "ADMIN")
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll()
                )
                .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));  // Enable CORS
        return http.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration cfg = new CorsConfiguration();
            cfg.setAllowedOrigins(Collections.singletonList("http://localhost:3000")); // Cho phép localhost:3000
            cfg.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));  // Chỉ định các phương thức cho phép
            cfg.setAllowCredentials(true);  // Nếu bạn sử dụng cookie hoặc xác thực
            cfg.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));  // Các header được cho phép
            cfg.setExposedHeaders(Arrays.asList("Authorization"));  // Cho phép hiển thị các header đặc biệt
            cfg.setMaxAge(3600L);  // Thiết lập thời gian cache cho preflight request
            return cfg;
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

