package zerobase.boardproject.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zerobase.boardproject.domain.User;
import zerobase.boardproject.dto.AuthDto;
import zerobase.boardproject.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService{

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;


  public User register(AuthDto.SignUp dto) {

    // ID 중복검사
    boolean exist = userRepository.existsByUserLoginId(dto.getUserLoginId());
    if (exist) {
      throw new RuntimeException("이미 사용중인 아이디 입니다.");
    }

    dto.setPassword(passwordEncoder.encode(dto.getPassword()));
    System.out.println("service 통과");
    return userRepository.save(dto.toEntity());

  }

}
