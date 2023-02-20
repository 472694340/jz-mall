package com.jz.mall.generator.service;

import com.jz.mall.generator.model.PmsBrand;

import java.util.List;

public interface PmsBrandService {
    List<PmsBrand> listAll(Integer pageNum, Integer pageSize);

    PmsBrand getOne(Long id);
}
