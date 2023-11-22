package service;

import domain.Posts;
import dto.PostDto;
import org.springframework.stereotype.Service;
import repository.PostRepository;

@Service
public class PostService {

  private final PostRepository postRepository;

  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  // 게시글 등록
  public PostDto addPost(Posts posts){
    return PostDto.fromEntity(postRepository.save(
        Posts.builder()
            .user(posts.getUser())
            .title(posts.getTitle())
            .content(posts.getContent())
            .created_date(posts.getCreated_date())
            .build()
    ));
  }

}
