package com.demo.jdbc_template.transaction;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;


/**
 * 不知为何不生效
 */
public class TransactionTest {

    @Test
    public void testTX(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("jdbc_template/transaction.xml");
        AccountService accountService = classPathXmlApplicationContext.getBean("accountService", AccountService.class);
        accountService.transfer();
    }





}
