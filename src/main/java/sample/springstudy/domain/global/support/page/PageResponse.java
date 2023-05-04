package sample.springstudy.domain.global.support.page;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Schema(description = "paging response model")
public class PageResponse<T> {

  @Schema(description = "페이지 당 게시물 수")
  private int pageSize;

  @Schema(description = "현재 페이지 번호")
  private int pageNumber;

  @Schema(description = "존재하는 총 페이지 수")
  private int totalPageNumber;

  @Schema(description = "존재하는 총 데이터 수")
  private Long totalSize;

  @Schema(description = "현재 페이지 데이터 목록")
  private List<T> list;

  public PageResponse(int pageSize, int pageNumber, int totalPageNumber, Long totalSize,
      List<T> list) {
    this.pageSize = pageSize;
    this.pageNumber = pageNumber;
    this.totalPageNumber = totalPageNumber;
    this.totalSize = totalSize;
    this.list = list;
  }

  public static <T> PageResponse<T> convert(Page<T> page) {
    PageResponse<T> response = new PageResponse<>();
    response.setPageNumber(page.getNumber() + 1);
    response.setPageSize(page.getSize());
    response.setTotalPageNumber(page.getTotalPages());
    response.setTotalSize(page.getTotalElements());
    response.setList(page.getContent());
    return response;
  }
}
