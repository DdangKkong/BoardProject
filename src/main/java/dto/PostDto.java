package dto;

import domain.Posts;
import java.time.LocalDateTime;
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
public class PostDto {

  // 회원 닉네임
  private String nickname;

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
        .nickname(posts.getUser().getNickname())
        .title(posts.getTitle())
        .content(posts.getContent())
        .created_date(posts.getCreated_date())
        .modified_date(posts.getModified_date())
        .removed_date(posts.getRemoved_date())
        .build();
  }

}
