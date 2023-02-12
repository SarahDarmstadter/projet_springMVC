package com.exemple.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.exemple.repository")
public class JpaConfiguration {

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      DataSource dataSource, Environment environment) {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(dataSource);
    entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    entityManagerFactoryBean.setPackagesToScan("com.example.model");

    Properties jpaProperties = new Properties();
    jpaProperties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
    jpaProperties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
    jpaProperties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
    jpaProperties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));

    entityManagerFactoryBean.setJpaProperties(jpaProperties);

    return entityManagerFactoryBean;
  }
//  Une transaction Hibernate est un ensemble d'opérations effectuées sur la base de données
//  qui doivent être traitées de manière cohérente et atomique.
//  Soit toutes les opérations sont effectuées avec succès, soit aucune n'est effectuée.
//  Cela garantit que la base de données reste dans un état cohérent, même en cas d'erreur
//  ou d'exception pendant le traitement des opérations.
//  Les transactions sont gérées à l'aide de la classe Transaction fournie par Hibernate.
//  Pour démarrer une transaction, vous appelez la méthode begin() sur une instance de la classe Transaction.
//  Pour terminer une transaction, vous appelez la méthode commit() ou rollback(),
//  selon que vous souhaitez enregistrer les modifications ou les annuler.
//  Lorsqu'une transaction est en cours, les opérations effectuées sur la base de données sont enregistrées
//  dans une unité de travail Hibernate. Lorsque la transaction est terminée avec succès,
//  ces opérations sont persistantes dans la base de données.
//  Si la transaction échoue, les opérations sont annulées et la base de données est restaurée à son état précédent.


  @Bean
  public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(sessionFactory);
    return transactionManager;
  }
}