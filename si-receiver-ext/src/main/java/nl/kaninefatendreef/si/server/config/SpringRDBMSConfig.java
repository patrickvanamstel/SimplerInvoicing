package nl.kaninefatendreef.si.server.config;

import java.util.Properties;


import javax.sql.DataSource;

import nl.kaninefatendreef.si.SIConfigurationRuntimeException;
import nl.kaninefatendreef.si.constant.SIConfigurationProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import nl.kaninefatendreef.si.server.config.SIServerProperties;

@Configuration
@EnableJpaRepositories(basePackages = {"nl.kaninefatendreef.si.server.repository.jpa"})
@EnableTransactionManagement
@Profile("rdbms")
public class SpringRDBMSConfig {

	private Logger _logger =  LoggerFactory.getLogger(SpringRDBMSConfig.class);
	
	@Autowired
	Environment _environment = null;
	
	//TODO read from the app server
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		String driverClassName = "org.postgresql.Driver";
		if (_environment.containsProperty(SIConfigurationProperties.SI_RECEIVER_RDBMS_DRIVER_CLASSNAME.getValue())){
			driverClassName = _environment.getProperty(SIConfigurationProperties.SI_RECEIVER_RDBMS_DRIVER_CLASSNAME.getValue());
		}
		dataSource.setDriverClassName(driverClassName);	
		String datsourceURL = null;
		String property = SIConfigurationProperties.SI_RECEIVER_RDBMS_DRIVER_URL.getValue();
		if (_environment.containsProperty(property)){
			datsourceURL = _environment.getProperty(property);
		}else{
			throw new SIConfigurationRuntimeException("Driver classname not found with property name " + property);
		}
		dataSource.setUrl(datsourceURL);
		
		String userName = null;
		property = SIConfigurationProperties.SI_RECEIVER_RDBMS_DRIVER_USERNAME.getValue();
		if (_environment.containsProperty(property)){
			userName = _environment.getProperty(property);
		}else{
			throw new SIConfigurationRuntimeException("Driver classname not found with property name " + property);
		}
		dataSource.setUsername(userName);
		String password = null;
		property = SIConfigurationProperties.SI_RECEIVER_RDBMS_DRIVER_PASSWORD.getValue();
		if (_environment.containsProperty(property)){
			password = _environment.getProperty(property);
		}else{
			throw new SIConfigurationRuntimeException("Driver password not found with property name " + property);
		}
		dataSource.setPassword(password);
		
		StringBuilder infobuilder = new StringBuilder();
		
		infobuilder.append("Datasource with the following properties intitialized");
		
		infobuilder.append("driverClassName : " + driverClassName + "\n");
		infobuilder.append("datsourceURL : " + datsourceURL + "\n");
		infobuilder.append("userName : " + userName + "\n");
		infobuilder.append("password : " + password + "\n");

		_logger.info(infobuilder.toString());
		
		return dataSource;
	}
	 
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = null;
		try {
			localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
			localContainerEntityManagerFactoryBean.setDataSource(dataSource());
			
			_logger.debug("Scanning packages for entities " + SIServerProperties.SI_RECEIVER_RDBMS_JPA_MODEL.getValue());
			
			// Add configurable properties
			localContainerEntityManagerFactoryBean.setPackagesToScan(new String[] { SIServerProperties.SI_RECEIVER_RDBMS_JPA_MODEL.getValue() });
			
			
			
			JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
			localContainerEntityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
			
			localContainerEntityManagerFactoryBean.setJpaProperties(additionalProperties());

		} catch (Throwable t) {
			_logger.error("While initantiating the entityManagerFactory" , t);
			throw new SIConfigurationRuntimeException(t);
		}
		return localContainerEntityManagerFactoryBean;
	}
	  
	Properties additionalProperties() {
		
		
		String hibernateDialect = "org.hibernate.dialect.PostgreSQLDialect" ;
		String property = SIConfigurationProperties.SI_RECEIVER_RDBMS_DRIVER_HIBERNATE_DIALECT.getValue();
		if (_environment.containsProperty(property)){
			hibernateDialect = _environment.getProperty(property);
		}
		 	
	    String hibernateHbm2dll = "update";
	    property = SIConfigurationProperties.SI_RECEIVER_RDBMS_DRIVER_HIBERNATE_HM2DLLAUTO.getValue();
		if (_environment.containsProperty(property)){
			hibernateHbm2dll = _environment.getProperty(property);
		}

	    String hibernateShowSql = "false";
	    property = SIConfigurationProperties.SI_RECEIVER_RDBMS_DRIVER_HIBERNATE_SHOW_SQL.getValue();
		if (_environment.containsProperty(property)){
			hibernateShowSql = _environment.getProperty(property);
		}
	    
	    
	    String hibernateFormatSql = "false";
	    property = SIConfigurationProperties. SI_RECEIVER_RDBMS_DRIVER_HIBERNATE_FORMAT_SQL.getValue();
		if (_environment.containsProperty(property)){
			hibernateFormatSql = _environment.getProperty(property);
		}

		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect",	hibernateDialect);
		properties.setProperty("hibernate.hbm2ddl.auto", hibernateHbm2dll);
		properties.setProperty("hibernate.show_sql", hibernateShowSql);
		properties.setProperty("hibernate.format_sql", hibernateFormatSql);
		return properties;
	}
	  
	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory()
				.getObject());

		return transactionManager;
	}
}
