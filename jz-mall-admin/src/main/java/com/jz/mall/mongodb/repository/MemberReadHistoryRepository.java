package com.jz.mall.mongodb.repository;

import com.jz.mall.mongodb.MemberReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory,String> {
    List<MemberReadHistory> findByMemberIdOrderByMemberIdDesc(Long memberId);
}
