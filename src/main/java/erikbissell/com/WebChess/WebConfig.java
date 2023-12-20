package erikbissell.com.WebChess;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    
    public void addCorsMapping(CorsRegistry registry){
        registry.addMapping("/api/**")
        .allowedOrigins("http://localhost3000")
        .allowedMethods("GET", "POST","PUT","DELETE")
        .allowedHeaders("*")
        .allowCredentials(true)
        .maxAge(3600);
    }
}
