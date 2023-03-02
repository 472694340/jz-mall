package com.jz.mall.exception.base;

import com.jz.mall.utils.HttpsStatus;

/**
 * 自定义一些需要特殊处理的常见异常
 */
public class NormalException extends BaseException{
   private static final long serialVersionUID = 1L;

   public NormalException(){
       super("常见异常", HttpsStatus.HTTP_INTERNAL_ERROR);
   }

}
