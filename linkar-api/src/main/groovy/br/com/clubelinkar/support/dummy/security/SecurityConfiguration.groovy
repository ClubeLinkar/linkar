//package br.com.clubelinkar.support.dummy.security
//
//import br.com.clubelinkar.support.dummy.security.authentication.AjaxAuthenticationFailureHandler
//import br.com.clubelinkar.support.dummy.security.authentication.AjaxAuthenticationSuccessHandler
//import br.com.clubelinkar.support.dummy.security.authentication.AjaxLogoutSuccessHandler
//import br.com.clubelinkar.support.dummy.security.authentication.LinkarAuthenticationProvider
//import br.com.clubelinkar.support.dummy.security.authorization.Http401UnauthorizedEntryPoint
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
//import org.springframework.security.crypto.password.PasswordEncoder
//import org.springframework.web.context.request.RequestContextListener
//
//import javax.annotation.Resource
//
//
///**
// * Application Security Configuration
// */
//@Configuration
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Resource
//    private AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler
//
//    @Resource
//    private AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler
//
//    @Resource
//    private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler
//
//    @Resource
//    private Http401UnauthorizedEntryPoint authenticationEntryPoint
//
//    @Resource
//    private LinkarAuthenticationProvider linkarAuthenticationProvider
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder()
//    }
//
//
//    @Bean
//    @ConditionalOnMissingBean(RequestContextListener.class)
//    public RequestContextListener requestContextListener() {
//        return new RequestContextListener()
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(linkarAuthenticationProvider)
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http
//                .httpBasic()
//                .realmName("linkar")
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint)
//                .and()
//                .formLogin()
//                .loginProcessingUrl("/api/authentication")
//                .successHandler(ajaxAuthenticationSuccessHandler)
//                .failureHandler(ajaxAuthenticationFailureHandler)
//                .and()
//                .logout()
//                .logoutUrl("/api/logout")
//                .logoutSuccessHandler(ajaxLogoutSuccessHandler)
//                .deleteCookies("JSESSIONID")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/api/authentication").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .csrf()
//                .disable()
//                .headers()
//                .frameOptions()
//                .disable()
//    }
//}
