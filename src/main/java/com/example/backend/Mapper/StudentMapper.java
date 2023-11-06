package com.example.backend.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.StudentInfo;
import com.example.backend.entity.StudentProfession;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface StudentMapper extends BaseMapper<StudentInfo> {
    @Select("select u.*,o.stu_profession from tb_student u,tb_profession o WHERE u.stu_profession_id = o.stu_profession_id  LIMIT ${offset},${pageSize};")
    public List<StudentProfession> getStudentProfession(int offset, int pageSize);
    @Delete("delete from tb_student where stu_id = ${stuId}")
    public int deleteStudent(String stuId);
}
