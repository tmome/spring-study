package sample.springstudy.domain.global.handler;


import com.sun.jdi.request.InvalidRequestStateException;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sample.springstudy.domain.global.exception.BusinessException;
import sample.springstudy.domain.global.support.code.ErrorCode;
import sample.springstudy.domain.global.support.utils.ApiResponse;
import sample.springstudy.domain.global.support.utils.ApiResponseGenerator;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler({
      EntityNotFoundException.class,
      InvalidRequestStateException.class
  })
  public ApiResponse<Void> dataException(final BusinessException businessException) {
    return businessException(businessException);
  }

  public ApiResponse<Void> businessException(final BusinessException businessException) {
    return ApiResponseGenerator.fail(ErrorCode.BAD_REQUEST_ERROR, businessException.getMessage());
  }
}
