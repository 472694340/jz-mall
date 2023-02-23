package com.jz.mall.generator.controller;

import com.jz.mall.generator.common.CommonResult;
import com.jz.mall.generator.model.UmsMember;
import com.jz.mall.generator.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author ShenLiang
 * @since 2023-02-21
 */
@RestController
@RequestMapping("/umsMember")
@Api(tags = "UmsMemberController",description = "会员管理")
public class UmsMemberController {
    /**
     * 1.根据电话号码获取验证码信息
     * 2.校验验证码
     */

    @Autowired
    UmsMemberService memberService;

    /**
     *  根据手机号,生成验证码返回
     * @param phoneNumber
     * @return
     */
    @GetMapping("authCode")
    @ApiOperation("获取验证码")
    public CommonResult authCode(@RequestParam(value = "phoneNumber")String phoneNumber){
        String authCode = memberService.authCode(phoneNumber);
        return CommonResult.success(authCode);
    }

    @ApiOperation("校验验证码")
    @GetMapping("verify")
    public CommonResult verify(@RequestParam(value = "authCode")String authCode,@RequestParam(value = "phoneNumber") String phoneNumber){
        if (StringUtils.isEmpty(authCode)){
            return CommonResult.failed("请输入验证码");
        }
       boolean isPass = memberService.verify(authCode,phoneNumber);
       return isPass? CommonResult.success("验证成功"):CommonResult.failed("验证失败");
    }


}
