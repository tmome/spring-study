CREATE TABLE IF NOT EXISTS board (
    board_id BIGINT(5) AUTO_INCREMENT PRIMARY KEY COMMENT '게시물 식별자',
    board_title VARCHAR(200) NOT NULL COMMENT '게시물 제목',
    board_content TEXT NOT NULL COMMENT '게시물 내용',
    board_delete_yn BIT(1) NOT NULL DEFAULT 0 COMMENT '게시물 삭제여부',
    create_date DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '게시물 생성일',
    update_date DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '게시물 최종수정일'
);
COMMENT ON TABLE board is '게시물 테이블';

CREATE TABLE IF NOT EXISTS board_comment (
    board_id BIGINT(5) PRIMARY KEY COMMENT '게시물 번호',
    board_comment_contents VARCHAR(200) NOT NULL COMMENT '댓글 컨텐츠 내용',
    board_comment_delete_yn BIT(1) NOT NULL DEFAULT 0 COMMENT '댓글 삭제여부',
    create_date DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '댓글 생성일',
    update_date DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '댓글 최종수정일'
);
COMMENT ON TABLE board_comment is '게시물 댓글 테이블';
