package cn.sdu.icat.stirm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author icatzfd
 */
@SpringBootApplication
@MapperScan("cn.sdu.icat.stirm.dal.mapper")
public class StirmApplication {

    public static void main(String[] args) {
        SpringApplication.run(StirmApplication.class, args);
    }

}
