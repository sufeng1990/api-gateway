//package ai.shuzhi.iot.gateway.apigateway.config;
//
//
//import io.swagger.annotations.Api;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
///**
// * Swagger2的接口配置
// *
// * @author sufeng
// * @date 2019/06/01
// */
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    @Bean
//    Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//            .apiInfo(apiInfo())
//            .forCodeGeneration(true)
//            .useDefaultResponseMessages(false)
//            .select()
//            .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
//            .paths(PathSelectors.any())
//            .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//            .title("数知科技-IoT应用网关")
//            .description("API接口文档")
//            .termsOfServiceUrl("http://www.shuzhi.ai")
//            .contact(new Contact("数知科技", "http://www.shuzhi.ai", ""))
//            .version("1.0")
//            .build();
//    }
//}