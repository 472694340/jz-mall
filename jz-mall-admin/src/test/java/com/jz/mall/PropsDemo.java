package com.jz.mall;

import cn.hutool.setting.dialect.Props;
import org.junit.Test;

public class PropsDemo {

    @Test
    public void PropsTest(){
        Props props = new Props("application.yml");
        String str = props.getStr("four");
        System.out.println(str);
    }
}
