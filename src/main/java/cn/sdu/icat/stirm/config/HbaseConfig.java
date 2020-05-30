package cn.sdu.icat.stirm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Hbase配置文件
 *
 * @author icatzfd
 * Created on 2020/5/30 19:48.
 */
@Configuration
@ImportResource("classpath:hbase-spring.xml")
public class HbaseConfig {
}
