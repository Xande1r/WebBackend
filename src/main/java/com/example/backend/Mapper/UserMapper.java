package com.example.backend.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper

public interface UserMapper extends BaseMapper<User> {
}
