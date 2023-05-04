package sample.springstudy.domain.board.repository;

import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import sample.springstudy.domain.board.dto.BoardDto;
import sample.springstudy.domain.board.dto.QBoardDto;
import sample.springstudy.domain.board.entity.BoardEntity;
import sample.springstudy.domain.board.entity.QBoardEntity;

public class BoardRepositoryCustomImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom {

  private static final QBoardEntity BOARD_ENTITY = QBoardEntity.boardEntity;

  public BoardRepositoryCustomImpl() {
    super(BoardEntity.class);
  }

  @Override
  public Page<BoardDto> findAllByCondition(final Pageable pageable) {

    final var query = getPaginatedBoardResponseQuery();
    final var result = getQuerydsl()
        .applyPagination(pageable, query)
        .fetchResults();

    return new PageImpl<>(result.getResults(), pageable, result.getTotal());
  }

  private JPQLQuery<BoardDto> getPaginatedBoardResponseQuery() {
    return getQuerydsl()
        .createQuery()
        .select(new QBoardDto(BOARD_ENTITY))
        .from(BOARD_ENTITY);
  }
}
