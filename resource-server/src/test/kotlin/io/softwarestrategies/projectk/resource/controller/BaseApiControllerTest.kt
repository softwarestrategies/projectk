package io.softwarestrategies.projectk.resource.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.softwarestrategies.projectk.resource.config.TestSecurityConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc

@ActiveProfiles("test")
@ContextConfiguration(classes = [ TestSecurityConfig::class ] )
@WebMvcTest
abstract class BaseApiControllerTest {

    @Autowired
    protected lateinit var mockMvc: MockMvc

    protected val objectMapper: ObjectMapper = ObjectMapper()

    protected val REGULAR_USER_USERNAME = "test-regular-user"
    protected val REGULAR_USER_PASSWORD = "5D4afcK5j@4M9cDP"
}