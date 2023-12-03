package zerobase.boardproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zerobase.boardproject.domain.User;
import zerobase.boardproject.dto.AuthDto;
import zerobase.boardproject.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  // 회원가입
  @PostMapping("/signup")
  public ResponseEntity<User> signUp(@RequestBody AuthDto.SignUp request) {
    System.out.println("요청들어옴");
    User user = userService.register(request);
    return ResponseEntity.ok(user);
  }

  // 로그인
//  @PostMapping("/signin")
//  public ResponseEntity<User> signIn (@RequestBody AuthDto.SignIn request) {
//    return ResponseEntity.ok();
//  }

}
