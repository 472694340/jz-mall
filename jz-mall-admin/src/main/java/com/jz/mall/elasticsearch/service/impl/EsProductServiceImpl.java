package com.jz.mall.elasticsearch.service.impl;

import com.jz.mall.elasticsearch.dao.EsProductDao;
import com.jz.mall.elasticsearch.esModel.EsProduct;
import com.jz.mall.elasticsearch.repository.EsProductRepository;
import com.jz.mall.elasticsearch.service.EsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 这里所有的操作,都有一层转换的意思
 *  增/删/改/查,都需要先从数据库中把数据导入后,然后再查询,在删除的时候,需要先对数据库进行删除
 *   这里对实现层进行复查 2023.3.4
 */
@Service
public class EsProductServiceImpl implements EsProductService {

    @Autowired
    private EsProductDao productDao;

    @Autowired
    private EsProductRepository productRepository;

    /**
     * 导入数据库中的商品数据
     * @return
     */
    @Override
    public int importAll() {
        List<EsProduct> esProducts = productDao.selectFormDataBase();//先从数据库中查
        Iterable<EsProduct> esProductIterable = productRepository.saveAll(esProducts);//存入es
        Iterator<EsProduct> iterator = esProductIterable.iterator();
        int count = 0;
        while (iterator.hasNext()){
            count++;
            iterator.next();
        }
        return count;
    }

    /**
     * 删除数据
     * @param id
     */
    @Override
    public void deleteProduct(Long id) {
        int i = productDao.deleteById(id);
        if (i == 1){
            productRepository.deleteById(id);
        }
    }

    /**
     * 在es中创建数据
     * @param id
     * @return
     */
    @Override
    public EsProduct createProduct(Long id) {
        EsProduct esProduct = productDao.selectById(id);
        if (esProduct != null){
            EsProduct product = productRepository.save(esProduct);
            return product;
        }
        return null;
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void delete(List<Long> ids) {
        if (!ids.isEmpty()){
            List<EsProduct> esProducts = productDao.selectBatchIds(ids);
            if (!esProducts.isEmpty()){//如果从数据库中查出的数据不为空,那么删除数据库中的数据 且 删除ES中的数据
                ids.stream().forEach(o->productDao.deleteById(o));
            }
            esProducts.stream().forEach(o->productRepository.delete(o));
        }
    }

    /**
     * 根据关键词查找数据,返回分页数据
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return productRepository.findByNameOrSubTitleOrKeywords(keyword,keyword,keyword,pageable);
    }
}
