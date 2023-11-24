package zerobase.boardproject.domain;

import java.math.BigInteger;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

  // MYSQL에서 id 생성 및 자동증가
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigInteger id;

  // 회원 아이디
  private String user_login_id;

  // 회원 비밀번호
  private String password;

  // 회원 닉네임
  private String nickname;

  // 회원가입 일시
  private LocalDateTime created_date;

  // 회원정보수정 일시
  private LocalDateTime modified_date;

  // 회원탈퇴 일시
  private LocalDateTime removed_date;

}
