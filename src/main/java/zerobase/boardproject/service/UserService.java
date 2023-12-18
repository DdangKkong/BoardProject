package zerobase.boardproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zerobase.boardproject.domain.User;
import zerobase.boardproject.dto.AuthDto;
import zerobase.boardproject.exception.AppException;
import zerobase.boardproject.exception.ErrorCode;
import zerobase.boardproject.repository.UserRepository;
import zerobase.boardproject.security.JwtProvider;

@Service
@RequiredArgsConstructor
public class UserService{

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Value("${spring.jwt.secret}")
  private String secretKey;

  // 회원가입
  public void register(AuthDto.SignUp dto) {

    // ID 중복검사
    boolean exist = userRepository.existsByUserLoginId(dto.getUserLoginId());
    if (exist) {
      throw new AppException(ErrorCode.USERLOGINID_DUPLICATED, dto.getUserLoginId() + "는 이미 사용중인 아이디 입니다.");
    }

    dto.setPassword(passwordEncoder.encode(dto.getPassword()));
    userRepository.save(dto.toEntity());

  }

  // 로그인
  public String login(AuthDto.SignIn dto) {

    String loginId = dto.getUserLoginId();
    String loginPw = dto.getPassword();

    // 로그인 아이디 없음
    User user = userRepository.findByUserLoginId(loginId)
        .orElseThrow(() -> new AppException(ErrorCode.USERLOGINID_NOT_FOUND, "아이디를 찾을 수 없습니다."));
    // 패스워드 틀림
    if (!passwordEncoder.matches(loginPw, user.getPassword())) {
      throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드를 잘못 입력했습니다.");
    }

    // 토큰 발급
    String token = JwtProvider.generateToken(user.getUserLoginId(), secretKey);
    return token;

  }

}
