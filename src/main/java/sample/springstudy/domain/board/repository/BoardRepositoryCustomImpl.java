package sample.springstudy.domain.board.repository;

import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import sample.springstudy.domain.board.dto.BoardPaginatedResponseDto;
import sample.springstudy.domain.board.entity.BoardEntity;
import sample.springstudy.domain.board.entity.QBoardEntity;

public class BoardRepositoryCustomImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom {

  private static final QBoardEntity boardEntity = QBoardEntity.boardEntity;

  public BoardRepositoryCustomImpl() {
    super(BoardEntity.class);
  }

  @Override
  public Page<BoardPaginatedResponseDto> findAllByCondition(Pageable pageable) {
    return null;
  }

  private JPQLQuery<BoardPaginatedResponseDto> getPaginatedBoardResponseQuery() {
    return null;
  }
}
