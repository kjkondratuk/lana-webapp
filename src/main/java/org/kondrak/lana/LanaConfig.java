package org.kondrak.lana;

import org.kondrak.archer.listener.ChannelListener;
import org.kondrak.archer.listener.MessageListener;
import org.kondrak.archer.listener.ModuleListener;
import org.kondrak.archer.listener.ReadyListener;
import org.kondrak.archer.listener.UserListener;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"org.kondrak.lana", "org.kondrak.archer", "org.kondrak.shared"})
@MapperScan("org.kondrak.shared.mappers")
public class LanaConfig extends WebMvcConfigurerAdapter {

    @Bean
    public Properties applicationProperties() throws Exception {
        Properties props = new Properties();
        props.load(new BufferedReader(new FileReader(new File("config.properties"))));
        return props;
    }

    @Bean
    public DataSource dataSource() throws Exception {
        PGPoolingDataSource ds = new PGPoolingDataSource();
        ds.setServerName(applicationProperties().getProperty("db.server"));
        ds.setPassword(applicationProperties().getProperty("db.password"));
        ds.setUser(applicationProperties().getProperty("db.user"));
        ds.setDatabaseName(applicationProperties().getProperty("db.name"));
        ds.setPortNumber(Integer.parseInt(applicationProperties().getProperty("db.port")));
        return ds;
    }

    @Bean
    public IDiscordClient discordClient(
            @Autowired ChannelListener channel,
            @Autowired MessageListener messageListener,
            @Autowired ModuleListener moduleListener,
            @Autowired ReadyListener readyListener,
            @Autowired UserListener userListener) throws Exception {
        return new ClientBuilder()
                .withToken(applicationProperties().getProperty("discord.token"))
                .registerListeners(channel, messageListener, moduleListener, readyListener, userListener)
                .login();
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
                .addResourceLocations("dist/");
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".html");
        return resolver;
    }
}
