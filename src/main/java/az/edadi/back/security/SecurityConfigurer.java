package az.edadi.back.security;
import az.edadi.back.security.jwt.JwtBean;
import az.edadi.back.security.jwt.JwtVerifierFilter;
import az.edadi.back.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer {
    
    private final JwtBean jwtBean;
    private final JwtService jwtService;
    public SecurityConfigurer(JwtBean jwtBean, JwtService jwtService) {
        this.jwtBean = jwtBean;
        this.jwtService = jwtService;
    }
    private final String[] POST_WHITE_LIST = {
            "/api/v1/auth/**",
            "/api/search",
            "/ws/**",
            "/ws"
    };
    private final String[] GET_WHITE_LIST = {
            "/api/v1/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/api/search",
            "/h2-console/**",
            "/ws/**",
            "/ws",
            "/"
    };



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtVerifierFilter jwtVerifierFilter = new JwtVerifierFilter(jwtBean, jwtService);
        return http
                .csrf(AbstractHttpConfigurer::disable)
                 .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET,GET_WHITE_LIST).permitAll()
                        .requestMatchers(HttpMethod.POST, POST_WHITE_LIST).permitAll()
                         .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .addFilterBefore(jwtVerifierFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


}