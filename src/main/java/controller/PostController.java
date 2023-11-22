package controller;

import domain.Posts;
import java.math.BigInteger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {

  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  // 게시글 등록
  @PostMapping("/{user_id}")
  public ResponseEntity<?> addPost(
      @PathVariable BigInteger user_id,
      Posts posts) {

    return ResponseEntity.ok(postService.addPost(posts));

  }

}
