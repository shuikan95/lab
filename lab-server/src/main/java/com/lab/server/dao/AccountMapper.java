package com.lab.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lab.common.domain.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}