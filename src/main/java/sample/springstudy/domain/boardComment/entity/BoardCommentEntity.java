package sample.springstudy.domain.boardComment.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import sample.springstudy.domain.board.entity.BoardEntity;

@Getter
@NoArgsConstructor
@Entity(name = "board_comment")
public class BoardCommentEntity {

  /**
   * board table 식별자
   */
  @Id
  @Column(name = "board_id", nullable = false)
  private Long boardId;

  /**
   * 댓글 내용
   */
  @Column(name = "board_comment_contents", nullable = false)
  private String boardCommentContents;

  @Column(name = "board_comment_delete_yn", nullable = false)
  private boolean boardCommentDeleteYn;

  /**
   * 댓글 생성 일자
   */
  @CreatedDate
  @Column(name = "create_date", nullable = false)
  private LocalDateTime createDate;

  /**
   * 댓글 수정 일자
   */
  @LastModifiedDate
  @Column(name = "update_date", nullable = false)
  private LocalDateTime updateDate;

  @MapsId
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "board_id", referencedColumnName = "board_id")
  private BoardEntity board;
}
