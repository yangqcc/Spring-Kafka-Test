package com.yqc.tx.config;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author yangqc
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

  @Configuration
  static class MyDataSourceConfig {

    private String url = "jdbc:mysql://127.0.0.1:3306/MY_TEST?characterEncoding=utf-8&useSSL=true";

    private String driverClassName = "com.mysql.jdbc.Driver";

    private String username = "root";

    private String password = "123";

    private int initialSize = 10;

    private int minIdle = 15;

    private long maxWait = 200;

    private int maxActive = 20;

    private long minEvictableIdleTimeMillis = 15;

    @Bean
    public DataSource Data() {
      BasicDataSource datasource = new BasicDataSource();
      datasource.setUrl(url);
      datasource.setDriverClassName(driverClassName);
      datasource.setUsername(username);
      datasource.setPassword(password);
      datasource.setInitialSize(initialSize);
      datasource.setMinIdle(minIdle);
      datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
      return datasource;
    }
  }


  @Bean(value = "jdbcTemplate")
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Bean
  public PlatformTransactionManager txManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

}