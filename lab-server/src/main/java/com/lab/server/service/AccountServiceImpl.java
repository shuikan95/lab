package com.lab.server.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lab.common.domain.Account;
import com.lab.common.service.AccountService;

import com.lab.server.dao.AccountMapper;
import org.apache.dubbo.config.annotation.Service;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

}
