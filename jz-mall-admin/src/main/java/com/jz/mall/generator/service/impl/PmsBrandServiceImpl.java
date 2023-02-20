package com.jz.mall.generator.service.impl;

import com.github.pagehelper.PageHelper;
import com.jz.mall.generator.mapper.PmsBrandMapper;
import com.jz.mall.generator.model.PmsBrand;
import com.jz.mall.generator.model.PmsBrandExample;
import com.jz.mall.generator.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PmsBrandServiceImpl implements PmsBrandService {
    @Autowired
    PmsBrandMapper pmsBrandMapper;

    @Override
    public List<PmsBrand> listAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<PmsBrand> list = pmsBrandMapper.selectByExample(new PmsBrandExample());
        return list;
    }

    @Override
    public PmsBrand getOne(Long id) {
        return pmsBrandMapper.selectByPrimaryKey(id);
    }
}
