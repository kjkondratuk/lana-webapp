package org.kondrak.lana;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;

@Configuration
@MapperScan("org.kondrak.lana.mappers")
public class LanaConfig {

    @Bean
    public DataSource dataSource() throws Exception {
        Properties props = new Properties();
        props.load(new BufferedReader(new FileReader(new File("config.properties"))));
        PGPoolingDataSource ds = new PGPoolingDataSource();
        ds.setServerName(props.getProperty("db.server"));
        ds.setPassword(props.getProperty("db.password"));
        ds.setUser(props.getProperty("db.user"));
        ds.setDatabaseName(props.getProperty("db.name"));
        ds.setPortNumber(Integer.parseInt(props.getProperty("db.port")));
        return ds;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() throws Exception {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
//        Environment env = new Environment("default", new SpringManagedTransactionFactory(), dataSource());
//        org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
//        config.setEnvironment(env);
//        SqlSessionFactoryBuilder sessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setTransactionFactory(new SpringManagedTransactionFactory());
        bean.setDataSource(dataSource());
        return bean;
    }

//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory());
//    }
}
