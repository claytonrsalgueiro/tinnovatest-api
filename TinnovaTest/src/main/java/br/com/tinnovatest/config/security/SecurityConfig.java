/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tinnovatest.config.security;

import javax.servlet.Filter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

/**
 *
 * @author clayton.salgueiro
 */
@Configuration
@ConditionalOnProperty(name = "https-force", havingValue = "false")
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@SuppressWarnings("unused")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        
        http.requiresChannel().requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null).requiresSecure();

        http.authorizeRequests()
                .antMatchers("/ui/**", "/api/v1/public/**", "/api/v1/files/public/**", "/login",
                        "/", "/*.png", "/css/**", "/js/**")
                .permitAll()
                .antMatchers("/", "/error**").permitAll()
                .anyRequest().authenticated().and().logout().invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .and().csrf().disable().httpBasic().and()
                .addFilterBefore(this.corsFilter(), AbstractPreAuthenticatedProcessingFilter.class);

    }

    public Filter corsFilter() {
        return new CorsFilter();
    }

    
}
