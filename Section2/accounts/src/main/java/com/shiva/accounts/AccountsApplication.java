package com.shiva.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef ="auditAwareImpl") /** Please Activating the jpa auditing and Leverage the bean with name auditAwareImpl**/
@OpenAPIDefinition(
        info=@Info(
                title="Account Microservice",
                description = "Account Microservice REST API",
                version = "V1",
                contact = @Contact(
                        name="Shivam Samadhiya",
                        email = "shivam@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0"
                )

        ),
            externalDocs=@ExternalDocumentation(
                     description = "This is The Account Microservice...."
            )
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
