package zerobase.boardproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zerobase.boardproject.domain.Posts;
import zerobase.boardproject.domain.User;
import zerobase.boardproject.dto.PostDto;
import zerobase.boardproject.repository.PostRepository;
import zerobase.boardproject.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

  @InjectMocks
  private PostService postService;

  @Mock
  private PostRepository postRepository;

  @Mock
  private UserRepository userRepository;

  @Test
  void createPost() {
      //given
    User user = User.builder()
        .nickname("wonwoo").build();
    user.setId(BigInteger.valueOf(12));
    given(userRepository.findById(any(BigInteger.class)))
        .willReturn(Optional.of(user));
    given(postRepository.save(any()))
        .willReturn(Posts.builder()
            .user(user)
            .build());
    ArgumentCaptor<Posts> captor = ArgumentCaptor.forClass(Posts.class);

      //when
    PostDto postDto = postService.createPost(BigInteger.valueOf(12), "subject", "zerobase");

      //then
    verify(postRepository).save(captor.capture());
    assertEquals(BigInteger.valueOf(12), postDto.getUser_id());
    assertEquals("subject", captor.getValue().getTitle());
    assertEquals("zerobase", captor.getValue().getContent());

  }

  @Test
  void deletePost() {
      //given
    User user = User.builder()
        .id(BigInteger.valueOf(12)).build();
    Posts posts = Posts.builder()
        .user(user)
        .id(BigInteger.valueOf(13))
        .title("Sorry")
        .content("GoodBye")
        .created_date(LocalDateTime.of(2023,1,29,4,50))
        .build();
    given(postRepository.findById(any(BigInteger.class)))
        .willReturn(Optional.of(posts));
    ArgumentCaptor<Posts> captor = ArgumentCaptor.forClass(Posts.class);

      //when
    PostDto postDto = postService.deletePost(BigInteger.valueOf(13));

      //then
    verify(postRepository).save(captor.capture());
    assertEquals(BigInteger.valueOf(13), postDto.getPost_id());
    assertEquals("Deleted", postDto.getContent());
    assertEquals(LocalDateTime.of(2023,1,29,4,50),
        postDto.getCreated_date());
    assertNotNull(postDto.getRemoved_date());

  }

  @Test
  void modifyPost() {
      //given
    User user = User.builder().id(BigInteger.valueOf(12)).build();
    Posts posts = Posts.builder()
        .user(user)
        .id(BigInteger.valueOf(13))
        .title("WOW")
        .content("perfect")
        .build();
    given(postRepository.findById(any(BigInteger.class)))
        .willReturn(Optional.of(posts));
    ArgumentCaptor<Posts> captor = ArgumentCaptor.forClass(Posts.class);

      //when
    PostDto postDto = postService.modifyPost(BigInteger.valueOf(13), "Modified title", "modified content");

      //then
    verify(postRepository).save(captor.capture());
    assertEquals(BigInteger.valueOf(13), postDto.getPost_id());
    assertEquals("Modified title", postDto.getTitle());
    assertEquals("modified content", postDto.getContent());
    assertNotNull(postDto.getModified_date());

  }

}