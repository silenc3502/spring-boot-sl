CREATE TABLE reactive_board (
    board_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    writer_id BIGINT,
    content TEXT,
    create_date TIMESTAMP,
    update_date TIMESTAMP
);
