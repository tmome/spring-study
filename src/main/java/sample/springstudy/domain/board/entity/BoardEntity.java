package sample.springstudy.domain.board.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import sample.springstudy.domain.board.dto.BoardSaveSource;

@Getter
@NoArgsConstructor
@Entity(name = "board")
public class BoardEntity {

  /**
   * board table 식별자
   */
  @Id
  @Column(name = "board_id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long boardId;

  /**
   * 게시물 제목
   */
  @Column(name = "board_title", nullable = false)
  private String boardTitle;

  /**
   * 게시물 내용
   */
  @Column(name = "board_content", nullable = false, columnDefinition = "TEXT")
  private String boardContent;

  /**
   * 게시물 삭제 여부
   */
  @Column(name = "board_delete_yn", nullable = false)
  private boolean boardDeleteYn;

  /**
   * 게시물 생성 일자
   */
  @CreatedDate
  @Column(name = "create_date", nullable = false)
  private LocalDateTime createDate;

  /**
   * 게시물 수정 일자
   */
  @LastModifiedDate
  @Column(name = "update_date", nullable = false)
  private LocalDateTime updateDate;

  /**
   * 추후 확장성 고려 board_comment 테이블 호출 정보는 따로 End Point 로 분기 작업 진행 예정 (연관 관계 제거 및 댓글 페이징 처리)
   */
//  @OneToMany(
//      mappedBy = "board",
//      fetch = FetchType.LAZY,
//      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
//      orphanRemoval = true
//  )
//  private Set<BoardCommentEntity> comments = new LinkedHashSet<>();

  @Builder
  public BoardEntity(
      Long boardId,
      String boardTitle,
      String boardContent,
      boolean boardDeleteYn,
      LocalDateTime createDate,
      LocalDateTime updateDate
  ) {
    this.boardId = boardId;
    this.boardTitle = boardTitle;
    this.boardContent = boardContent;
    this.boardDeleteYn = boardDeleteYn;
    this.createDate = createDate;
    this.updateDate = updateDate;
  }

  public static BoardEntity saveBuildOf(final BoardSaveSource boardSaveSource) {
    return BoardEntity.builder()
        .boardTitle(boardSaveSource.getBoardTitle())
        .boardContent(boardSaveSource.getBoardContent())
        .boardDeleteYn(Boolean.FALSE)
        .createDate(LocalDateTime.now())
        .updateDate(LocalDateTime.now())
        .build();
  }
}
