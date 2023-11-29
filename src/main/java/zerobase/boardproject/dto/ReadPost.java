package zerobase.boardproject.dto;

import com.sun.istack.NotNull;
import java.math.BigInteger;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ReadPost {

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Request {

    @NotNull
    private BigInteger postId;

  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Response {
    private BigInteger postId;

    public static Response from(PostDto postDto) {
      return Response.builder()
          .postId(postDto.getPostId())
          .build();
    }

  }

}
