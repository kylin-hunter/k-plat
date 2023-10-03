package io.github.kylinhunter.plat.web.configuration;

import io.github.kylinhunter.plat.web.security.DefaultSecurityWebSecurityConfigurer;
import io.github.kylinhunter.plat.web.security.filter.JwtLoginFilter;
import io.github.kylinhunter.plat.web.security.filter.JwtVerifyFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-01 00:27
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AutoSecurityWebSecurityConfigurer extends DefaultSecurityWebSecurityConfigurer {



  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http

        .authorizeRequests()
        //
//        .antMatchers("/auth/verify_token").permitAll()
//        .antMatchers("/core/auth/verify_token").permitAll()
//        .antMatchers("/auth/verify_token").anonymous()
//        .antMatchers("/core/auth/verify_token").anonymous()
//        .antMatchers("/core/login").permitAll()
//        .antMatchers("/error").anonymous()
        // 除上面外的所有请求全部需要鉴权认证

        .anyRequest().authenticated()
        .and().formLogin().loginProcessingUrl("/login")

        .and()
        .logout().logoutUrl("/logout")
        .and().exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler(responseWriter))
        .authenticationEntryPoint(authenticationEntryPoint(responseWriter))
        .and()
        .addFilter(new JwtLoginFilter(authenticationManagerBean(), tokenService, responseWriter))
        .addFilter(new JwtVerifyFilter(authenticationManagerBean(), traceHandler, tokenService,
            userContextHandler, responseWriter))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .cors()
        .and().csrf().disable();


  }





}