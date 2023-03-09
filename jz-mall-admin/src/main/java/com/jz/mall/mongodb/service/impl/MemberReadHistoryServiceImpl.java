package com.jz.mall.mongodb.service.impl;

import com.jz.mall.mongodb.MemberReadHistory;
import com.jz.mall.mongodb.repository.MemberReadHistoryRepository;
import com.jz.mall.mongodb.service.MemberReadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class MemberReadHistoryServiceImpl implements MemberReadHistoryService {

    @Autowired
    MemberReadHistoryRepository readHistoryRepository;

    /**
     * 直接从MongoDB里查数据,说明存也是直接存在MongoDB里,和数据库没有关系
     * @param id
     * @return
     */
    @Override
    public List<MemberReadHistory> getList(Long id) {
            return readHistoryRepository.findByMemberIdOrderByMemberIdDesc(id);
    }

    /**
     * 直接存,返回1,那就是不管什么情况下都会成功?这里是不是有问题
     * @param readHistory
     * @return
     */
    @Override
    public int create(MemberReadHistory readHistory) {
        readHistory.setId(null);
        readHistory.setCreateTime(new Date());
        readHistoryRepository.save(readHistory);
        return 1;
    }

    @Override
    public MemberReadHistory getOne(String historyId) {
        return readHistoryRepository.findById(historyId).get();
    }

    @Override
    public int delete(List<String> ids) {
        List<MemberReadHistory> deleteList = new ArrayList<>();
        for (String id : ids) {
            MemberReadHistory history = new MemberReadHistory();
            history.setId(id);
            deleteList.add(history);
        }

        readHistoryRepository.deleteAll(deleteList);
        return ids.size();
    }
}
