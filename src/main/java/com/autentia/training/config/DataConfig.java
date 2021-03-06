package com.autentia.training.config;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.h2.server.web.WebServlet;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.InputStream;

@Configuration
@MapperScan("com.autentia.training.mapper")
public class DataConfig {

    private static Log log = LogFactory.getLog(DataConfig.class);

    @Bean
    public DataSource dataSource() throws Exception {
        log.debug("Initialising the DB....");

        PooledDataSource dataSource = new PooledDataSource("org.h2.Driver", "jdbc:h2:mem:testDb:MVCC=true;LOCK_TIMEOUT=5000", "sa", "");

        initializeDB(dataSource);

        log.debug("DB initialised!");

        return dataSource;
    }

    /**
     * Initialize DB schema and loads initial set of data
     *
     * @param dataSource
     */
    public static void initializeDB(DataSource dataSource) throws Exception{
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        log.debug("Creating DB schema...");
        InputStream schemaFileInputStream = new ClassPathResource("scripts/sql/schema-h2.sql").getInputStream();
        jdbcTemplate.execute(IOUtils.toString(schemaFileInputStream, "UTF-8"));

        log.debug("Adding DB data...");
        InputStream dataFileInputStream = new ClassPathResource("scripts/sql/data-h2.sql").getInputStream();
        jdbcTemplate.execute(IOUtils.toString(dataFileInputStream, "UTF-8"));
    }

    @Bean
    public DataSourceTransactionManager transactionManager() throws Exception {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        return sqlSessionFactory.getObject();
    }

    /**
     * This bean enables H2 web console at /console path
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }

}
