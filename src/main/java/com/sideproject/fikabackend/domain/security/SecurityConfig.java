package com.sideproject.fikabackend.domain.security;

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

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //csrf 미적용
        http.csrf().disable();

        // h2 console 화면 사용을 위해 설정
        http.headers().frameOptions().disable();

        http
                //spring security에서 제공하는 로그인 인증창을 disable함
                .httpBasic().disable()

                // jwt token으로 인증하므로 stateless 하도록 처리
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //URL별 접근제어 관리 옵션
        http.authorizeRequests()

                // antMatchers : 권한관리 대상 지정 | 경로설정할때 여러 경로를 다루게 되는데 특수한 경우를 위에 두고 아래쪽으로
                // 갈수록 일반적인 경우를 두어야 경로가 꼬이거나 무한루프 발생가 발생 안함

                .antMatchers("/","/**","/h2-console/**").permitAll()
//                access("permitAll")
                // 해당 API에 대해서는 모든 요청을 허가한다는 설정이다.
                .antMatchers("/users/login").permitAll()
                // 해당 API에 대해서는 Admin 권한이 있어야 요청할 수 있다는 설정이다.
                .antMatchers("/users/test").hasRole("Admin")
                .anyRequest().authenticated()
                .and()
                 // JWT 인증을 위하여 직접 구현한 필터를 UsernamePasswordAuthenticationFilter 전에 실행하겠다는 설정이다.
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
