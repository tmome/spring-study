package sample.springstudy.domain.global.support.code;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ErrorCode {

  SUCCESS("2000", "OK"),
  UNAUTHORIZED_ERROR("9001", "인증 오류"),
  BAD_REQUEST_ERROR("9002", "부적절한 요청 오류"),
  UNKNOWN_ERROR("9999", "알 수 없는 오류");

  private final String code;
  private final String defaultMessage;

  ErrorCode(String code, String defaultMessage) {
    this.code = code;
    this.defaultMessage = defaultMessage;
  }

}
