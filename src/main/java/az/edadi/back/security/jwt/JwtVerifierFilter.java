package az.edadi.back.security.jwt;

import az.edadi.back.constants.UserAuthority;
import az.edadi.back.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
@Slf4j
public class JwtVerifierFilter extends OncePerRequestFilter {

    private final JwtBean jwtBean;
    private final JwtService jwtService;


    public JwtVerifierFilter(JwtBean jwtBean, JwtService jwtService) {
        this.jwtBean = jwtBean;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://edauni.com");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("Accept-Language", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, Accept-Language, remember-me");
        String requestHeader = httpServletRequest.getHeader(AUTHORIZATION);

        if (requestHeader != null && requestHeader != "") {
            String token = requestHeader.replace(jwtBean.getTitle(), "");
            Claims body = jwtService.getAccessTokenClaims(token);
            List<String> userAuthorityList = (List<String>) body.get("authorities");
            String username = body.getSubject();
            Object id = body.get("id");

            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = userAuthorityList.stream()
                    .map(s -> new SimpleGrantedAuthority(UserAuthority.valueOf(s).getPermission()))
                    .collect(Collectors.toSet());

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    id,
                    simpleGrantedAuthorities
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

}
