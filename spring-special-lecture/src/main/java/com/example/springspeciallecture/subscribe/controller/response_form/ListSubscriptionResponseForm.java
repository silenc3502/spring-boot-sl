package com.example.springspeciallecture.subscribe.controller.response_form;

import com.example.springspeciallecture.subscribe.service.response.ListSubscriptionResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class ListSubscriptionResponseForm {
    private final List<Long> accountIdList;

    public static ListSubscriptionResponseForm from(ListSubscriptionResponse response) {
        List<Long> accountIdList = response.getSubscribedAccountList().stream()
                .map(account -> account.getId())
                .collect(Collectors.toList());

        return new ListSubscriptionResponseForm(accountIdList);
    }
}
