package com.dumbo.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * --Swagger2配置类--
 *
 * Created by Dumbo on 2018/4/21
 **/

@Configuration   //让Spring来加载该类配置
@EnableSwagger2
public class Swagger2Config {
        @Bean
        public Docket createRestApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo()) //创建该Api的基本信息（这些基本信息会展现在文档页面中）
                    .select()   //返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现
                    .apis(RequestHandlerSelectors.basePackage("com.dumbo.server.controller"))
                    .paths(PathSelectors.any())
                    .build();
        }

        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("My RESTful APIs")
                    .description("Spring Boot中使用Swagger2构建RESTful APIs")
                    .contact(new Contact("Dumbo","",""))
                    .version("1.0")
                    .build();
        }
}
