package zerobase.boardproject.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zerobase.boardproject.dto.CreateComment;
import zerobase.boardproject.dto.DeleteComment;
import zerobase.boardproject.dto.ModifyComment;
import zerobase.boardproject.dto.ReadComment;
import zerobase.boardproject.service.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class CommentController {

  private final CommentService commentService;

  // 댓글 등록
  @PostMapping("/comments")
  public ResponseEntity<String> createComment(
      @RequestBody @Valid CreateComment.Request request, Authentication authentication
  ) {
    CreateComment.Response.from(
        commentService.createComment(
            request.getUserId(), request.getPostId(), request.getContent()
        )
    );
    return ResponseEntity.ok().body(authentication.getName() + "님의 댓글 등록이 완료 되었습니다.");
  }

  // 댓글 삭제
  @DeleteMapping("/comments")
  public ResponseEntity<String> deleteComment(
      @RequestBody @Valid DeleteComment.Request request
  ) {
    DeleteComment.Response.from(
        commentService.deleteComment(request.getUserId(), request.getCommentId()));
    return ResponseEntity.ok().body("delete complete");
  }

  // 댓글 수정
  @PutMapping("/comments")
  public ResponseEntity<String> modifyPost(
      @RequestBody @Valid ModifyComment.Request request
  ) {
    ModifyComment.Response.from(
        commentService.modifyComment(
            request.getCommentId(), request.getContent()
        )
    );
    return ResponseEntity.ok().body("modify complete");
  }

  // 댓글 조회
  @GetMapping("/comments")
  public ReadComment.Response readPost(
      @RequestBody @Valid ReadComment.Request request
  ) {
    return ReadComment.Response.from(
        commentService.readComment(request.getCommentId()));
  }


}