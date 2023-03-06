package com.jz.mall.config;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


/**
 * Swagger2API文档的配置
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())//注入相关API信息
                .select()
                //为当前包下controller生成API文档
//                .apis(RequestHandlerSelectors.basePackage("com.jz.mall.core.controller"))
                //为有@Api注解的Controller生成API文档
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //为有@ApiOperation注解的方法生成API文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                //添加登录认证
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("SwaggerUI演示")
                    .description("jz-mall-admin")
                    .contact(new Contact("ShenLiang","",""))
                    .version("1.0")
                    .build();
        }

        /*
        private List<SecurityScheme> securitySchemes(){
            List<SecurityScheme> list = new ArrayList<>();
            // basicAuth SwaggerBootstrapUI支持的不好,使用swagger原生UI
            list.add(new BasicAuth("basicAuth"));
            // name 为参数名  keyname是页面传值显示的 keyname， name在swagger鉴权中使用
            list.add(new ApiKey("access_token", "access_token", "header"));
            list.add(new ApiKey("query_token鉴权值-参数名称", "query_token", "query"));
            return list;
        }
         */

    private List<ApiKey> securitySchemes() {
        //设置请求头信息
        List<ApiKey> result = new ArrayList<ApiKey>();
        /*
         public ApiKey(String name, String keyname, String passAs) {
                this(name, keyname, passAs, new ArrayList());
            }
         */
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", "header");
        result.add(apiKey);
        return result;
    }


    private List<SecurityContext> securityContexts() {
        //设置需要登录认证的路径
        List<SecurityContext> result = new ArrayList();
        result.add(getContextByPath("/pmsBrand/.*"));
        return result;
    }

    private SecurityContext getContextByPath(String pathRegex){
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result = new ArrayList();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        result.add(new SecurityReference("Authorization", authorizationScopes));
        return result;
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
//                .apis(RequestHandlerSelectors.basePackage("com.jz.mall.core.controller"))
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
