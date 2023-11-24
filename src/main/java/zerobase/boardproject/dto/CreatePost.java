package zerobase.boardproject.dto;

import com.sun.istack.NotNull;
import java.math.BigInteger;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class CreatePost {

  @Getter
  @Setter
  @AllArgsConstructor
  public static class Request {

    @NotNull
    private BigInteger user_id;

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
    private BigInteger user_id;
    private LocalDateTime dateTime;

    public static Response from(PostDto postDto) {
      return Response.builder()
          .user_id(postDto.getUser_id())
          .dateTime(postDto.getCreated_date())
          .build();
    }

  }

}
