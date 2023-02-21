package com.jz.mall.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.jz.mall.generator.model.PmsBrand;
import com.jz.mall.generator.mapper.PmsBrandMapper;
import com.jz.mall.generator.service.PmsBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author ShenLiang
 * @since 2023-02-21
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService {

    @Autowired
    PmsBrandMapper pmsBrandMapper;

    @Override
    public List<PmsBrand> listAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<PmsBrand> list = pmsBrandMapper.selectList(new QueryWrapper<PmsBrand>());
        return list;
    }

    @Override
    public PmsBrand brand(Long id) {
        return pmsBrandMapper.selectById(id);
    }

    @Override
    public int create(PmsBrand pmsBrand) {
        return pmsBrandMapper.insert(pmsBrand);
    }

    @Override
    public int delete(Long id) {
        return pmsBrandMapper.deleteById(id);
    }

    @Override
    public int updated(Long id, PmsBrand brand) {
        brand.setId(id);
        return pmsBrandMapper.updateById(brand);
    }
}
