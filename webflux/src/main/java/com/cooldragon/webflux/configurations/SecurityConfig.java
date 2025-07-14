package com.cooldragon.webflux.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("admin")
                //.password("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("USER")
                .build();
        return new MapReactiveUserDetailsService(user);
    }
        
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) throws Exception {
        http
            .cors(ServerHttpSecurity.CorsSpec::disable)
            .csrf(ServerHttpSecurity.CsrfSpec::disable) // CSRF 비활성화
            .authorizeExchange(exchanges -> exchanges
                .pathMatchers("/webjars/swagger-ui/**", "/v3/api-docs/**", "/api-docs/**").permitAll()
                .pathMatchers("/actuator/**").permitAll()   // 해당 경로는 인증 없이 접근 허용
                .pathMatchers("/api/hello/**").permitAll()   // 해당 경로는 인증 없이 접근 허용
                .pathMatchers(HttpMethod.OPTIONS, "/api/admin/**").hasRole("ADMIN") // ADMIN 권한 필요
                .anyExchange().authenticated() // 그 외 모든 요청은 인증 필요
            )
            //.httpBasic(withDefaults())     // HTTP Basic 인증 활성화
            //.formLogin(withDefaults())    // 폼 로그인 활성화            
            //.formLogin(ServerHttpSecurity.FormLoginSpec::disable) // Form Login 비활성화
            //.httpBasic(ServerHttpSecurity.HttpBasicSpec::disable) // HTTP Basic 비활성화
            //.logout(logout -> logout
            //    .logoutUrl("/logout")
            //    .logoutHandler(new WebSessionServerLogoutHandler())
           // )
            ;
        // JWT, 커스텀 필터 추가 시: .addFilterBefore(new JWTFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
        return http.build();
    }
}
