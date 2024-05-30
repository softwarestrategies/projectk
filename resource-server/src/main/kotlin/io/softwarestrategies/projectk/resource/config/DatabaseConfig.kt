package io.softwarestrategies.projectk.resource.config

import com.mchange.v2.c3p0.ComboPooledDataSource
import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.beans.PropertyVetoException
import java.util.Properties
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(basePackages = ["io.softwarestrategies.projectk.resource.data.repository"])
@EnableTransactionManagement
@PropertySource(
    value = ["classpath:/db.properties", "file:/usr/local/projectk/external.properties"],
    ignoreResourceNotFound = true
)
class DatabaseConfig {

    @Autowired
    var env: Environment? = null

    @Bean
    @Primary
    @Throws(PropertyVetoException::class)
    fun dataSource(): DataSource {
        val dataSource: ComboPooledDataSource = ComboPooledDataSource()
        dataSource.setDriverClass(env!!.getProperty("spring.datasource.driver-class-name"))
        dataSource.setJdbcUrl(env!!.getProperty("spring.datasource.url"))
        dataSource.setUser(env!!.getProperty("spring.datasource.username"))
        dataSource.setPassword(env!!.getProperty("spring.datasource.password"))
        return dataSource
    }

    @Bean
    fun entityManagerFactory(dataSource: DataSource?): LocalContainerEntityManagerFactoryBean {
        val entityManagerFactoryBean = LocalContainerEntityManagerFactoryBean()
        entityManagerFactoryBean.setDataSource(dataSource)
        entityManagerFactoryBean.setPackagesToScan("io.softwarestrategies.projectk.resource") // Replace with the package where your entity classes are located
        entityManagerFactoryBean.setPersistenceUnitName("projectkPersistenceUnit") // Give a unique name
        entityManagerFactoryBean.setJpaProperties(hibernateProperties())

        val vendorAdapter = HibernateJpaVendorAdapter()
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter)

        entityManagerFactoryBean.setEntityManagerFactoryInterface(jakarta.persistence.EntityManagerFactory::class.java)

        return entityManagerFactoryBean
    }

    @Bean
    fun transactionManager(entityManagerFactory: EntityManagerFactory?): PlatformTransactionManager {
        val transactionManager = JpaTransactionManager()
        transactionManager.setEntityManagerFactory(entityManagerFactory)
        return transactionManager
    }

    private fun hibernateProperties(): Properties {
        val properties = Properties()
        properties.setProperty("hibernate.id.new_generator_mappings", "false")
        properties.setProperty("hibernate.show_sql", env!!.getProperty("show.sql", "false"))
        properties.setProperty("hibernate.format_sql", env!!.getProperty("format.sql", "true"))
        properties.setProperty("hibernate.use_sql_comments", env!!.getProperty("use_sql_comments", "true"))
        return properties
    }
}