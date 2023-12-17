package zerobase.boardproject.dto;

import com.sun.istack.NotNull;
import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ReadComment {

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Request {

    @NotNull
    private BigInteger commentId;

  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Response {
    private String content;

    public static Response from(CommentDto commentDto) {
      return Response.builder()
          .content(commentDto.getContent())
          .build();
    }

  }

}
