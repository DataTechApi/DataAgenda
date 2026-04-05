package br.com.datatech.DataAgenda.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "DataAgenda API",
                version = "1.0.0",
                description = "API para gerenciamento de manutençẽs dos sistemas da Empresa",
                contact = @Contact(
                        name = "DataTech",
                        url = "https://github.com/DataTechApi/DataAgenda"

                )

        )

)
public class OpenApiConfiguration {



}
