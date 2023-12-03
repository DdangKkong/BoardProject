package zerobase.boardproject.controller;

import javax.validation.Valid;
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
  public CreatePost.Response createPost(
      @RequestBody @Valid CreatePost.Request request
  ) {
    return CreatePost.Response.from(
        postService.createPost(
            request.getUserId(), request.getTitle(), request.getContent()
        )
    );
  }

  // 게시글 삭제
  @PostMapping("/letter")
  public DeletePost.Response deletePost(
      @RequestBody @Valid DeletePost.Request request
  ) {
    return DeletePost.Response.from(
        postService.deletePost(request.getPostId()));
  }

  // 게시글 수정
  @PostMapping("/form")
  public ModifyPost.Response modifyPost(
      @RequestBody @Valid ModifyPost.Request request
  ) {
    return ModifyPost.Response.from(
        postService.modifyPost(
            request.getPostId(), request.getTitle(), request.getContent()
        )
    );
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