package sample.springstudy.domain.boardComment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardCommentDto {

  @Schema(description = "게시물 번호")
  private Long boardId;

  @Schema(description = "댓글 내용")
  private String boardCommentContents;

  @Schema(description = "게시물 생성일")
  private LocalDateTime createDate;

  @Schema(description = "게시물 수정일")
  private LocalDateTime updateDate;
}
