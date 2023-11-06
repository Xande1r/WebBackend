package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_profession")
public class Profession {
    @TableId
    private Integer stuProfessionId;
    private String stuProfession;
}
