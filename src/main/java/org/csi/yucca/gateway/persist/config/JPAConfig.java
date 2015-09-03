package org.csi.yucca.gateway.persist.config;

import javax.sql.DataSource;

import org.csi.yucca.gateway.persist.entity.Event;
import org.csi.yucca.gateway.service.dto.EventDto;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({"org.csi.yucca.gateway.persist", "org.csi.yucca.gateway.service"})
@EnableJpaRepositories("org.csi.yucca.gateway.persist")
public class JPAConfig {

	@Autowired
    private DataSource datasource;
	/*@Bean
	public DataSource dataSource() {
	    return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build();
	}*/

	/*@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
	    HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
	    adapter.setShowSql(true);
	    adapter.setGenerateDdl(true);
	    adapter.setDatabase(Database.HSQL);
	    return adapter;
	}*/

    @Bean(name = "entityManagerFactory")
    @Autowired
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(datasource);  //dataSource());
        factoryBean.setPackagesToScan(new String[] { "org.csi.yucca.gateway.persist" });
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
    @Bean
    public BeanMappingBuilder beanMappingBuilder() {
        BeanMappingBuilder builder = new BeanMappingBuilder() {
            protected void configure() {
                mapping(EventDto.class, Event.class);
            }
        };

        return builder;
    }

    @Bean
    public DozerBeanMapper getMapper() {
        //return new DozerBeanMapper();
    	DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(beanMappingBuilder());

        return mapper;
    }

}
