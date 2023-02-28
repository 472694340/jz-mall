package com.jz.mall.generator.service.impl;

import cn.hutool.setting.dialect.Props;
import com.jz.mall.generator.model.UmsMember;
import com.jz.mall.generator.mapper.UmsMemberMapper;
import com.jz.mall.generator.service.RedisService;
import com.jz.mall.generator.service.UmsMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import freemarker.core.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(UmsMemberServiceImpl.class);

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX;

    @Value("${redis.key.expire.authCodeExpire}")
    private String REDIS_KEY_EXPIRE;

    @Autowired
    RedisServiceImpl redisService;

    @Autowired
    UmsMemberMapper umsMemberMapper;

    @Override
    public String authCode(String phoneNumber) {
        //获取配置文件里信息的另一种方式
//        Props props = new Props("application.yml");
//        String preAuthCode = props.getStr("authCode");
//        String expireAuthCode = props.getStr("authCodeExpire");
//        log.info("preAuthCode: " + preAuthCode);//"portal:authCode:"
//        log.info("expireAuthCode: " + expireAuthCode);//120

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

    @Override
    public boolean verify(String authCode, String phoneNumber) {
        String redisCode = redisService.get(REDIS_KEY_PREFIX + phoneNumber);
        if (authCode.equalsIgnoreCase(redisCode)){
            return true;
        }
        return false;
    }


}
