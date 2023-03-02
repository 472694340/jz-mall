package com.jz.mall.core.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 限时购表
 * </p>
 *
 * @author ShenLiang
 * @since 2023-02-21
 */
@Getter
@Setter
@TableName("sms_flash_promotion")
@ApiModel(value = "SmsFlashPromotion对象", description = "限时购表")
public class SmsFlashPromotion implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String title;

    @ApiModelProperty("开始日期")
    private Date startDate;

    @ApiModelProperty("结束日期")
    private Date endDate;

    @ApiModelProperty("上下线状态")
    private Integer status;

    @ApiModelProperty("秒杀时间段名称")
    private Date createTime;


}
