package com.jz.mall.core.domain.model;

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
 * 帮助分类表
 * </p>
 *
 * @author ShenLiang
 * @since 2023-02-21
 */
@Getter
@Setter
@TableName("cms_help_category")
@ApiModel(value = "CmsHelpCategory对象", description = "帮助分类表")
public class CmsHelpCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    @ApiModelProperty("分类图标")
    private String icon;

    @ApiModelProperty("专题数量")
    private Integer helpCount;

    private Integer showStatus;

    private Integer sort;


}
