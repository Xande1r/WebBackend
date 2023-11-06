package com.example.backend.Controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.Mapper.ProfessionMapper;
import com.example.backend.Mapper.StudentMapper;
import com.example.backend.Mapper.UserMapper;
import com.example.backend.common.Code;
import com.example.backend.common.LoginResponse;
import com.example.backend.common.PageInfo;
import com.example.backend.entity.Profession;
import com.example.backend.entity.StudentInfo;
import com.example.backend.entity.StudentProfession;
import com.example.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/page")
@RestController
@CrossOrigin("http://localhost:9528")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ProfessionMapper professionMapper;
    @PostMapping("/login")
    public LoginResponse login(@RequestBody User user)
    {
        User user1 = userMapper.selectById(user.getUsername());
        if(user1==null)
        {
            return new LoginResponse(Code.UNAUTHORIZED,"用户不存在",null);
        }else if(!user1.getPassword().equals(user.getPassword()))
        {
            return new LoginResponse(Code.FAIL,"密码错误",null);
        }else{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token",user1.getUsername());
            return new LoginResponse(Code.SUCCESS,"登录成功",jsonObject);
        }
    }
    @GetMapping("/userInfo")
    public LoginResponse getUserInfo() {
        for(int i = 0; i < 100000; i++);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","为人民服务");
        jsonObject.put("avatar","https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png");
        return new LoginResponse(Code.SUCCESS, "访问成功", jsonObject);
    }
    @PostMapping("/studentInfo")
    public LoginResponse getStudentInfo(@RequestBody PageInfo pageInfo)
    {
        int currentPage = pageInfo.getCurrentPage();
        int pageSize = pageInfo.getPageSize();
        int start = (currentPage - 1) * pageSize;
        List<StudentProfession> studentProfession = studentMapper.getStudentProfession(start, pageSize);
        int total = studentMapper.selectCount(null);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",total);
        jsonObject.put("data",studentProfession);
        return new LoginResponse(Code.SUCCESS,"访问成功",jsonObject);
    }
    @PostMapping("/studentInfos")
    public LoginResponse deleteStudent(@RequestBody List<StudentInfo> studentInfos)
    {
        try{
            for(StudentInfo studentInfo:studentInfos)
            {
                studentMapper.deleteById(studentInfo.getStuId());
            }
            return new LoginResponse(Code.SUCCESS,"删除成功",null);
        }catch(Exception e)
        {
            return new LoginResponse(Code.FAIL,"删除失败",null);
        }

    }
    @GetMapping("/QstudentInfo/{params}")
    public LoginResponse selectQuery(@PathVariable String params)
    {
        QueryWrapper<StudentInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("stu_name",params);
        List<StudentInfo> studentInfos = studentMapper.selectList(queryWrapper);
        return new LoginResponse(Code.SUCCESS,"访问成功",studentInfos);
    }
    @PostMapping("/delete/studentInfo")
    public LoginResponse deleteStudentInfo(@RequestBody String stuId)
    {
        stuId = stuId.substring(stuId.indexOf(":")+1,stuId.indexOf("}"));
        try{
            int flag = studentMapper.deleteById(Integer.parseInt(stuId));
            if(flag !=0)
            {
                return new LoginResponse(Code.SUCCESS,"删除成功",null);
            }
            return new LoginResponse(Code.FAIL,"删除失败",null);
        }catch(Exception e)
        {
            return new LoginResponse(Code.FAIL,"删除失败",null);
        }
    }
    @GetMapping("/studentProfession")
    public LoginResponse getStudentProfession()
    {
        List<Profession> professions = professionMapper.selectList(null);
        return new LoginResponse(Code.SUCCESS,"访问成功",professions);
    }
    @PutMapping("/update/studentProfession")
    public LoginResponse update(@RequestBody StudentInfo studentInfo)
    {


        int row = studentMapper.updateById(studentInfo);
        if(row != 0)
        {
            return new LoginResponse(Code.SUCCESS,"修改成功",null);
        }
        return new LoginResponse(Code.FAIL,"修改失败",null);
    }
}
