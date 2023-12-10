package zerobase.boardproject.security;

import java.io.IOException;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import zerobase.boardproject.exception.AppException;
import zerobase.boardproject.exception.ErrorCode;
import zerobase.boardproject.service.UserService;

@RequiredArgsConstructor
@Slf4j // log.~~ 사용하려면 추가
public class JwtFilter extends OncePerRequestFilter {

  private final UserService userService;
  private final String secretKey;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
    log.info("authorization : {}", authorization);

    // token 확인
    if (authorization == null || !authorization.startsWith("Bearer ")) {
      log.error("authorization 을 잘못 보냈습니다.");
      filterChain.doFilter(request, response); // 밑에서 가져온거
      return;
    }

    // token 꺼내기
    String token = authorization.split(" ")[1];

    // token expired 체크
    if (JwtProvider.isExpired(token, secretKey)) {
      log.error("token 이 만료 되었습니다.");
//      filterChain.doFilter(request, response); // 밑에서 가져온거
      throw new AppException(ErrorCode.TOKEN_EXPIRED, "token 이 만료되었습니다.");
    }

    // token 에서 userLoginId 꺼내기
    String userLoginId = JwtProvider.getUserLoginId(token, secretKey);
    log.info("userLoginId:{}" + userLoginId);

    // 권한 부여
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(userLoginId, null, List.of(new SimpleGrantedAuthority("USER")));
    // Detail
    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    filterChain.doFilter(request, response);

  }
}
