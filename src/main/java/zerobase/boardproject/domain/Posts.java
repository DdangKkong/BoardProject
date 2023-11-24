package zerobase.boardproject.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigInteger;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
public class Posts {

  // MYSQL에서 id 생성 및 자동증가
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigInteger id;

  // 게시글 제목
  private String title;

  // 게시글 내용
  private String content;

  // 게시글 작성 일시
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
  private LocalDateTime created_date;

  // 게시글 수정 일시
  private LocalDateTime modified_date;

  // 게시글 삭제 일시
  private LocalDateTime removed_date;

  // User Entity의 id
  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

}
