package sample.springstudy.domain.board.application;

import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.springstudy.domain.board.dto.BoardContentsResponseDto;
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

  @Transactional(readOnly = true)
  public BoardContentsResponseDto getBoardContent(final Long boardId) {
    return BoardContentsResponseDto.of(boardRepository
        .findById(boardId)
        .orElseThrow(EntityNotFoundException::new));
  }
}
