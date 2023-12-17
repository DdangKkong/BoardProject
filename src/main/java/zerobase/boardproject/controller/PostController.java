package zerobase.boardproject.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zerobase.boardproject.dto.CreatePost;
import zerobase.boardproject.dto.DeletePost;
import zerobase.boardproject.dto.ModifyPost;
import zerobase.boardproject.dto.ReadPost;
import zerobase.boardproject.service.PostService;

@RestController
@RequestMapping("/board")
public class PostController {

  private final PostService postService;
  public PostController(PostService postService) {
    this.postService = postService;
  }

  // 게시글 등록
  @PostMapping("/posts")
  public ResponseEntity<String> createPost(
      @RequestBody @Valid CreatePost.Request request, Authentication authentication
  ) {
    CreatePost.Response.from(
        postService.createPost(
            request.getUserId(), request.getTitle(), request.getContent()
        )
    );
    return ResponseEntity.ok().body(authentication.getName() + "님의 게시글 등록이 완료 되었습니다.");
  }

  // 게시글 삭제
  @DeleteMapping("/posts")
  public ResponseEntity<String> deletePost(
      @RequestBody @Valid DeletePost.Request request
  ) {
    DeletePost.Response.from(
        postService.deletePost(request.getUserId(), request.getPostId()));
    return ResponseEntity.ok().body("delete complete");
  }

  // 게시글 수정
  @PostMapping("/form")
  public ResponseEntity<String> modifyPost(
      @RequestBody @Valid ModifyPost.Request request
  ) {
    ModifyPost.Response.from(
        postService.modifyPost(
            request.getPostId(), request.getTitle(), request.getContent()
        )
    );
    return ResponseEntity.ok().body("modify complete");
  }

  // 게시글 조회
  @GetMapping("/posts")
  public ReadPost.Response readPost(
      @RequestBody @Valid ReadPost.Request request
  ) {
    return ReadPost.Response.from(
        postService.readPost(request.getPostId()));
  }


}