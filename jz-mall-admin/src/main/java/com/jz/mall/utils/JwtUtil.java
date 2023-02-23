package com.jz.mall.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Date;
import java.util.HashMap;


/**
 *  token生成/解析类
 *   token的组成:
 *   三部分: 头部(放签名算法) + payload(用户名 + 生成时间 + 过期时间) + signature(以前两部分为基础 生成的签名)
 */
public class JwtUtil {
    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    @Autowired
    UserDetails userDetails;

    @Value("${jwt.secret}")
    private String JWT_SECRET;//自定义私钥

    @Value("${jwt.expire}")
    private Long JWT_EXPIRE;

    private static final String USERNAME = "username";
    private static final String CREATEDTIME = "create_time";

    /**
     * 生成token,需要的几个要素
     *  1.用户信息: 需要包括用户名和用户密码,
     *       思考: token生成的时机,是在用户登录成功时需要生成返回的,解析token需要 验证的是用户名和密码是否和数据库中的一致? 还是验证同一username的token 是否在缓存中存在即可
     *  2.加密算法:
     *  3.私钥:
     *  4.token的过期时间
     */
    public String generatorToken(String username){
        String token = Jwts.builder()
                .setClaims(claims(userDetails))//用户信息(是否包含密码?)
                .signWith(SignatureAlgorithm.HS256,JWT_SECRET)//加密算法,常用的加密算法是哪个
                .setExpiration(generatorExpire())//过期时间
                .compact();
        return token;
    }


    /**
     *  根据用户信息生成Claims
     *   这里是根据username 还是 userDetails里的信息?
     *   答案是 根据userDetails里的信息,因为是登录成功后,每个用户登录成功会在上下文里存入相应的信息,不然这里的username怎么来?
     */
    //错误示范,不是要存放密码,而是要存放用户的相关信息(包括,用户名,创建时间)
    public HashMap<String,Object> claims(UserDetails userDetails){
        HashMap<String,Object> claims = new HashMap<>();
        claims.put(USERNAME,userDetails.getUsername());
        claims.put(CREATEDTIME,new Date());
//        map.put(); //是否要存放密码/权限
        return claims;
    }



    /**
     * 解析token,获取用户名
     *  1.不知道用户名,从请求头中获取传过来的token,通过与生成token时签名算法 相同的算法 从token中解析出用户信息(用户名)集合claims
     *          获取Claims的错误示范  Claims claims = Jwts.claims();//这样就可以? 肯定不对
     */
    public String parseUserNameFormToken(String token){
        if (token == null){
            return "token 为空";
        }
        return getClaimsFromToken(token).getSubject();//这里应该是取出用户名
    }

    //从token中获取payload信息
    public Claims getClaimsFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        if (claims == null){
            log.info("校验失败");
        }
        return claims;
    }

    /**
     * 验证token是否有效
     *       1.未过期
     *       2.用户信息一致(一个是从token中取出的username 一个是在上下文中保存的username)
     */
    public boolean verifyToken(String token){
        String usernameFromToken = parseUserNameFormToken(token);
        String username = userDetails.getUsername();
        return  !isPassed(token) && usernameFromToken.equalsIgnoreCase(username); //未过期 并且 用户名相等
    }

    //验证token是否过期
    public boolean isPassed(String token){
        //获取token中的过期过期时间和现在时刻进行对比
        //1.获取token中的生成时间
        Claims claims = getClaimsFromToken(token);
        Date expirationDate = claims.getExpiration();//过期的时间
        //比较现在和过期的时间,如果过期时间在现在时刻之前,说明过期了
        return expirationDate.before(new Date());
    }

    //刷新过期时间,重新生成token
    public void refesh(String token){
        if (isPassed(token)){
            Claims claims = getClaimsFromToken(token);
            claims.put(CREATEDTIME,new Date());
//            generatorToken(claims.getSubject()) //不用重新生成token,过期时间存放在claims里,直接覆盖值就好
        }
    }

    //生成过期时间,配置文件中的只是一个时间长度
    private Date generatorExpire() {
        return new Date(System.currentTimeMillis() + JWT_EXPIRE);
    }

}
