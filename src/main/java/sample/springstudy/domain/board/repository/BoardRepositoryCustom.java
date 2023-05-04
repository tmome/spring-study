package sample.springstudy.domain.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sample.springstudy.domain.board.dto.BoardDto;

public interface BoardRepositoryCustom {

  Page<BoardDto> findAllByCondition(final Pageable pageable);

}
