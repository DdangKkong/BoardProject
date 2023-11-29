package zerobase.boardproject.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.stereotype.Service;
import zerobase.boardproject.domain.Posts;
import zerobase.boardproject.domain.User;
import zerobase.boardproject.dto.PostDto;
import zerobase.boardproject.repository.PostRepository;
import zerobase.boardproject.repository.UserRepository;

@Service
public class PostService {

  private final PostRepository postRepository;

  private final UserRepository userRepository;

  public PostService(PostRepository postRepository, UserRepository userRepository) {
    this.postRepository = postRepository;
    this.userRepository = userRepository;
  }

  // 게시글 등록
  public PostDto createPost(BigInteger userId, String title, String content){
    User user = getUser(userId);

    return PostDto.fromEntity(postRepository.save(
        Posts.builder()
            .user(user)
            .title(title)
            .content(content)
            .createdDate(Timestamp.valueOf(LocalDateTime.now()))
            .build()
    ));
  }

  // 게시글 삭제
  public PostDto deletePost(BigInteger postId) {
    Posts posts = getPost(postId);

    posts.setContent("Deleted");
    posts.setRemovedDate(Timestamp.valueOf(LocalDateTime.now()));

    postRepository.save(posts);

    return PostDto.fromEntity(posts);

  }

  // 게시글 수정
  public PostDto modifyPost(BigInteger postId, String title, String content) {
    Posts posts = getPost(postId);

    posts.setTitle(title);
    posts.setContent(content);
    posts.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));

    postRepository.save(posts);

    return PostDto.fromEntity(posts);
  }

  public PostDto readPost(BigInteger postId) {
    Posts posts = getPost(postId);

    return PostDto.fromEntity(posts);
  }


  private User getUser(BigInteger userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("유저가 없습니다"));
  }

  private Posts getPost(BigInteger postId) {
    return postRepository.findById(postId)
        .orElseThrow(() -> new RuntimeException("게시글이 없습니다"));
  }

}
