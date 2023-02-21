package com.jz.mall.generator.service.impl;

import cn.hutool.setting.dialect.Props;
import com.jz.mall.generator.model.UmsMember;
import com.jz.mall.generator.mapper.UmsMemberMapper;
import com.jz.mall.generator.service.RedisService;
import com.jz.mall.generator.service.UmsMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author ShenLiang
 * @since 2023-02-21
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements UmsMemberService {

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX;

    @Value("${redis.key.expire.authoCode}")
    private String REDIS_KEY_EXPIRE;

    @Autowired
    RedisServiceImpl redisService;

    @Override
    public String authCode(String phoneNumber) {
//        Props props = new Props("application.yml");
//        String preAuthCode = props.getStr("authCode");
//        String expireAuthCode = props.getStr("authCode");


        //先生成六位数的随机数
        StringBuffer str = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str = str.append(random.nextInt(9));
        }
        redisService.set(REDIS_KEY_PREFIX + phoneNumber, String.valueOf(str));
        redisService.setExpire(REDIS_KEY_PREFIX + phoneNumber , Long.valueOf(REDIS_KEY_EXPIRE));
        return str.toString();
    }
}
