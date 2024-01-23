package az.edadi.back.config;

import az.edadi.back.constants.AppConstants;
import az.edadi.back.security.jwt.JwtBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class SecurityBeanConfig  {

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
    public WebMvcConfigurer corsConfigurer() {
        return new  WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(AppConstants.DOMAIN)
                        .allowedHeaders("*")
                        .allowedMethods("*");
            }
        };
    }

    public static void main(String[] args) {

        String arr = "adsfghj";

        char [] c = arr.toCharArray();
        int i=0;
        int j=c.length-1;
        while(i<j){
            char t=c[i];
            c[i]=c[j];
            c[j]=t;
            i++;
            j--;
        }

        for(int k=0;k<c.length;k++)
            System.out.println(c[k]);
    }

}
