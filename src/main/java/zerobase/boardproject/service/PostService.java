package zerobase.boardproject.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
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
  public PostDto createPost(BigInteger user_id, String title, String content){
    User user = getUser(user_id);

    return PostDto.fromEntity(postRepository.save(
        Posts.builder()
            .user(user)
            .title(title)
            .content(content)
            .created_date(LocalDateTime.now())
            .build()
    ));
  }

  // 게시글 삭제
  public PostDto deletePost(BigInteger posts_id) {
    Posts posts = getPost(posts_id);

    posts.setContent("Deleted");
    posts.setRemoved_date(LocalDateTime.now());

    postRepository.save(posts);

    return PostDto.fromEntity(posts);

  }

  // 게시글 수정
  public PostDto modifyPost(BigInteger posts_id, String title, String content) {
    Posts posts = getPost(posts_id);

    posts.setTitle(title);
    posts.setContent(content);
    posts.setModified_date(LocalDateTime.now());

    postRepository.save(posts);

    return PostDto.fromEntity(posts);
  }


  private User getUser(BigInteger userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("유저가 없습니다"));
    return user;
  }

  private Posts getPost(BigInteger postId) {
    Posts posts = postRepository.findById(postId)
        .orElseThrow(() -> new RuntimeException("게시글이 없습니다"));
    return posts;
  }

}
