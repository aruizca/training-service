package com.autentia.training.config

import groovy.sql.Sql
import groovy.util.logging.Log4j
import org.apache.ibatis.datasource.pooled.PooledDataSource
import org.h2.server.web.WebServlet
import org.springframework.boot.context.embedded.ServletRegistrationBean
import org.springframework.core.io.ClassPathResource

import javax.sql.DataSource

import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.jdbc.datasource.SimpleDriverDataSource

@Configuration
@MapperScan('com.autentia.training.persistence')
@Log4j
class DataConfig {

    @Bean
    DataSource dataSource() {
        log.debug('Initialising the DB....')
        PooledDataSource dataSource = new PooledDataSource(
                'org.h2.Driver',
                'jdbc:h2:mem:testDb:MVCC=true;LOCK_TIMEOUT=5000',
                'sa',
                ''
        )

        initializeDB(dataSource)

        log.debug('DB initialised!')

        return dataSource
    }

    void initializeDB(DataSource dataSource) {
        Sql sql = Sql.newInstance(dataSource)

        log.debug('Creating DB schema...')
        File schemaFile = new ClassPathResource('scripts/sql/schema-h2.sql').getFile()
        try {
            sql.execute(schemaFile.text)
        } catch (e) {
            log.error(e)
        }

        log.debug('Adding DB data...')
        File dataFile = new ClassPathResource('scripts/sql/data-h2.sql').getFile()
        sql.execute(dataFile.text)

    }

    @Bean
    DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource())
    }

    @Bean
    SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean()
        sessionFactory.setDataSource(dataSource())
        sessionFactory.setTypeAliasesPackage('com.autentia.training.domain')
        return sessionFactory
    }

    /**
     * This bean enables h2 web console at /console path
     * @return
     */
    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }
}