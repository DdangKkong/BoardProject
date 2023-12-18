package zerobase.boardproject.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import zerobase.boardproject.service.UserService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  @Value("${spring.jwt.secret}")
  private String secretKey;
  private final UserService userService;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .httpBasic().disable()
        .csrf().disable()
        .cors().and()
        .authorizeRequests()
        .antMatchers("/users/signup", "/users/signin").permitAll() // join, login은 언제나 가능
//        .antMatchers(HttpMethod.POST, "/**").authenticated() // POST 요청은 일단 다 막음
        .antMatchers("/**").authenticated() // 접근 다 막음
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt사용하는 경우 씀
        .and()
        .addFilterBefore(new JwtFilter(userService, secretKey), UsernamePasswordAuthenticationFilter.class) // UserNamePasswordAuthenticationFilter적용하기 전에 JWTTokenFilter를 적용 하라는 뜻
        .build();
  }

}
