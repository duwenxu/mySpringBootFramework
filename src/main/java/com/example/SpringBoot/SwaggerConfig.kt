package com.example.SpringBoot

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
open class SwaggerConfig {

    @Bean
    open fun api(): Docket {
        val pars = ArrayList<Parameter>()
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("com.waytogalaxy.display"))
                .paths(PathSelectors.any())
                .build()
//                .consumes(setOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
//                .securitySchemes(securitySchemes())
//                .securityContexts(listOf(securityContext()))
                .globalOperationParameters(pars)
                .apiInfo(apiInfo())
    }

    private fun securitySchemes(): MutableList<out SecurityScheme>? {
        return arrayListOf(ApiKey("apiKey", "Authorization", "header"))
    }

    private fun securityContext(): SecurityContext {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build()
    }

    private fun defaultAuth(): List<SecurityReference> {
        val auth = AuthorizationScope("global", "accessEverything")
        val authScope = arrayOf(auth)
        return listOf(SecurityReference("apiKey", authScope))
    }


    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("嗜血的蚂蚁 Restful api文档")
                .description("restful 风格对外接口")
                //服务条款网址
                //.termsOfServiceUrl("http://blog.csdn.net/forezp")
                .version("1.0")
                //.contact(new Contact("帅呆了", "url", "email"))
                .build()
    }

}