package zerobase.boardproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

  USERLOGINID_DUPLICATED(HttpStatus.CONFLICT, ""),
  USERLOGINID_NOT_FOUND(HttpStatus.NOT_FOUND, ""),
  INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, ""),
  TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, ""),
  USER_UNMACHED(HttpStatus.FORBIDDEN,""),
  USERID_NOT_FOUND(HttpStatus.NOT_FOUND, ""),
  POSTID_NOT_FOUND(HttpStatus.NOT_FOUND, ""),
  COMMENTID_NOT_FOUND(HttpStatus.NOT_FOUND, "");

  private HttpStatus httpStatus;
  private String message;

}
