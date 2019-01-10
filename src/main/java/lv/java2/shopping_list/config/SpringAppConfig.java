package lv.java2.shopping_list.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.ServletRegistration;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "lv.java2.shopping_list")
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class SpringAppConfig {

    /*
    PropertySourcesPlaceholderConfigurer is used for ${} placeholders in @Value annotations to access and register properties
    with @PropertySource
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public static DataSource dataSource(
            //Reading ${} placeholders with PropertySourcePlaceholderConfigurer
            @Value("${jdbcUrl}") String jdbcUrl,
            @Value("${db.userName}") String userName,
            @Value("${db.password}") String password) {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(jdbcUrl);
        ds.setUsername(userName);
        ds.setPassword(password);
        return ds;
    }

    @Bean
    public Properties hibernateProperties(
            @Value("${hibernate.dialect}") String dialect,
            @Value("${hibernate.show_sql}") boolean showSQL,
            @Value("${hibernate.format_sql}") boolean formatSQL,
//            @Value("{hibernate.connection.autocommit}") boolean autoCommit,
            @Value("${hibernate.hbm2ddl.auto}") String validateSchema
    ) {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.show_sql", showSQL);
        properties.put("hibernate.format_sql", formatSQL);
//        properties.put("hibernate.connection.autocommit", autoCommit);
        properties.put("hibernate.hbm2ddl.auto", validateSchema);
        return properties;
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource,
                                         @Value("${hibernate.packagesToScan}") String packagesToScan,
                                         @Qualifier("hibernateProperties") Properties properties) throws Exception {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan(packagesToScan);
        sessionFactoryBean.setHibernateProperties(properties);
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
