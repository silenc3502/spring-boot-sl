package com.example.springspeciallecture.board.service;

import com.example.springspeciallecture.board.service.request.CreateBoardRequest;
import com.example.springspeciallecture.board.service.request.ListBoardRequest;
import com.example.springspeciallecture.board.service.request.UpdateBoardRequest;
import com.example.springspeciallecture.board.service.response.CreateBoardResponse;
import com.example.springspeciallecture.board.service.response.ListBoardResponse;
import com.example.springspeciallecture.board.service.response.ReadBoardResponse;
import com.example.springspeciallecture.board.service.response.UpdateBoardResponse;

public interface BoardService {
    ListBoardResponse list(ListBoardRequest request);
    CreateBoardResponse register(CreateBoardRequest createBoardRequest);
    ReadBoardResponse read(Long boardId);
    UpdateBoardResponse update(Long boardId, Long accountId, UpdateBoardRequest updateBoardRequest);
    void delete(Long boardId, Long accountId);
}
