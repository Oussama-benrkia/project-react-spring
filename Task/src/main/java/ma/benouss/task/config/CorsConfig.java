package ma.benouss.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;


@Configuration
public class CorsConfig {

    @Value("${app.front-end}")
    private String font;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(font)
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .allowCredentials(true); // Allows credentials (e.g., cookies)
            }
        };
    }
}
