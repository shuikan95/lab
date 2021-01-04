package com.lab.client.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lab.client.vo.AccountVO;
import com.lab.common.domain.Account;
import com.lab.common.service.AccountService;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.Reference;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@ApiSupport(author = "SK95")
@Validated
@RestController
public class ClientController {
    @Reference
    private AccountService accountService;

    @ApiOperation("查询单个数据")
    @SneakyThrows
    @PostMapping("/findOne")
    public Account findOne(@Validated @RequestBody AccountVO accountVO) {
        return accountService.getById(accountVO.getId());
    }

    @ApiOperation("查询单个数据")
    @SneakyThrows
    @GetMapping("/findOne/{id}")
    public Account findOne(@Range(min = 0, max = 1000, message = "id范围大于0，小于1000！") @PathVariable("id") Integer id) {
        return accountService.getById(id);
    }

    @ApiOperation("新增数据")
    @SneakyThrows
    @GetMapping("/add")
    public Boolean addAccount() {
        Account account = new Account();
        account.setName("李四" + new Random().nextInt(200));
        account.setAccount("sili" + new Random().nextInt(200));
        account.setPosition("JAVA开发工程师" + new Random().nextInt(200));
        return accountService.save(account);
    }
}
