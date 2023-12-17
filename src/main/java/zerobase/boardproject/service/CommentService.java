package zerobase.boardproject.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.boardproject.domain.Comments;
import zerobase.boardproject.domain.Posts;
import zerobase.boardproject.domain.User;
import zerobase.boardproject.dto.CommentDto;
import zerobase.boardproject.exception.AppException;
import zerobase.boardproject.exception.ErrorCode;
import zerobase.boardproject.repository.CommentRepository;
import zerobase.boardproject.repository.PostRepository;
import zerobase.boardproject.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;

  private final UserRepository userRepository;

  private final PostRepository postRepository;


  // 게시글 등록
  public CommentDto createComment(BigInteger userId, BigInteger postId, String content) {
    User user = getUser(userId);
    Posts posts = getPost(postId);

    return CommentDto.fromEntity(commentRepository.save(
        Comments.builder()
            .user(user)
            .posts(posts)
            .content(content)
            .createdDate(Timestamp.valueOf(LocalDateTime.now()))
            .build()
    ));
  }

  // 게시글 삭제
  public CommentDto deleteComment(BigInteger userId, BigInteger commentId) {
    User user = getUser(userId);
    Comments comments = getComment(commentId);

    // 댓글 작성자인지 확인 (작성자만 본인의 댓글 삭제 가능)
    BigInteger writerId = comments.getUser().getId();
    if (!Objects.equals(writerId, userId)) {
      throw new AppException(ErrorCode.USER_UNMACHED, user.getNickname() + "님은 댓글 작성자가 아닙니다");
    }

    comments.setContent("Deleted");
    comments.setRemovedDate(Timestamp.valueOf(LocalDateTime.now()));

    commentRepository.save(comments);

    return CommentDto.fromEntity(comments);

  }

  // 게시글 수정
  public CommentDto modifyComment(BigInteger commentId, String content) {
    Comments comments = getComment(commentId);

    comments.setContent(content);
    comments.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));

    commentRepository.save(comments);

    return CommentDto.fromEntity(comments);
  }

  public CommentDto readComment(BigInteger commentId) {
    Comments comments = getComment(commentId);

    return CommentDto.fromEntity(comments);
  }


  private User getUser(BigInteger userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new AppException(ErrorCode.USERID_NOT_FOUND, "회원이 아닙니다"));
  }

  private Posts getPost(BigInteger postId) {
    return postRepository.findById(postId)
        .orElseThrow(() -> new AppException(ErrorCode.POSTID_NOT_FOUND, "게시글이 없습니다"));
  }

  private Comments getComment(BigInteger commentId) {
    return commentRepository.findById(commentId)
        .orElseThrow(() -> new AppException(ErrorCode.COMMENTID_NOT_FOUND, "댓글이 없습니다"));
  }

}
