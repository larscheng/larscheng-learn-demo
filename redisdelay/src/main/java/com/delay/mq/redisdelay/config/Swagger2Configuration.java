package com.delay.mq.redisdelay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationScopeBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;

/***
 *
 * @author zhengql
 */
@EnableSwagger2
@Configuration
public class Swagger2Configuration {


    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.delay.mq.redisdelay.web"))
                //select any api that matches one of these paths
                .paths(PathSelectors.any())
                .build()

                //Sets up the security schemes used to protect the apis. Supported schemes are ApiKey, BasicAuth and OAuth
                .securitySchemes(newArrayList(apiKeySecurityScheme()))
                .securityContexts(newArrayList(apiKeySecurityContext()))

                ;
    }



    private ApiInfo apiInfo() {
        Contact contact = new Contact("us", "", "zhengqilong6@gmail.com");
        return new ApiInfoBuilder()
                .title("redisMq API")
                .description("redisMq API，部分接口需要 Token 验证，可通过 Authorize 按钮输入 Token，如有疑问请 Contact us")
                .termsOfServiceUrl("http://www.senthink.com")
                .contact(contact)
                .license("redisMq License Version 1.0.0")
                .licenseUrl("")
                .version("1.0.0")
                .build();
    }

    private SecurityScheme apiKeySecurityScheme() {
        return new ApiKey("Token", "Authorization", "header");
    }

    private SecurityContext apiKeySecurityContext() {
        AuthorizationScope[] authScopes = new AuthorizationScope[1];
        authScopes[0] = new AuthorizationScopeBuilder()
                .scope("read")
                .description("access")
                .build();
        SecurityReference securityReference = SecurityReference.builder()
                .reference("Token")
                .scopes(authScopes)
                .build();

        return SecurityContext.builder().securityReferences(newArrayList(securityReference)).build();
    }

    private SecurityScheme basicAuthSecurityScheme() {
        return new BasicAuth("basicAuth");
    }

    private SecurityContext basicAuthSecurityContext() {
        AuthorizationScope[] authScopes = new AuthorizationScope[1];
        authScopes[0] = new AuthorizationScopeBuilder()
                .scope("global")
                .description("access")
                .build();
        SecurityReference securityReference = SecurityReference.builder()
                .reference("basicAuth")
                .scopes(authScopes)
                .build();

        return SecurityContext.builder().securityReferences(newArrayList(securityReference)).build();
    }

}