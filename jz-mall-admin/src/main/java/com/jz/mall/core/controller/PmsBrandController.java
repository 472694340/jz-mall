package com.jz.mall.core.controller;

import com.jz.mall.common.CommonPage;
import com.jz.mall.common.CommonResult;
import com.jz.mall.core.domain.model.PmsBrand;
import com.jz.mall.core.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author ShenLiang
 * @since 2023-02-21
 */
@Api(tags = "PmsBrandController", description = "商品品牌管理")
@RestController
@RequestMapping("/pmsBrand")
public class PmsBrandController {
    private static final Logger log = LoggerFactory.getLogger(PmsBrandController.class);

    @Autowired
    PmsBrandService pmsBrandService;

    @ApiOperation("分页查询接口")
    @GetMapping("listAll")
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public CommonResult<CommonPage<PmsBrand>> listAll(@RequestParam(name = "pageNum",defaultValue = "1")Integer pageNum,
                                                      @RequestParam(name = "pageSize",defaultValue = "8")Integer pageSize){
        List<PmsBrand> list = pmsBrandService.listAll(pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }


    @GetMapping("{id}")
    public CommonResult<PmsBrand> brand(@PathVariable("id")Long id){
        CommonResult<PmsBrand> result = new CommonResult<>();

        PmsBrand brand = pmsBrandService.brand(id);
        if (brand == null){
            result = CommonResult.failed("查询失败");
            log.info("查询失败");
        }else {
            result = CommonResult.success(brand);
            log.info("查询成功,{}",brand);
        }
        return result;
    }


    @PostMapping("create")
    public CommonResult create(@RequestBody PmsBrand pmsBrand){
        CommonResult result;
        int count = pmsBrandService.create(pmsBrand);
        if (count == 1){
            result = CommonResult.success(pmsBrand);
            log.info("创建成功,{}",pmsBrand);
        }else {
            result = CommonResult.failed("操作失败");
            log.info("创建失败,{}",pmsBrand);
        }
        return result;
    }

    @PostMapping("update/{id}")
    public CommonResult update(@RequestBody PmsBrand brand, @PathVariable("id") Long id){
        CommonResult result;
        int count = pmsBrandService.updated(id,brand);
        if (count == 1){
            result = CommonResult.success(brand);
            log.info("更新数据成功,{}",brand.toString());
        }else {
            result = CommonResult.failed("更新失败");
            log.info("更新失败id:{},数据为:{}",id,brand.toString());
        }
        return result;
    }

    @GetMapping("delete/{id}")
    public CommonResult delete(@PathVariable("id")Long id){
        CommonResult result;
        int count = pmsBrandService.delete(id);
        if (count == 1){
            result = CommonResult.success(id);
            log.info("删除数据成功,主键id:{}",id);
        }else {
            result = CommonResult.failed("删除失败");
            log.info("删除数据失败,主键id:{}",id);
        }
        return result;
    }
}
