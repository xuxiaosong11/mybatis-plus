package com.example.mybaitsplus.handler;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;


@Slf4j
@Component
/**
 * @author qp
 */
public class MyObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
         log.info("start fill");
        System.out.println("start fill");
         this.setFieldValByName("createTime",new Date(),metaObject);
         this.setFieldValByName("updateTime",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
         log.info("start update fill---");
        System.out.println("start  update fill");
         this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
