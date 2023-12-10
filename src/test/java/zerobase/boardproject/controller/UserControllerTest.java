//package zerobase.boardproject.controller;
//
//import static jdk.internal.vm.compiler.word.LocationIdentity.any;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.anyList;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.BDDMockito.given;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import zerobase.boardproject.domain.User;
//import zerobase.boardproject.dto.AuthDto;
//import zerobase.boardproject.service.UserService;
//
//@WebMvcTest(UserController.class)
//class UserControllerTest {
//
//  @MockBean
//  private UserService userService;
//
//  @Autowired
//  private MockMvc mockMvc;
//
//  @Autowired
//  private ObjectMapper objectMapper;
//
//  @Test
//  void signUp() throws AppException {
//
//      //given
//    AuthDto.SignUp dto = AuthDto.SignUp.builder()
//            .userLoginId("wonwoo")
//                .password("123456789")
//                    .nickname("zero").build();
//    given(userService.register(dto))
//
//      //when
//      //then
//  }
//
//}