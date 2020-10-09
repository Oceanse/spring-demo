package com.demo.jdbc_template.CodeConfig_DataSource;

import com.demo.jdbc_template.dto.Phone;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;


/**
 * spring框架为我们提供了很多操作模板类，比如操作关系型数据库的jdbcTemplate，操作nosql数据库的Redis Template，操作消息队列的jmsTemplate等等
 * JdbcTemplate只能作为一个 JDBC 的小工具, 而不是 ORM 框架
 * <p>
 * 步骤：
 * 1.导入sprign-jdbc和spring-tx坐标
 * 2.创建数据库表和实体
 * 3.创建JdbcTemplate对象
 * 4.执行数据库操作
 */
public class JDBC_TemplateTest {
    JdbcTemplate jdbcTemplate;

    @BeforeTest
    public void test() {
        //设置数据库信息
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://192.168.56.102:3306/phone?useSSL=false");
        ds.setUsername("root");
        ds.setPassword("123123");
        jdbcTemplate = new JdbcTemplate(ds);
    }


    /**
     * 查
     */
    @Test
    public void testQuery() {

        //统计所有记录数量
        String querySql = "select * from phoneTable";

        //第一个参数是sql语句，第二个是接口RowMapper,需要自己实现接口
        List<Phone> phones = jdbcTemplate.query(querySql, new MyRowMapper());
        System.out.println(phones);
    }


    /**
     * 增
     * CREATE TABLE phoneTable(
     * brand VARCHAR(200) ,
     * price DOUBLE
     * );
     */
    @Test
    public void testInsert() {
        String brand = "Nova5";
        double price = 2500;

        String brand2 = "iphone";
        double price2 = 5000;

        String insertsql = "insert into phoneTable (brand,price) values(?,?)";
        jdbcTemplate.update(insertsql, brand, price);
        jdbcTemplate.update(insertsql, brand2, price2);
    }


    /**
     * 删
     */
    @Test
    public void testDelete() {
        String brand = "Nova5";

        String deleteSql = "DELETE from phoneTable WHERE brand = ?";
        jdbcTemplate.update(deleteSql, brand);
    }


    /**
     * 改
     */
    @Test
    public void testUpdate() {
        String brand = "iphone";
        double price = 10000;
        String sql = "UPDATE phoneTable SET price = ? WHERE brand = ?";
        jdbcTemplate.update(sql, price, brand);
    }


    @Test
    public void testCount() {

        //统计所有记录数量
        String countSql = "select count(*) from phoneTable";

        //第一个参数是sql语句，第二个是接口RowMapper,需要自己实现接口
        int count = jdbcTemplate.queryForObject(countSql, Integer.class);
        System.out.println(count);
    }
}