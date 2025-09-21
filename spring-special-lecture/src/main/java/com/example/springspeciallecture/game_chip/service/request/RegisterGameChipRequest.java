package com.example.springspeciallecture.game_chip.service.request;

import com.example.springspeciallecture.account.entity.Account;
import com.example.springspeciallecture.game_chip.entity.GameChip;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterGameChipRequest {
    private final String title;
    private final String description;
    private final int price;

    private final Long accountId;

    public GameChip toGameChip(Account account) {
        return new GameChip(title, description, price, account);
    }
}
