package az.edadi.back.security;
import az.edadi.back.security.jwt.JwtBean;
import az.edadi.back.security.jwt.JwtVerifierFilter;
import az.edadi.back.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurer {
    
    private final JwtBean jwtBean;
    private final JwtService jwtService;


    @Autowired
    public SecurityConfigurer(
            JwtBean jwtBean,
            JwtService jwtService) {
        this.jwtBean = jwtBean;
        this.jwtService = jwtService;

    }


    private final String[] WHITE_LIST = {
            "/api/auth/**",
            "/api/search",
            "/ws/**",
            "/ws"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtVerifierFilter jwtVerifierFilter = new JwtVerifierFilter(jwtBean, jwtService);
        return http
                .csrf(csrf->csrf.disable())
                 .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITE_LIST).permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtVerifierFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


}