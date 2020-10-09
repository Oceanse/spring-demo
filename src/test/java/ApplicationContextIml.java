import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.testng.annotations.Test;

import java.util.Date;



//applicationcontext实现类

public class ApplicationContextIml {


    //FileSystemXmlApplicationContext(绝对路径，文件可以在工程外面)
    @Test
    public void test(){

        String path="C:\\Users\\epanhai\\git\\springdemo\\src\\Ioc.xml";
        ApplicationContext applicationContext=new FileSystemXmlApplicationContext(path);

        //获取spring容器中所有的bean
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

        //获取spring容器中所有的bean的数量
        int beanDefinitionCount = applicationContext.getBeanDefinitionCount();
        System.out.println("beanDef//获取spring容器中所有的bean的数量initionCount="+beanDefinitionCount);

        //获取spring容器中启动时间
        long startupDate = applicationContext.getStartupDate();

        //获得的是自1970-1-01 00:00:00.000 到当前时刻的时间距离,类型为long
        System.out.println(System.currentTimeMillis());

        System.out.println(new Date(System.currentTimeMillis()));
        System.out.println("startupDate="+startupDate);

    }


    //FileSystemXmlApplicationContext(相对路径，相对工程根目录)
    @Test
    public void test2(){
        ApplicationContext applicationContext=new FileSystemXmlApplicationContext("xmlfileload.xml");
        System.out.println(applicationContext.getBeanDefinitionCount());

        ApplicationContext applicationContext2=new FileSystemXmlApplicationContext("src\\xmlfileload2.xml");
        System.out.println(applicationContext2.getBeanDefinitionCount());
    }


    //ClassPathXmlApplicationContext不带classpath，带不带/都可以
    @Test
    public void test3(){

        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("/ioc/produce_bean/Ioc.xml");
        System.out.println(applicationContext.getBeanDefinitionCount());
    }

    //ClassPathXmlApplicationContext带classpath
    @Test
    public void test4(){

        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:ioc/produce_bean/Ioc.xml");
        System.out.println(applicationContext.getBeanDefinitionCount());
    }



}
