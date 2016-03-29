package br.com.clubelinkar.support.security

import br.com.clubelinkar.support.security.handler.AjaxAuthenticationFailureHandler
import br.com.clubelinkar.support.security.handler.AjaxAuthenticationSuccessHandler
import br.com.clubelinkar.support.security.handler.AjaxLogoutSuccessHandler
import br.com.clubelinkar.support.security.provider.SecurityAuthenticationProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

import javax.annotation.Resource

/**
 * @author Lennon Jesus
 */
@Configuration
@EnableWebSecurity
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    private AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler

    @Resource
    private AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler

    @Resource
    private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler

    @Resource
    private Http401UnauthorizedEntryPoint authenticationEntryPoint

    @Resource
    SecurityAuthenticationProvider securityAuthenticationProvider

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder()
    }

    @Autowired
    private UserDetailsService userDetailsService

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(securityAuthenticationProvider)
        auth.userDetailsService(userDetailsService)
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .realmName("com.lennonjesus")
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .and()
                .formLogin()
                .loginProcessingUrl("/api/authentication")
                .successHandler(ajaxAuthenticationSuccessHandler)
                .failureHandler(ajaxAuthenticationFailureHandler)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler(ajaxLogoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/api/authentication").permitAll()
                .antMatchers("/api/authenticate").permitAll()
//                .antMatchers("/api/v2/api-docs").permitAll()
//                .antMatchers("/api/**").hasAnyAuthority("ROLE_USER")
                .anyRequest().authenticated()
                .and()
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
        

    }

}