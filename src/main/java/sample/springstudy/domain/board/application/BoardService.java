package sample.springstudy.domain.board.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.springstudy.domain.board.dto.BoardPaginatedResponseDto;
import sample.springstudy.domain.board.repository.BoardRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;

  @Transactional(readOnly = true)
  public Page<BoardPaginatedResponseDto> getPaginatedBoard(final Pageable pageable) {
    return boardRepository.findAllByCondition(pageable)
        .map(BoardPaginatedResponseDto::of);
  }
}
