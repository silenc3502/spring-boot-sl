package com.example.springspeciallecture.reactive_board.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class ReactiveBoard {

    @Id
    private Long boardId;

    private String title;

    private Long writerId; // R2DBC에서는 ManyToOne 불가 → FK만 저장

    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime createDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime updateDate;

    // 생성자 (새 Board 만들 때만 사용)
    public ReactiveBoard(String title, Long writerId, String content) {
        this.title = title;
        this.writerId = writerId;
        this.content = content;
        this.createDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    // 값 변경 메서드 (Setter 안 쓰고)
    public ReactiveBoard changeTitle(String title) {
        this.title = title;
        this.updateDate = LocalDateTime.now();
        return this;
    }

    public ReactiveBoard changeContent(String content) {
        this.content = content;
        this.updateDate = LocalDateTime.now();
        return this;
    }
}
