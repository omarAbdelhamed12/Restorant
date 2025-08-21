package com.spring.boot.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Restaurant_App",
                description = "to take order",
                version = "version 1",
                contact = @Contact(
                        name = "Omar Abdelhamed",
                        email = "omar_4.5@icloud.com",
                        url = "https://www.facebook.com/omar.abdelhamed.399"
                ),
                license = @License(
                        url = "https://www.facebook.com/omar.abdelhamed.399",
                        name = "Restaurant"
                )
        )
)
public class SwaggerConfig {
}
