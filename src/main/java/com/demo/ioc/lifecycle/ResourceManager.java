package com.demo.ioc.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 在使用 Java 注解配置时，不需要显式地配置 init-method 和 destroy-method，而是通过 @PostConstruct 和 @PreDestroy 注解来标记初始化和销毁方法。
 * init-method等价于@PostConstruct注解
 * destroy-method等价于@PreDestroy注解
 */
public class ResourceManager {

    public ResourceManager() {
        System.out.println("ResourceManager()......");
    }

    private Properties properties;

    @PostConstruct
    public void loadFile() {
        System.out.println("loadFile......");
        // 在初始化阶段加载资源文件

        try ( InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("jdbc_template/jdbc.properties")) {
            properties = new Properties();
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("cleanup......");
        // 在销毁阶段释放资源
        properties = null;
    }

    // 获取配置属性值
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    // 其他资源管理方法...
}
