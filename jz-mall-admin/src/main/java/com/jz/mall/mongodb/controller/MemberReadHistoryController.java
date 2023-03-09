package com.jz.mall.mongodb.controller;

import com.jz.mall.common.CommonResult;
import com.jz.mall.mongodb.MemberReadHistory;
import com.jz.mall.mongodb.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对商品浏览记录的增删改查功能
 *  谁对商品的浏览记录,这里有个主语,应该包含用户的id
 *
 *  逻辑上来讲,查记录,那就应该去MongoDB里去查,那么生成的数据是应该存在MongoDB里,还是生成在MySQL里,同步了再去查?
 */
@RestController
@RequestMapping("/history")
@Api(tags = "MemberReadHistoryController.class",description = "商品浏览记录")
public class MemberReadHistoryController {
    private static final Logger log = LoggerFactory.getLogger(MemberReadHistoryController.class);

    @Autowired
    MemberReadHistoryService historyService;

    @GetMapping("one/{id}")
    @ApiOperation("获取详细信息")
    public CommonResult one(@PathVariable("id") String id){
        MemberReadHistory history= historyService.getOne(id);
        return CommonResult.success(history);
    }

    /**
     * 批量删除/单个删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ApiOperation("删除浏览记录")
    public CommonResult delete(@RequestParam("ids")List<String> ids){
        int count =  historyService.delete(ids);
        if (count >= 1){
            log.info("浏览记录删除成功,{}",ids);
            return CommonResult.success();
        }else {
            return CommonResult.failed("删除失败");
        }
    }

    /**
     * 生成浏览记录
     * @param readHistory
     * @return
     */
    @ApiOperation("创建浏览记录")
    @PostMapping("create")
    public CommonResult create(@RequestBody MemberReadHistory readHistory){
       int count =  historyService.create(readHistory);
       if (count == 1){
           log.info("浏览记录生成成功,{}",readHistory);
           return CommonResult.success();
       }else {
           return CommonResult.failed();
       }
    }



    /**
     * 获取用户浏览记录
     * @param id
     * @return
     */
    @GetMapping("list/{id}")
    @ApiOperation("展示浏览记录")
    public CommonResult getList(@PathVariable Long id){
       List<MemberReadHistory> list =  historyService.getList(id);
       return CommonResult.success(list);
    }


}
