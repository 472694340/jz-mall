package com.jz.mall.generator.service;

import com.jz.mall.generator.model.PmsBrand;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author ShenLiang
 * @since 2023-02-21
 */
public interface PmsBrandService extends IService<PmsBrand> {

    List<PmsBrand> listAll(Integer pageNum, Integer pageSize);

    PmsBrand brand(Long id);

    int create(PmsBrand pmsBrand);

    int delete(Long id);

    int updated(Long id, PmsBrand brand);
}
