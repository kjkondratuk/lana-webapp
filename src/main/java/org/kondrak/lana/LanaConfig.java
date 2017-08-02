package org.kondrak.lana;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;

@Configuration
@MapperScan("org.kondrak.lana.mappers")
public class LanaConfig extends WebMvcConfigurerAdapter {

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
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setTransactionFactory(new SpringManagedTransactionFactory());
        bean.setDataSource(dataSource());
        return bean;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("/lana-frontend/dist/");
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
//        resolver.setPrefix("/");
        resolver.setSuffix(".html");
        return resolver;
    }
}
