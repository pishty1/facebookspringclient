/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.quickstart.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.quickstart.FileService;
import org.springframework.social.quickstart.TeamService;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Main configuration class for the application. Turns on @Component scanning, loads externalized application.properties, and sets up the database.
 *
 * @author Keith Donald
 */
@Configuration
@ComponentScan(basePackages = "org.springframework.social.quickstart")
@PropertySource("classpath:org/springframework/social/quickstart/config/application.properties")
@EnableJpaRepositories("org.springframework.social.quickstart")
@EnableTransactionManagement
public class MainConfig {


  @Bean(destroyMethod = "shutdown")
  public DataSource dataSource() {
    EmbeddedDatabaseFactory factory = new EmbeddedDatabaseFactory();
    factory.setDatabaseName("spring-social-quickstart");
    factory.setDatabaseType(EmbeddedDatabaseType.H2);
    factory.setDatabasePopulator(databasePopulator());
    return factory.getDatabase();
  }

  /*@Bean
  public PlatformTransactionManager transactionManager() {
    return new DataSourceTransactionManager(dataSource());
  }*/

  /*public DataSource dataSource() {
    URI uri = new URI();
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.

    return null;
  }*/

  // internal helpers

  private DatabasePopulator databasePopulator() {
    ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
    populator.addScript(new ClassPathResource("JdbcUsersConnectionRepository.sql", JdbcUsersConnectionRepository.class));
    return populator;
  }

  //Extras
//	@Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
//    }

  @Bean
  public FileService fileService() {
    return new FileService();
  }

  @Bean
  public CommonsMultipartResolver multipartResolver() {
    CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
    commonsMultipartResolver.setMaxUploadSize(1000000);
    return commonsMultipartResolver;
  }

  @Bean
  public TeamService teamService() {
    TeamService teamService = new TeamService();
    return teamService;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
    LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
    lef.setDataSource(dataSource);
    lef.setJpaVendorAdapter(jpaVendorAdapter);
    lef.setPackagesToScan("org.springframework.social.quickstart");
    return lef;
  }


  @Bean
  public JpaVendorAdapter jpaVendorAdapter() {
    HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
    hibernateJpaVendorAdapter.setShowSql(false);
    hibernateJpaVendorAdapter.setGenerateDdl(true);
    hibernateJpaVendorAdapter.setDatabase(Database.H2);
    return hibernateJpaVendorAdapter;
  }

  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource, EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(entityManagerFactory);
    jpaTransactionManager.setDataSource(dataSource);
    return jpaTransactionManager;
  }

}
