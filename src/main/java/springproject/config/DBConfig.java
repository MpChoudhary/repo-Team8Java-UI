package springproject.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DBConfig {
    //    @Value("${db.driver}")
    private String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    //    @Value("${db.password}")
    private String PASSWORD = "reallyStrong!123";

    //    @Value("${db.url}")
    private String URL = "jdbc:sqlserver://192.168.99.100\\\\\\\\sql_server:1433;databaseName=SpringProject";

    //    @Value("${db.username}")
    private String USERNAME = "sa";

    //    @Value("${hibernate.dialect}")
    private String DIALECT = "org.hibernate.dialect.SQLServerDialect";

    //    @Value("${hibernate.show_sql}")
    private String SHOW_SQL = "true";

    //    @Value("${hibernate.hbm2ddl.auto}")
    private String HBM2DDL_AUTO = "update";

    //    @Value("${entitymanager.packagesToScan}")
    private String PACKAGES_TO_SCAN = "springproject";

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    //    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", DIALECT);
        hibernateProperties.put("hibernate.show_sql", SHOW_SQL);
        hibernateProperties.put("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
}