package com.app.shop.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Shop app api collection",
                version = "0.0.1",
                contact = @Contact(
                        name = "Nguyen Van Huy",
                        email = "nvh1892kw@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"
                )

        ),
        servers = {
                @Server(url = "http://localhost:1892/api/v1", description = "Local development"),
                @Server(url = "http://localhost:1892/api/v1", description = "Production")
        },
        tags = {
                @Tag(name = "auth", description = "Operations related to order processing"),
                @Tag(name = "user", description = "Operations related to user management"),
                @Tag(name = "role", description = "Operations related to role processing"),
                @Tag(name = "permission", description = "Operations related to permission processing"),
                @Tag(name = "product", description = "Operations related to product management"),
                @Tag(name = "order", description = "Operations related to order processing"),
                @Tag(name = "category", description = "Operations related to category processing"),
                @Tag(name = "order detail", description = "Operations related to order detail processing")
        }
)
@SecurityScheme(
        name = "bearer-key",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
@Configuration
public class OpenAPIConfig {
}
