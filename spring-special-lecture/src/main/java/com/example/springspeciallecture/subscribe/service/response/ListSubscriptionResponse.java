package com.example.springspeciallecture.subscribe.service.response;

import com.example.springspeciallecture.account.entity.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class ListSubscriptionResponse {
    private final List<Account> subscribedAccountList;

    public List<Long> getSubscribedAccountIdList() {
        return subscribedAccountList.stream()
                .map(Account::getId)
                .collect(Collectors.toList());
    }

    public static ListSubscriptionResponse from(List<Account> accountList) {
        return new ListSubscriptionResponse(accountList);
    }
}
