package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_student")
public class StudentInfo {
    @TableId(value="stu_id")
    private String stuId;
    private String stuName;
    private String stuSex;
    private String stuAge;
    private String stuOrigin;
    private Integer stuProfessionId;

}
