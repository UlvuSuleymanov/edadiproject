package az.edadi.back.config;

import az.edadi.back.security.jwt.JwtBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityBeanConfig {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    @ConfigurationProperties(prefix = "app.jwt")
    JwtBean jwtBean(){
        return new JwtBean();
    }

    @Bean
    public ErrorPageRegistrar errorPageRegistrar() {
        return (ErrorPageRegistry epr) -> {
            epr.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/resources/static/index.html"));
        };
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new  WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedHeaders("*")
                        .allowedMethods("*");
            }
        };
    }


}
