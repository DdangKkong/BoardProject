package zerobase.boardproject.domain;

import java.math.BigInteger;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name = "user")
public class User {

  // MYSQL에서 id 생성 및 자동증가
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private BigInteger id;

  // 회원 아이디
  @Column(name = "user_login_id")
  private String userLoginId;

  // 회원 비밀번호
  @Column(name = "password")
  private String password;

  // 회원 닉네임
  @Column(name = "nickname")
  private String nickname;

  // 회원가입 일시
  @Column(name = "created_date")
  private Timestamp createdDate;

  // 회원정보수정 일시
  @Column(name = "modified_date")
  private Timestamp modifiedDate;

  // 회원탈퇴 일시
  @Column(name = "removed_date")
  private Timestamp removedDate;

}
