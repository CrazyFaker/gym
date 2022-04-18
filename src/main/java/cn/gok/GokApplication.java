package cn.gok;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * boot项目的启动类
 */

@SpringBootApplication
//事务管理的注解
@EnableTransactionManagement
//dao层扫描的注解
@MapperScan("cn.gok.mapper")
public class GokApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(GokApplication.class, args);
    }

}
