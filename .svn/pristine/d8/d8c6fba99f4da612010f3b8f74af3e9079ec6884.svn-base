package com.ut.masterCode.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.ut.masterCode.mapper.primary", sqlSessionTemplateRef = "primarySqlSessionTemplate")
public class DataSourcePrimaryConfig {

  @Bean(name = "primaryDataSource")
  @ConfigurationProperties(prefix = "spring.datasource")
  @Primary
  public DataSource primaryDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "primarySqlSessionFactory")
  @Primary
  public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource) throws Exception {
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(dataSource);

    bean.setTypeAliasesPackage("com.ut.masterCode.model");
    bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/primary/*/*.xml"));

    // mybatis.configuration.map-underscore-to-camel-case
    org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
    configuration.setMapUnderscoreToCamelCase(true);
    bean.setConfiguration(configuration);

    return bean.getObject();
  }

  @Bean(name = "primaryTransactionManager")
  @Primary
  public DataSourceTransactionManager primaryTransactionManager(@Qualifier("primaryDataSource") DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean(name = "primarySqlSessionTemplate")
  @Primary
  public SqlSessionTemplate primarySqlSessionTemplate(@Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }
}
