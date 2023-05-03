INSERT INTO board (board_id, board_title, board_content, board_delete_yn, create_date, update_date)
VALUES (1, '첫번째 게시물', '첫번째 게시물 내용', true, current_timestamp(), current_timestamp());

INSERT INTO board_comment(board_id, board_comment_contents, board_comment_delete_yn, create_date, update_date)
VALUES (1, '첫번째 게시물 첫번째 댓글', true, current_timestamp(), current_timestamp());