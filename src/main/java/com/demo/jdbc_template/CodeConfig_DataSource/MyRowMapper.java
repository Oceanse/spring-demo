package com.demo.jdbc_template.CodeConfig_DataSource;

import com.demo.jdbc_template.dto.Phone;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MyRowMapper implements RowMapper<Phone> {

    @Override
    public Phone mapRow(ResultSet resultSet, int i) throws SQLException {

        //结果集中获得字段值
        String brand = resultSet.getString("brand");
        String price = resultSet.getString("price");

        //把字段值封装到对象中
        Phone phone = new Phone();
        phone.setBrand(brand);
        phone.setPrice(price);
        return phone;
    }
}