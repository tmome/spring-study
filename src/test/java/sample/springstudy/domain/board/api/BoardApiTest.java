package sample.springstudy.domain.board.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;

@DisplayName("board Api test")
@Import(HttpEncodingAutoConfiguration.class)
@WebMvcTest(controllers = BoardApi.class)
class BoardApiTest {

}