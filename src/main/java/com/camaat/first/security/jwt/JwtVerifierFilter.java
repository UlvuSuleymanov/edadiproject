package com.camaat.first.security.jwt;

import com.camaat.first.entity.UserAuthority;
import com.camaat.first.service.UserPrincipalService;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
 import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class JwtVerifierFilter extends OncePerRequestFilter {

      private  final JwtBean jwtBean;
       private  final UserPrincipalService userPrincipalService;

     public JwtVerifierFilter(JwtBean jwtBean, UserPrincipalService userPrincipalService) {
         this.jwtBean = jwtBean;
         this.userPrincipalService = userPrincipalService;
     }

     @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

         httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
         httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
         httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
         httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
         httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");

         String requestHeader = httpServletRequest.getHeader(AUTHORIZATION);

         if(requestHeader!=null && requestHeader!="") {
             try {

                 JwtProvider jwtProvider = new JwtProvider(jwtBean);

                 String token = requestHeader.replace(jwtBean.getTitle(), "");


                 Claims body = jwtProvider.getTokenClaims(token);
                 List<String> userAuthorityList = (List<String>) body.get("authorities");

                 Object id = body.get("id");

                 Set<SimpleGrantedAuthority> simpleGrantedAuthorities=  userAuthorityList.stream()
                          .map(s ->new SimpleGrantedAuthority( UserAuthority.valueOf(s).getPermission()) )
                          .collect(Collectors.toSet());
                 String username = jwtProvider.getUsernameFromToken(token);

                  Authentication authentication = new UsernamePasswordAuthenticationToken(
                          username,
                          id,
                          simpleGrantedAuthorities
                 );
                 SecurityContextHolder.getContext().setAuthentication(authentication);


             } catch (Exception e) {
                 throw new RuntimeException(e);
             }
         }


         filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
