package sample.springstudy.domain.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "board paginated response dto")
@Getter
@NoArgsConstructor
public class BoardPaginatedResponseDto {

  @Schema(description = "게시물 번호")
  private Long boardId;

  @Schema(description = "게시물 제목")
  private String boardTitle;

  @Schema(description = "게시물 생성일")
  private LocalDateTime createDate;

  @Schema(description = "게시물 수정일")
  private LocalDateTime updateDate;

  @Builder
  public BoardPaginatedResponseDto(
      Long boardId,
      String boardTitle,
      LocalDateTime createDate,
      LocalDateTime updateDate
  ) {
    this.boardId = boardId;
    this.boardTitle = boardTitle;
    this.createDate = createDate;
    this.updateDate = updateDate;
  }

  public static BoardPaginatedResponseDto of(final BoardDto boardDto) {
    return BoardPaginatedResponseDto.builder()
        .boardId(boardDto.getBoardId())
        .boardTitle(boardDto.getBoardTitle())
        .createDate(boardDto.getCreateDate())
        .updateDate(boardDto.getUpdateDate())
        .build();
  }
}
