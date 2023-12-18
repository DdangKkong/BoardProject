package zerobase.boardproject.dto;

import java.math.BigInteger;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zerobase.boardproject.domain.Comments;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {

  // 회원 id
  private BigInteger userId;

  // 게시글 id
  private BigInteger postId;

  // 댓글 id
  private BigInteger commentId;

  // 댓글 내용
  private String content;

  // 댓글 작성 일시
  private Timestamp createdDate;

  // 댓글 수정 일시
  private Timestamp modifiedDate;

  // 댓글 삭제 일시
  private Timestamp removedDate;

  public static CommentDto fromEntity(Comments comments){
    return CommentDto.builder()
        .userId(comments.getUser().getId())
        .postId(comments.getPosts().getId())
        .commentId(comments.getId())
        .content(comments.getContent())
        .createdDate(comments.getCreatedDate())
        .modifiedDate(comments.getModifiedDate())
        .removedDate(comments.getRemovedDate())
        .build();
  }

}
