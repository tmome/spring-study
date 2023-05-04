package sample.springstudy.domain.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.springstudy.domain.board.entity.BoardEntity;

@Getter
@NoArgsConstructor
public class BoardDto {

  @Schema(description = "게시물 번호")
  private Long boardId;

  @Schema(description = "게시물 제목")
  private String boardTitle;

  @Schema(description = "게시물 생성일")
  private LocalDateTime createDate;

  @Schema(description = "게시물 수정일")
  private LocalDateTime updateDate;

//  @Schema(description = "게시물 댓글 목록")
//  List<BoardCommentDto> boardCommentList;

  @QueryProjection
  public BoardDto(final BoardEntity boardEntity) {
    this.boardId = boardEntity.getBoardId();
    this.boardTitle = boardEntity.getBoardTitle();
    this.createDate = boardEntity.getCreateDate();
    this.updateDate = boardEntity.getUpdateDate();
  }
}
