package zerobase.boardproject.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zerobase.boardproject.dto.CreatePost;
import zerobase.boardproject.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

  private final PostService postService;
  public PostController(PostService postService) {
    this.postService = postService;
  }

  // 게시글 등록
  @PostMapping("/create")
  public CreatePost.Response createPost(
      @RequestBody CreatePost.Request request
  ) {
    return CreatePost.Response.from(
        postService.createPost(
            request.getUser_id(), request.getTitle(), request.getContent()
        )
    );
  }

}
