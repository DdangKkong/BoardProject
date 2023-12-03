package zerobase.boardproject.domain;

import java.math.BigInteger;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "posts")
public class Posts {

  // MYSQL에서 id 생성 및 자동증가
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "posts_id")
  private BigInteger id;

  // 게시글 제목
  @Column(name = "title")
  private String title;

  // 게시글 내용
  @Column(name = "content")
  private String content;

  // 게시글 작성 일시
  @Column(name = "created_date")
  private Timestamp createdDate;

  // 게시글 수정 일시
  @Column(name = "modified_date")
  private Timestamp modifiedDate;

  // 게시글 삭제 일시
  @Column(name = "removed_date")
  private Timestamp removedDate;

  // User Entity의 id
  @ManyToOne
  @JoinColumn(name = "user_id")
//  @JsonIgnore
  private User user;

}
