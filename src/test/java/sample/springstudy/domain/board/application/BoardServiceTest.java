package sample.springstudy.domain.board.application;

import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static sample.springstudy.domain.board.dto.BoardPaginatedResponseDto.builder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sample.springstudy.domain.board.dto.BoardContentsResponseDto;
import sample.springstudy.domain.board.dto.BoardDto;
import sample.springstudy.domain.board.dto.BoardPaginatedResponseDto;
import sample.springstudy.domain.board.entity.BoardEntity;
import sample.springstudy.domain.board.repository.BoardRepository;

@DisplayName("application Service Layer Test")
@ExtendWith(SpringExtension.class)
class BoardServiceTest {

  @MockBean
  private BoardRepository boardRepository;

  private BoardService boardService;

  @BeforeEach
  public void init() {
    boardService = new BoardService(boardRepository);
  }

  @Test
  @DisplayName("어플리케이션 Layer 페이징 처리 Service Test")
  void testGetPaginatedBoardResponse() {
    //given
    final var paging =
        PageRequest.of(0, 20,
            Sort.by(Order.asc("createDate")));

    final var responseDto = BoardDto.builder()
        .boardId(LONG_ONE)
        .boardTitle("테스트 제목")
        .createDate(LocalDateTime.now())
        .updateDate(LocalDateTime.now())
        .build();

    final var content = Collections.singletonList(responseDto);
    final var result = PageableExecutionUtils.getPage(content, paging, content::size);

    when(boardRepository.findAllByCondition(paging))
        .thenReturn(result);

    //when
    final var actualResult = boardService.getPaginatedBoard(paging);

    //when
    assertThat(actualResult).isNotEmpty();
    assertThat(actualResult.getContent())
        .hasOnlyElementsOfType(BoardPaginatedResponseDto.class)
        .extracting("boardTitle")
        .contains("테스트 제목");
  }

  @Test
  @DisplayName("application Layer 특정 게시물 조회")
  void testGetBoardContent() {
    final var boardId = LONG_ONE;

    final var result = BoardEntity.builder()
        .boardId(LONG_ONE)
        .boardTitle("테스트 게시물")
        .createDate(LocalDateTime.now())
        .updateDate(LocalDateTime.now())
        .boardContent("테스트 내용")
        .build();


    when(boardRepository.findById(boardId))
        .thenReturn(Optional.ofNullable(result));

    assertThat(result)
        .isNotNull()
        .isInstanceOf(BoardEntity.class)
        .extracting("boardId")
        .isEqualTo(boardId);
  }
}