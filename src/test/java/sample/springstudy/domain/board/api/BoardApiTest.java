package sample.springstudy.domain.board.api;

import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.test.web.servlet.MockMvc;
import sample.springstudy.domain.board.application.BoardService;
import sample.springstudy.domain.board.dto.BoardPaginatedResponseDto;
import sample.springstudy.domain.global.support.page.PageResponse;
import sample.springstudy.domain.global.support.utils.ApiResponseGenerator;

@DisplayName("board Api test")
@Import(HttpEncodingAutoConfiguration.class)
@WebMvcTest(controllers = BoardApi.class)
class BoardApiTest {

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BoardService boardService;

  @Test
  @DisplayName("페이징 처리 된 목록 반환 테스트")
  void testGetPaginatedBoardResponse() throws Exception {

    //given
    final var paging =
        PageRequest.of(0, 20, Sort.by(Order.asc("createDate")));

    final var responseDto = BoardPaginatedResponseDto.builder()
        .boardId(LONG_ONE)
        .boardTitle("테스트 제목")
        .createDate(LocalDateTime.now())
        .updateDate(LocalDateTime.now())
        .build();

    final var content = Collections.singletonList(responseDto);

    final var result = PageableExecutionUtils.getPage(content, paging, content::size);

    when(boardService.getPaginatedBoard(paging))
        .thenReturn(result);

    final var jsonString =
        objectMapper.writeValueAsString(ApiResponseGenerator.success(PageResponse.convert(result)));

    //when
    final var actualResult = mockMvc.perform(
        get("/api/board")
        .accept(APPLICATION_JSON_VALUE)
        .param("page", String.valueOf(paging.getPageNumber())))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    assertThat(actualResult).isEqualTo(jsonString);
  }
}