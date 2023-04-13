package com.sideproject.fikabackend.global.security;

import com.sideproject.fikabackend.global.jwt.JwtAuthenticationFilter;
import com.sideproject.fikabackend.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity //스프링 시큐리티 지원을 가능하게 함
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //cors 설정
        http .cors().configurationSource(corsConfigurationSource());

        // h2 console 화면 사용을 위해 설정
        http.headers().frameOptions().disable();
        //csrf 미적용
        http.csrf().disable();

        //spring security에서 제공하는 로그인 인증창을 disable함
        http.httpBasic().disable()
        // jwt token으로 인증하므로 stateless 하도록 처리
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //URL별 접근제어 관리 옵션
        http.authorizeRequests()

                // antMatchers : 권한관리 대상 지정 | 경로설정할때 여러 경로를 다루게 되는데 특수한 경우를 위에 두고 아래쪽으로
                // 갈수록 일반적인 경우를 두어야 경로가 꼬이거나 무한루프 발생가 발생 안함

                .antMatchers("/","/**","/h2-console/**").permitAll()
                // 해당 API에 대해서는 모든 요청을 허가한다는 설정이다.
                .antMatchers("/members/login").permitAll()
                // 해당 API에 대해서는 Admin 권한이 있어야 요청할 수 있다는 설정이다.
                .antMatchers("/members/test").hasRole("Admin")
                .anyRequest().authenticated()
                .and()
                 // JWT 인증을 위하여 직접 구현한 필터를 UsernamePasswordAuthenticationFilter 전에 실행하겠다는 설정이다.
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true) ;
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedOrigin("http://localhost:3000"); // local 테스트 시
        configuration.addAllowedOrigin("https://www.example.com"); // 배포 시
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.addExposedHeader("Authorization");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}

