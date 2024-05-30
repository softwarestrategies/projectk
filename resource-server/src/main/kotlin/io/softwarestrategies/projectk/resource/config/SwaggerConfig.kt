package io.softwarestrategies.projectk.resource.config

import io.swagger.v3.oas.annotations.ExternalDocumentation
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License

@OpenAPIDefinition(
    info = Info(
        title = "ProjectK Resource Server",
        version = "v1.0",
        description = "REST API Documentation",
        contact = Contact(
            name = "Henry Dall",
            email = "henrydall@softwarestrategies.io",
            url = "https://www.softwarestrategies.io"
        ),
        license = License(
            name = "Apache 2.0",
            url = "https://www.javaguides.net/license"
        )
    ),
    externalDocs = ExternalDocumentation(
        //description = "Spring Boot User Management Documentation",
        //url = "https://www.javaguides.net/user_management.html"
    )
)
class SwaggerConfig {
}