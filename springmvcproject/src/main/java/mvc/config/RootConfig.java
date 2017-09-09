package mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by axmedbek on 9/3/17.
 */
@Configuration
@ComponentScan("mvc")
@EnableWebMvc
public class RootConfig {

    @Bean
    public MultipartResolver multipartResolver()
    {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        multipartResolver.setMaxInMemorySize(0);
        multipartResolver.setMaxUploadSize(2*1024*1024);
        return multipartResolver;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean()
    {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan(new String[]{"mvc.model"});
        sessionFactoryBean.setHibernateProperties(properties());
        return sessionFactoryBean;
    }


    public Properties properties()
    {
        return new Properties()
        {
            {
                setProperty("hibernate.hbm2ddl.auto","update");
                setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5InnoDBDialect");
                setProperty("hibernate.show_sql","true");
                setProperty("hibernate.connection.characterEncoding","UTF-8");
                setProperty("hibernate.connection.useUnicode","true");
            }
        };
    }

    @Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mvc");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

}
