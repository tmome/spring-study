package sample.springstudy.domain.board.entity;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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

  @OneToMany(
      mappedBy = "board",
      fetch = FetchType.LAZY,
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
      orphanRemoval = true
  )
  private Set<BoardCommentEntity> comments = new LinkedHashSet<>();

}
