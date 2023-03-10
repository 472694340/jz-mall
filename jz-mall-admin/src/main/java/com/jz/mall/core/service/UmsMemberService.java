package com.jz.mall.core.service;

import com.jz.mall.core.domain.model.UmsMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author ShenLiang
 * @since 2023-02-21
 */
public interface UmsMemberService extends IService<UmsMember> {

    String authCode(String phoneNumber);

    boolean verify(String authCode, String phoneNumber);
}
