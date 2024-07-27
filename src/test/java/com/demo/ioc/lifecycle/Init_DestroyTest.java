package com.demo.ioc.lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import java.sql.SQLException;


/**
 *  在构造函数之后，立刻执行init方法
 *  如果Spring容器没有执行close方法，则不执行销毁方法
 *  如果Spring容器执行了close方法，在执行该方法之前要执行销毁方法
 */
public class Init_DestroyTest {


    /**
     spring中通过配置init-method和destroy-method方法来实现Bean的初始化和销毁时附加的操作
     执行顺序：
     1 构造器(IOC)
     2 set方法(DI)
     3 init方法
     4 业务方法
     5 destroy方法
     */
    @Test
    public void test() throws SQLException {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc/lifecycle/init_destroy.xml");
        EmployeeManager manager = applicationContext.getBean("employeeManager", EmployeeManager.class);
        manager.executeQuery("SELECT * FROM users");
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }


    /**
     spring中通过配置init-method和destroy-method方法来实现Bean的初始化和销毁时附加的操作
     执行顺序：
     1 构造器(IOC)
     2 set方法(DI)
     3 init方法
     4 业务方法
     5 destroy方法
     */
    @Test
    public void test2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc/lifecycle/init_destroy2.xml");
        Stage stage = applicationContext.getBean("stage", Stage.class);
        stage.perform();
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }

    /**
     * prototype Bean的每次请求都会生成新的实例，所以它们的生命周期管理相对简单。
     * 每当应用程序通过ApplicationContext请求一个prototype Bean时，Spring就会创建一个新的Bean实例，
     * 并将其返回给应用程序。Spring不会跟踪这些实例的后续生命周期。一旦Bean被创建并返回给应用程序,它的生命周期就完全由应用程序来管理。
     * 这意味着对于prototype Bean：
     * 初始化：当应用程序首次请求某个prototype Bean时，Spring会创建一个新的实例，并执行与Bean相关的初始化逻辑（例如，调用@PostConstruct注解的方法）。
     * 使用：一旦Bean被创建并返回给应用程序，它的使用就完全由应用程序控制。Spring不会对其进行任何进一步的管理或干预。
     * 销毁：对于prototype作用域的Bean，Spring不会管理其销毁过程。如果Bean持有需要显式关闭或释放的资源（如数据库连接、文件句柄等），则应用程序需要负责在使用完毕后适当地清理这些资源。
     */
    @Test
    public void test3(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc/lifecycle/init_destroy2.xml");
        Stage stage = applicationContext.getBean("stage2", Stage.class);
        stage.perform();
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }

    /**
     * 在使用 Java 注解配置时，不需要显式地配置 init-method 和 destroy-method，而是通过 @PostConstruct 和 @PreDestroy 注解来标记初始化和销毁方法。
     * init-method等价于@PostConstruct注解
     * destroy-method等价于@PreDestroy注解
     */
    @Test
    public void test4(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc/lifecycle/init_destroy3.xml");
        ResourceManager resourceManager = applicationContext.getBean("resourceManager", ResourceManager.class);
        String property = resourceManager.getProperty("jdbc.driver");
        System.out.println("property = " + property);
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }

}
