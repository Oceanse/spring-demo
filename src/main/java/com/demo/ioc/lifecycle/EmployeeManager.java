package com.demo.ioc.lifecycle;


import java.sql.*;

/**
 spring中通过配置init-method和destroy-method方法来实现Bean的初始化和销毁时附加的操作
 执行顺序：
 1 构造器(IOC)
 2 set方法(DI)
 3 init方法
 4 业务方法
 5 destroy方法
 */
public class EmployeeManager {

    public EmployeeManager() {
        System.out.println("1 Initialize EmployeeManager instance......");
    }

    private int id;

    public void setId(int id) {
        System.out.println("2 setId.......");
        this.id = id;
    }

    private Connection connection;

    // 在初始化阶段建立数据库连接
    public void init() throws SQLException {
        System.out.println("3 Initialize the DB Connection.............");
        connection = DriverManager.getConnection("jdbc:mysql://172.16.12.128:3306/jpaDB", "root", "123123");
    }

    // 执行查询操作
    public void executeQuery(String query) throws SQLException {
        System.out.println("4 query = " + query);
    }

    // 在销毁阶段释放数据库连接
    public void cleanup() {
        System.out.println("5 Release the DB Connection");
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
