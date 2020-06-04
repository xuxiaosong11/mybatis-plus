package com.example.mybaitsplus.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


/**
 * @author qp
 * 用户实体类
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @TableId(type = IdType.AUTO)
    private int id;
    private String name;
    private int age;
    private String email;
    @TableField(fill = FieldFill.INSERT)
    @Version
    private int version;
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
