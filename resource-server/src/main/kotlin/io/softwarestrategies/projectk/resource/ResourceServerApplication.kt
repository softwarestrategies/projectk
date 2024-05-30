package io.softwarestrategies.projectk.resource

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class, HibernateJpaAutoConfiguration::class])
class ResourceServerApplication

fun main(args: Array<String>) {
    SpringApplicationBuilder()
        .sources(ResourceServerApplication::class.java) //replace with your application class
        .properties("spring.config.location=classpath:/application.yml,file:/usr/local/projectk/projectk-external.properties")
        .build()
        .run(*args)
}
