package sample.springstudy.domain.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.springstudy.domain.board.entity.BoardEntity;

@Schema(description = "board selected response dto")
@Getter
@NoArgsConstructor
public class BoardContentsResponseDto {
  @Schema(description = "게시물 번호")
  private Long boardId;

  @Schema(description = "게시물 제목")
  private String boardTitle;

  @Schema(description = "게시물 생성일")
  private LocalDateTime createDate;

  @Schema(description = "게시물 수정일")
  private LocalDateTime updateDate;

  @Schema(description = "게시물 내용")
  private String boardContent;

  @Builder
  public BoardContentsResponseDto(
      Long boardId,
      String boardTitle,
      LocalDateTime createDate,
      LocalDateTime updateDate,
      String boardContent
  ) {
    this.boardId = boardId;
    this.boardTitle = boardTitle;
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.boardContent = boardContent;
  }

  public static BoardContentsResponseDto of(final BoardEntity boardEntity) {
    return BoardContentsResponseDto.builder()
        .boardId(boardEntity.getBoardId())
        .boardTitle(boardEntity.getBoardTitle())
        .createDate(boardEntity.getCreateDate())
        .updateDate(boardEntity.getUpdateDate())
        .boardContent(boardEntity.getBoardContent())
        .build();
  }
}
