package com.ut.masterCode.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    Environment environment;

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ut.masterCode"))
                .paths(regex("/*.*"))
                .build()
                .protocols(protocols())
                .securitySchemes(securitySchemes())
                .apiInfo(metaInfo());
    }


    private ApiInfo metaInfo() {
        return new ApiInfo(
                "Structure Project APi",
                "",
                environment.getProperty("swagger.version"),
                null,
                new Contact(null, null, null),
                null, "", Collections.emptyList());
    }

    private List<? extends SecurityScheme> securitySchemes() {
        return Collections.<SecurityScheme>singletonList(new ApiKey("Bearer", "Authorization", "header"));
    }

    private Set<String> protocols() {
        Set<String> protocols = new HashSet<>();
        protocols.add("http");
        protocols.add("https");
        return protocols;
    }

}
