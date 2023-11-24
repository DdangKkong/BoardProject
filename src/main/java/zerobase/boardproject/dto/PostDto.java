package zerobase.boardproject.dto;

import java.math.BigInteger;
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
  private BigInteger user_id;

  // 게시글 id
  private BigInteger post_id;

  // 게시글 제목
  private String title;

  // 게시글 내용
  private String content;

  // 게시글 작성 일시
  private LocalDateTime created_date;

  // 게시글 수정 일시
  private LocalDateTime modified_date;

  // 게시글 삭제 일시
  private LocalDateTime removed_date;

  public static PostDto fromEntity(Posts posts){
    return PostDto.builder()
        .user_id(posts.getUser().getId())
        .post_id(posts.getId())
        .title(posts.getTitle())
        .content(posts.getContent())
        .created_date(posts.getCreated_date())
        .modified_date(posts.getModified_date())
        .removed_date(posts.getRemoved_date())
        .build();
  }

}
