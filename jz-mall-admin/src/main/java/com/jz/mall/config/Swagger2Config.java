package com.jz.mall.config;


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
 * Swagger2API文档的配置
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包下controller生成API文档
                .apis(RequestHandlerSelectors.basePackage("com.jz.mall.generator.controller"))
                //为有@Api注解的Controller生成API文档
//                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //为有@ApiOperation注解的方法生成API文档
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SwaggerUI演示")
                .description("jz-mall-admin")
                .contact(new Contact("ShenLiang","",""))
                .version("1.0")
                .build();
    }
}


//@EnableSwagger2
//@Configuration
//public class SwaggerConfig {

    //构建一个Docket
    /*
        public Docket(DocumentationType documentationType) {
        this.apiInfo = ApiInfo.DEFAULT;
        this.groupName = "default";
        this.enabled = true;
        this.genericsNamingStrategy = new DefaultGenericTypeNamingStrategy();
        this.applyDefaultResponseMessages = true;
        this.host = "";
        this.pathMapping = Optional.absent();
        this.apiSelector = ApiSelector.DEFAULT;
        this.enableUrlTemplating = false;
        this.vendorExtensions = Lists.newArrayList();
        this.documentationType = documentationType;
    }
     */
//    @Bean
//    public Docket createRestfulApi(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())//文档信息
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.jz.mall.generator.controller"))
////                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)) //为有@Api注解的Controller生成API文档
////                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))//为有@ApiOperation注解的方法生成API文档
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    /*
//        private String title;
//        private String description;
//        private String termsOfServiceUrl;
//        private Contact contact;
//        private String license;
//        private String licenseUrl;
//        private String version;
//        private List<VendorExtension> vendorExtensions = Lists.newArrayList();
//
//        public ApiInfoBuilder() {
//        }
//     */
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("API文档")
//                .description("jz-mall-admin")
//                .contact("ShenLiang")
//                .version("1.0")
//                .build();
//    }

//}
