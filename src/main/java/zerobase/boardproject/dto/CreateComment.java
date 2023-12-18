package zerobase.boardproject.dto;

import com.sun.istack.NotNull;
import java.math.BigInteger;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CreateComment {

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Request {

    @NotNull
    private BigInteger userId;

    @NotNull
    private BigInteger postId;

    @NotNull
    private String content;

  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Response {
    private BigInteger commentId;
    private Timestamp dateTime;

    public static Response from(CommentDto commentDto) {
      return Response.builder()
          .commentId(commentDto.getCommentId())
          .dateTime(commentDto.getCreatedDate())
          .build();
    }

  }

}
