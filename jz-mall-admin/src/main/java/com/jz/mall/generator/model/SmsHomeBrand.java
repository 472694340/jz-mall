package com.jz.mall.generator.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 首页推荐品牌表
 * </p>
 *
 * @author ShenLiang
 * @since 2023-02-21
 */
@Getter
@Setter
@TableName("sms_home_brand")
@ApiModel(value = "SmsHomeBrand对象", description = "首页推荐品牌表")
public class SmsHomeBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long brandId;

    private String brandName;

    private Integer recommendStatus;

    private Integer sort;


}
