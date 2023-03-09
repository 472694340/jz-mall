package com.jz.mall.mongodb.service;

import com.jz.mall.mongodb.MemberReadHistory;

import java.util.List;

public interface MemberReadHistoryService {
    List<MemberReadHistory> getList(Long id);

    int create(MemberReadHistory readHistory);

    MemberReadHistory getOne(String historyId);

    int delete(List<String> ids);

}
