package zerobase.boardproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.math.BigInteger;
import java.sql.Timestamp;
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
//            .title(anyString())
//            .content(anyString())
            .build());
    ArgumentCaptor<Posts> captor = ArgumentCaptor.forClass(Posts.class);

      //when
    PostDto postDto = postService.createPost(BigInteger.valueOf(12), "subject", "zerobase");

      //then
    verify(postRepository).save(captor.capture());
    assertEquals(BigInteger.valueOf(12), postDto.getUserId());
    assertEquals("subject", captor.getValue().getTitle());
    assertEquals("zerobase", captor.getValue().getContent());

//    assertEquals("subject", postDto.getTitle());
//    assertEquals("zerobase", postDto.getContent());

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
        .createdDate(Timestamp.valueOf(LocalDateTime.of(2023,1,29,4,50)))
        .build();
    given(postRepository.findById(any(BigInteger.class)))
        .willReturn(Optional.of(posts));
    ArgumentCaptor<Posts> captor = ArgumentCaptor.forClass(Posts.class);

      //when
    PostDto postDto = postService.deletePost(BigInteger.valueOf(12), BigInteger.valueOf(13));

      //then
    verify(postRepository).save(captor.capture());
    assertEquals(BigInteger.valueOf(13), postDto.getPostId());
    assertEquals("Deleted", postDto.getContent());
    assertEquals(Timestamp.valueOf(LocalDateTime.of(2023,1,29,4,50)),
        postDto.getCreatedDate());
    assertNotNull(postDto.getRemovedDate());

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
    assertEquals(BigInteger.valueOf(13), postDto.getPostId());
    assertEquals("Modified title", postDto.getTitle());
    assertEquals("modified content", postDto.getContent());
    assertNotNull(postDto.getModifiedDate());

  }

  @Test
  void readPost() {
      //given
    User user = User.builder()
        .nickname("wonwoo").build();
    user.setId(BigInteger.valueOf(12));
    Posts posts = Posts.builder()
        .id(BigInteger.valueOf(13))
        .user(user)
        .title("subject")
        .content("zerobase").build();
    given(postRepository.findById(any(BigInteger.class)))
        .willReturn(Optional.of(posts));

      //when
    PostDto postDto = postService.readPost(BigInteger.valueOf(13));

      //then
    assertEquals(BigInteger.valueOf(12), postDto.getUserId());
    assertEquals("subject", postDto.getTitle());
    assertEquals("zerobase", postDto.getContent());

  }

}