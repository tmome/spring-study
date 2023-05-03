package sample.springstudy.domain.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sample.springstudy.domain.board.dto.BoardPaginatedResponseDto;

public interface BoardRepositoryCustom {

  Page<BoardPaginatedResponseDto> findAllByCondition(final Pageable pageable);

}
