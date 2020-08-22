package cn.edu.zju.drugtracing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @author Xinkang Wu
 * @date 2020/8/13 5:02 下午
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.edu.zju.drugtracing.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "药品溯源平台API文档",
                "基于以太坊平台的药品溯源平台\n智能合约查看地址：https://rinkeby.etherscan.io/address/0x6ff7463af40ddefab6db0f1572fccd7c3e190d0a",
                "API V2.4",
                "",
                new Contact("", "", ""),
                "", "", Collections.emptyList());
    }
}
