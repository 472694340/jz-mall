package com.jz.mall.core.domain.baseEntity;

import java.util.Date;

/**
 * 通常会有一个基类,里面有创建时间,创建人等信息
 */
public class BaseEntity {
    private static final long serialVersionUID = 1L;
    private Date createtime;//创建时间
    private String create;//创建人

}
