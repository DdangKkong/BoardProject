package zerobase.boardproject.dto;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zerobase.boardproject.domain.Posts;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {

  // 회원 id
  private BigInteger userId;

  // 게시글 id
  private BigInteger postId;

  // 게시글 제목
  private String title;

  // 게시글 내용
  private String content;

  // 게시글 작성 일시
  //  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
  private Timestamp createdDate;

  // 게시글 수정 일시
  private Timestamp modifiedDate;

  // 게시글 삭제 일시
  private Timestamp removedDate;

  public static PostDto fromEntity(Posts posts){
    return PostDto.builder()
        .userId(posts.getUser().getId())
        .postId(posts.getId())
        .title(posts.getTitle())
        .content(posts.getContent())
        .createdDate(posts.getCreatedDate())
        .modifiedDate(posts.getModifiedDate())
        .removedDate(posts.getRemovedDate())
        .build();
  }

}
