package com.demo.jdbc_template.transaction;

import com.demo.jdbc_template.CodeConfig_DataSource.AccountRowMapper;
import com.demo.jdbc_template.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.testng.annotations.Test;

import javax.transaction.Transactional;
import java.util.List;


//@Service //其他类在加载spring上下文会产生Error creating bean with name 'accountService'，所以这里先注释掉
public class AccountService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 先插入两条测试数据
     */
    @Test
    public void insert() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("jdbc_template/transaction.xml");
        JdbcTemplate jdbcTemplate = classPathXmlApplicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        int id = 5;
        String name = "xm2";
        double balance = 1000;

        int id2 = 6;
        String name2 = "xh2";
        double balance2 = 1000;

        String insertSql = "insert into account (id,name,balance) values(?,?,?)";

        jdbcTemplate.update(insertSql, id, name, balance);
        jdbcTemplate.update(insertSql, id2, name2, balance2);

    }


    @Transactional
    public void transfer(){
        String querySql = "select * from account where id=?";
        List<Account> accounts1 = jdbcTemplate.query(querySql, new AccountRowMapper(), 3);
        List<Account> accounts2 = jdbcTemplate.query(querySql, new AccountRowMapper(), 4);
        double balance1 = accounts1.get(0).getBalance();
        double balance2 = accounts2.get(0).getBalance();
        System.out.println("balance1 = " + balance1);
        System.out.println("balance2 = " + balance2);


        String updateSql = "update account set balance=" + (balance1 - 10) + "where id=3";
        String updateSql2 = "update account set balance=" + (balance2 + 10) + "where id=4";

        jdbcTemplate.update(updateSql);
        //模拟突然出现的异常，比如银行中可能为突然停电等等
        //如果没有配置事务管理的话会造成，id=1账户少了1000而id=2账户没有多钱
        System.out.println(1/0);
        jdbcTemplate.update(updateSql2);
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("jdbc_template/transaction.xml");
        AccountService accountService = classPathXmlApplicationContext.getBean("accountService", AccountService.class);
        accountService.transfer();
    }

}
