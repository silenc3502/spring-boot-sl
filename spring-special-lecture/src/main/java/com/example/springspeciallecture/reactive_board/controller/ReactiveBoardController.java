package com.example.springspeciallecture.reactive_board.controller;

import com.example.springspeciallecture.reactive_board.controller.request_form.ListReactiveBoardRequestForm;
import com.example.springspeciallecture.reactive_board.controller.response_form.ListReactiveBoardResponseForm;
import com.example.springspeciallecture.reactive_board.service.ReactiveBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reactive-board")
public class ReactiveBoardController {

    final private ReactiveBoardService reactiveBoardService;

    @GetMapping("/list")
    public Mono<ListReactiveBoardResponseForm> boardList(@ModelAttribute ListReactiveBoardRequestForm requestForm) {
        return reactiveBoardService.list(requestForm.toListReactiveBoardRequest())
                .map(response -> ListReactiveBoardResponseForm.from(
                        List.of(response),
                        (int) response.getTotalItems(),
                        response.getTotalPages()
                ));
    }
}
