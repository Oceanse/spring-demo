package com.demo.jdbc_template.transaction;

import com.demo.jdbc_template.CodeConfig_DataSource.AccountRowMapper;
import com.demo.jdbc_template.dto.Account;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccountService {


    /**
     * 先插入两条测试数据
     */
    public void insert() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("jdbc_template/transaction.xml");
        JdbcTemplate jdbcTemplate = classPathXmlApplicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        int id = 3;
        String name = "xm2";
        double balance = 1000;

        int id2 = 4;
        String name2 = "xh2";
        double balance2 = 1000;

        String insertSql = "insert into account (id,name,balance) values(?,?,?)";

        jdbcTemplate.update(insertSql, id, name, balance);
        jdbcTemplate.update(insertSql, id2, name2, balance2);

    }





    public void transfer(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("jdbc_template/transaction.xml");
        JdbcTemplate jdbcTemplate = classPathXmlApplicationContext.getBean("jdbcTemplate", JdbcTemplate.class);

        String querySql = "select * from account where id=?";
        List<Account> accounts1 = jdbcTemplate.query(querySql, new AccountRowMapper(), 1);
        List<Account> accounts2 = jdbcTemplate.query(querySql, new AccountRowMapper(), 2);
        double balance1 = accounts1.get(0).getBalance();
        double balance2 = accounts2.get(0).getBalance();
        System.out.println("balance1 = " + balance1);
        System.out.println("balance2 = " + balance2);


        String updateSql = "update account set balance=" + (balance1 - 10) + "where id=1";
        String updateSql2 = "update account set balance=" + (balance2 + 10) + "where id=2";

        jdbcTemplate.update(updateSql);
        //模拟突然出现的异常，比如银行中可能为突然停电等等
        //如果没有配置事务管理的话会造成，id=1账户少了1000而id=2账户没有多钱
        System.out.println(1/0);
        jdbcTemplate.update(updateSql2);
    }


}
