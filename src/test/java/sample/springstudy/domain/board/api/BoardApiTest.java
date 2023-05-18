package sample.springstudy.domain.board.api;

import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sample.springstudy.domain.board.dto.BoardPaginatedResponseDto.builder;

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
import sample.springstudy.domain.board.dto.BoardContentsResponseDto;
import sample.springstudy.domain.board.dto.BoardSaveSource;
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

    final var responseDto = builder()
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

  @Test
  @DisplayName("특정 게시물 조회")
  void testGetBoardContent() throws Exception {
    //given
    final var boardId = LONG_ONE;

    final var result = BoardContentsResponseDto.builder()
        .boardId(LONG_ONE)
        .boardTitle("테스트 게시물")
        .createDate(LocalDateTime.now())
        .updateDate(LocalDateTime.now())
        .boardContent("테스트 내용")
        .build();

    when(boardService.getBoardContent(boardId))
        .thenReturn(result);

    //when
    final var jsonString = objectMapper.writeValueAsString(ApiResponseGenerator.success(result));

    final var actualResult = mockMvc.perform(
        get("/api/board/1")
            .accept(APPLICATION_JSON_VALUE))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    //then
    assertThat(actualResult).isEqualTo(jsonString);
  }

  @Test
  @DisplayName("특정 게시물 생성 테스트")
  void testInsertBoard() throws Exception {

    //given
    final var boardSaveSource = BoardSaveSource
        .builder()
        .boardTitle("테스트 게시물")
        .boardContent("테스트 컨텐츠")
        .build();

    final var boardContentResponseDto = BoardContentsResponseDto.builder()
        .boardId(LONG_ONE)
        .boardTitle("테스트 게시물")
        .boardContent("테스트 컨텐츠")
        .createDate(LocalDateTime.now())
        .updateDate(LocalDateTime.now())
        .build();

    when(boardService.insertBoard(boardSaveSource))
        .thenReturn(boardContentResponseDto);

    final var jsonString =
        objectMapper.writeValueAsString(ApiResponseGenerator.success(boardContentResponseDto));

    // when
    final var actualResult = mockMvc.perform(
            post("/api/board")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(boardContentResponseDto))
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    //then
    assertThat(actualResult).isEqualTo(jsonString);
  }
}