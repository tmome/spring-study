package sample.springstudy.domain.board.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(description = "게시물 저장 모델")
@NoArgsConstructor
public class BoardSaveSource {

  @NotBlank
  @Schema(description = "제목", example = "게시물 제목", required = true)
  private String boardTitle;

  @NotBlank
  @Schema(description = "내용", example = "게시물 내용", required = true)
  private String boardContent;
}
