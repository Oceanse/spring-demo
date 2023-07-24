package com.demo.jpa.dao;

import com.demo.jpa.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 只需要编写dao层接口，不需要编写dao层接口的实现类
 * <p>
 * 符合SpringDataJpa的dao层接口规范
 * JpaRepository < 操作的实体类类型，实体类中主键属性的类型>： 封装了基本CRUD操作
 * JpaSpecificationExecutor< 操作的实体类类型 >： 封装了复杂查询（分页）
 * <p>
 * SpringData Jpa的运行过程和原理剖析:
 * 通过JdkDynamicAopProxy的invoke方法创建了一个动态代理对象（SimpleJpaRepository）
 * SimpleJpaRepository当中封装了JPA的操作（借助JPA的api完成数据库的CURD）
 * 通过hibernate完成数据库操作（封装了jdbc）
 * <p>
 * <p>
 * JPQL查询
 * jpql：jpa query language (jpa查询语言)
 * 特点:
 * 语法或关键字和sql语句类似
 * 查询的是类和类中的属性
 */
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {//Book是要管理的实体，Long是主键的数据类型。

    //@Query 使用jpql的方式查询。
    @Query(value = "from Book")
    public List<Book> findAllBooksWithJPQL();

    //参数值会传递给占位符，?1代表参数的占位符，其中1对应方法中的参数索引
    @Query(value = "from Book where title = ?1")
    public List<Book> findBookByTitleWithJPQL(String title);

    //也可以通过使用 @Query 来执行一个更新操作，为此，我们需要在使用 @Query 的同时，
    // 用 @Modifying 来将该操作标识为修改查询，这样框架最终会生成一个更新的操作，而非查询。
    //参数值会传递给占位符, ?1代表第1个参数，?2代表第2个参数
    @Query(value = "update Book set title = ?2 where id = ?1")
    @Modifying
    public void updateBookWithJPQL(Long id, String title);

    //使用sql的形式查询
    //nativeQuery:查询方式, true代表sql查询，false代表jpql查询
    @Query(value="select * from Book where title like ?1",nativeQuery = true)
    public List<Book> findByNameWithSQL(String title);


    //方法命名规则查询,需要按照SpringDataJpa提供的方法名称规则来定义方法，不需要再去配置jpql语句，就能完成查询
    public List<Book> findByTitle(String title);
    public Book findByIdAndTitle(Long id,String title);
    public List<Book> findByTitleLike(String title);//findBy + 属性名称+Like
}