package zerobase.boardproject.dto;

import com.sun.istack.NotNull;
import java.math.BigInteger;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CreatePost {

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Request {

    @NotNull
    private BigInteger userId;

    @NotNull
    private String title;

    @NotNull
    private String content;

  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Response {
    private BigInteger userId;
    private Timestamp dateTime;

    public static Response from(PostDto postDto) {
      return Response.builder()
          .userId(postDto.getUserId())
          .dateTime(postDto.getCreatedDate())
          .build();
    }

  }

}
