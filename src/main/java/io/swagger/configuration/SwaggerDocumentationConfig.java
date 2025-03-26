package io.swagger.configuration;

import jakarta.annotation.Generated;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-03-26T16:42:46.826388125Z[GMT]")
@Configuration
public class SwaggerDocumentationConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
            .info(new Info()
                .title("I18n-Server API")
                .description("This REST interface provides the client with the required translations and other information to function correctly.")
                .termsOfService("")
                .version("1.0.0")
                .license(new License()
                    .name("Apache 2.0")
                    .url("https://www.apache.org/licenses/LICENSE-2.0"))
                .contact(new io.swagger.v3.oas.models.info.Contact()
                    .email("felix.grebe@grebe-systems.de")));
    }

}
