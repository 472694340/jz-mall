package com.jz.mall.core.service.impl;

import com.jz.mall.core.domain.model.OmsOrderItem;
import com.jz.mall.core.mapper.OmsOrderItemMapper;
import com.jz.mall.core.service.OmsOrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单中所包含的商品 服务实现类
 * </p>
 *
 * @author ShenLiang
 * @since 2023-02-21
 */
@Service
public class OmsOrderItemServiceImpl extends ServiceImpl<OmsOrderItemMapper, OmsOrderItem> implements OmsOrderItemService {

}
