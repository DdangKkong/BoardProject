package zerobase.boardproject.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.Data;
import zerobase.boardproject.domain.User;

public class AuthDto {

  @Data
  public static class SignIn {

    private String userLoginId;
    private String password;

  }
  @Data
  public static class SignUp {

    private String userLoginId;
    private String password;
    private String nickName;

    public User toEntity() {
      return User.builder()
          .userLoginId(this.userLoginId)
          .password(this.password)
          .nickname(this.nickName)
          .createdDate(Timestamp.valueOf(LocalDateTime.now()))
          .build();
    }

  }

}
