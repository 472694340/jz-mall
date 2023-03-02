package com.jz.mall.core.controller;

import com.jz.mall.core.domain.model.UmsMenu;
import com.jz.mall.core.domain.model.UmsPermission;
import com.jz.mall.common.CommonResult;
import com.jz.mall.core.domain.dto.UmsAdminLoginParam;
import com.jz.mall.core.domain.model.UmsAdmin;
import com.jz.mall.core.domain.model.UmsRole;
import com.jz.mall.core.service.UmsAdminService;
import com.jz.mall.core.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author ShenLiang
 * @since 2023-02-21
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "UmsAdminController",description = "后台用户管理")
public class UmsAdminController {
    @Autowired
    UmsAdminService adminService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    UmsRoleService umsRoleService;

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result, HttpServletResponse response) {
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.unauthorized();
        }

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }


    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdmin umsAdminParam, BindingResult result) {
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if (umsAdmin == null) {
            CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);
    }

    @ApiOperation("获取用户权限")
    @GetMapping("getPermissions/{adminId}")
    public CommonResult<List<UmsPermission>> getPermissions(@PathVariable Long adminId){
        List<UmsPermission> permissionList = adminService.getPermissionList(adminId);
        if (!permissionList.isEmpty()){
            return CommonResult.success(permissionList);
        }
        return CommonResult.failed();
    }

    /**
     * 返回动态路由菜单列表
     * 由于是SpringSecurity里存了用户的信息,所以不用传用户名什么的
     * @return
     */
    @GetMapping("info")
    public CommonResult getInfo(Principal principal){
        if (principal == null){
            return CommonResult.unauthorized();
        }
        HashMap<String,Object> map = new HashMap<>();
        UmsAdmin umsAdmin = adminService.getUserByName(principal.getName());
        List<UmsMenu> menus = umsRoleService.getMenusList(umsAdmin.getId());
        List<UmsRole> rolesList = adminService.getRoleList(umsAdmin.getId());
        List<String> roles = rolesList.stream().map(UmsRole::getName).collect(Collectors.toList());
        map.put("icon",umsAdmin.getIcon());
        map.put("menus",menus);
        map.put("roles",roles);
        map.put("username",umsAdmin.getUsername());
        return CommonResult.success(map);
    }

    /**
     * 登出功能
     * 思考:是不是清除掉用户信息,然后重定向?
     */
    @PostMapping("logout")
    public CommonResult logout(){
        return CommonResult.success();
    }

}
