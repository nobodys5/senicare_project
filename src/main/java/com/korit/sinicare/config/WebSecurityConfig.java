package com.korit.sinicare.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.korit.sinicare.dto.response.ResponseCode;
import com.korit.sinicare.dto.response.ResponseMessage;
import com.korit.sinicare.filter.JwtAuthenticationFilter;
import com.korit.sinicare.handler.OAuth2SuccessHandler;
import com.korit.sinicare.service.implement.OAuth2UserServiceImplement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

// Spring Web 보안 설정
@Configurable
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final OAuth2UserServiceImplement oAuth2Service;

    // 필터체인 사용하기위해 bean어노테이션 사용하여 등록
    @Bean
    protected SecurityFilterChain configure(HttpSecurity security) throws Exception {

        security
            // basic 인증 방식 미사용
            .httpBasic(HttpBasicConfigurer::disable)
            // session 미사용 (유지 X)
            .sessionManagement(sessionManagement -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            // CSRF 취약점 대비 미지정
            .csrf(CsrfConfigurer::disable)
            // CORS 정책 설정
            .cors(cors -> cors.configurationSource(configurationSource())
            )
            // URL 패턴 및 HTTP 메서드에 따라 인증 및 인가 여부 지정
            .authorizeHttpRequests(request -> request
                .requestMatchers("/api/v1/auth/**","oauth2/callback/*", "/file/*", "/").permitAll()
                .anyRequest().authenticated()
            )
            // 인증 및 인가 작업중 발생하는 예외 처리
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint(new AuthenticationFailEntryPoint())
            )

            // oAuth2 로그인 적용
            .oauth2Login(oauth2 -> oauth2
                .redirectionEndpoint(endpoint -> endpoint.baseUri("/oauth2/callback/*"))
                .authorizationEndpoint(endpoint -> endpoint.baseUri("/api/v1/auth/sns-sign-in"))
                .userInfoEndpoint(endpoint -> endpoint.userService(oAuth2Service))
                .successHandler(oAuth2SuccessHandler)
            )

            // 필터 등록
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
        return security.build();
    }

    @Bean
    protected CorsConfigurationSource configurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*"); // 서로 다른 서버의 경로를 어떤 경로든 받을수 있게하는 설정 http 통합시켜주는 용도
        configuration.addAllowedHeader("*"); // postman에서 header 어떤 입력을 받을건지 설정하는 코드 *이기 때문에 값이 없어도 된다는 뜻의 코드
        configuration.addAllowedMethod("*"); // 위의 header와 마찬가지로 요청보낼 메서드 get,post등을 어떤 경우든 허용하겠다는 뜻

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // /**로 설정하면 예시 /auth/* 등의 모든경로를 받을수 있음 */ */
        source.registerCorsConfiguration("/**", configuration);

        return source;

    }

}

class AuthenticationFailEntryPoint implements AuthenticationEntryPoint{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        authException.printStackTrace();
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{ \"code\": \"" + ResponseCode.AUTHENTICATION_FAIL + "\", \"message\": \"" + ResponseMessage.AUTHENTICATION_FAIL + "\" }");
    };


}