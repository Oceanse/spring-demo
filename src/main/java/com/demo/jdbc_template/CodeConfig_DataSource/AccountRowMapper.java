package com.demo.jdbc_template.CodeConfig_DataSource;

import com.demo.jdbc_template.entity.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        //结果集中获得字段值
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        double balance = resultSet.getDouble("balance");
        //把字段值封装到对象中
        Account account=new Account();
        account.setId(id);
        account.setName(name);
        account.setBalance(balance);
        return account;
    }
}

