package sample.springstudy.domain.board.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.springstudy.domain.board.dto.BoardPaginatedResponseDto;
import sample.springstudy.domain.board.application.BoardService;
import sample.springstudy.domain.global.support.page.PageResponse;
import sample.springstudy.domain.global.support.utils.ApiResponse;
import sample.springstudy.domain.global.support.utils.ApiResponseGenerator;


@Tag(name = "board", description = "board sample api")
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApi {

  private final BoardService boardService;

  @GetMapping
  @Operation(summary = "게시물 목록 가져오기", description = "페이징 처리 된 게시물 목록을 반환합니다.")
  public ApiResponse<PageResponse<BoardPaginatedResponseDto>> getPaginatedBoardResponse(
      final @PageableDefault(
          size = 20,
          sort = "createDate",
          direction = Direction.ASC) Pageable pageable
  ) {
    return ApiResponseGenerator.success(PageResponse.convert(boardService.getPaginatedBoard(pageable)));
  }
}
