package com.example.Eticaret;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path brandUploadDir= Paths.get("./brand-logos");

        String brandUploadPath=brandUploadDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/brand-logos/**").addResourceLocations("file:/"+brandUploadPath+"/");

        Path productUploadDir=Paths.get("./product-images");
        String productUploadPath=productUploadDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/product-images/**").addResourceLocations("file:/"+productUploadPath+"/");

    }
}
