package zerobase.boardproject.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zerobase.boardproject.dto.AuthDto;
import zerobase.boardproject.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  // 회원가입
  @PostMapping("/signup")
  public ResponseEntity<String> signUp(@RequestBody @Valid AuthDto.SignUp request) {
    userService.register(request);
    return ResponseEntity.ok().body("welcome to join us");
  }

//   로그인
  @PostMapping("/signin")
  public ResponseEntity<String> signIn (@RequestBody @Valid AuthDto.SignIn request) {
    String token =  userService.login(request);
    return ResponseEntity.ok().body(token);
  }

}
