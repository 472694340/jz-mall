package com.jz.mall.generator.controller;

import com.jz.mall.generator.model.PmsBrand;
import com.jz.mall.generator.common.CommonPage;
import com.jz.mall.generator.common.CommonResult;
import com.jz.mall.generator.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌管理
 */
@RestController
@RequestMapping("/pmsBrand")
public class PmsBrandController {

    @Autowired
    PmsBrandService pmsBrandService;

    @GetMapping("listAll")
    public CommonResult<CommonPage<PmsBrand>> listAll( @RequestParam(name = "pageNum",defaultValue = "1")Integer pageNum,
                                                       @RequestParam(name = "pageSize",defaultValue = "8")Integer pageSize){
       List<PmsBrand> list = pmsBrandService.listAll(pageNum,pageSize);
       return CommonResult.success(CommonPage.restPage(list));
    }


    @GetMapping("getOne/{id}")
    public CommonResult<PmsBrand> getOne(@PathVariable(name = "id")Long id){
        CommonResult<PmsBrand> result = new CommonResult<>();

        PmsBrand brand = pmsBrandService.getOne(id);
        if (brand == null){
            result = CommonResult.failed();
        }else {
            result = CommonResult.success(brand);
        }
        return result;
    }
}
