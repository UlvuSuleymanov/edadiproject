package com.camaat.first.security;

import com.camaat.first.security.jwt.JwtVerifierFilter;
import com.camaat.first.security.jwt.JwtBean;
import com.camaat.first.service.UserPrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigurer  extends WebSecurityConfigurerAdapter {

     @Autowired
      UserPrincipalService userPrincipalService;
     @Autowired
     PasswordEncoder passwordEncoder;
     @Autowired
     JwtBean jwtBean;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userPrincipalService).passwordEncoder(passwordEncoder);
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .authorizeRequests()

//                .antMatchers(GET,"/api/search/**").permitAll()

                .antMatchers(GET,"/api/**").permitAll()
                .antMatchers(POST, "/api/auth/**").permitAll()
              //  .antMatchers(GET,"/api/university/{abbr}/posts").permitAll()
//                .antMatchers(GET,"/api/user/{username}").permitAll()
//
//                .antMatchers(GET,"/api/user/check/**").permitAll()
//
//                .antMatchers(GET,"/api/tag").permitAll()
//
//                .antMatchers(GET,"/api/posts").permitAll()
//
//                .antMatchers(GET,"/api/speciality").permitAll()
//
//                .antMatchers(GET,"/api/post/{postId}").permitAll()
//
//                .antMatchers(GET,"/api/uni/{abbr}/speciality").permitAll()
//
//                .antMatchers(GET,"/api/uni").permitAll()
//
//                .antMatchers(GET,"/api/uni/**").permitAll()
//
//                .antMatchers(GET,"/api/post/{postId}/comments").permitAll()

                 .anyRequest().authenticated()
                .and()

                .addFilterAfter(new JwtVerifierFilter(jwtBean, userPrincipalService), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests();
    }



}
