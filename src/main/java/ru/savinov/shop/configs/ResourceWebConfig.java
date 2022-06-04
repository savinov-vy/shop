package ru.savinov.shop.configs;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class ResourceWebConfig implements WebMvcConfigurer {
    final Environment environment;

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        String location = environment.getProperty("app.file.upload-dir");
        registry.addResourceHandler("/uploads/**").addResourceLocations(location);
    }
}