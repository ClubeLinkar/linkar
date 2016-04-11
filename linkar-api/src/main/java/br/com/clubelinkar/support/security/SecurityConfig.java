//package br.com.clubelinkar.support.security;
//
//import br.com.clubelinkar.api.user.User;
//import br.com.clubelinkar.api.user.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
///**
// * @author Lennon Jesus
// */
//@Configuration
//class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public void init(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService());
//    }
//
//    @Bean
//    UserDetailsService userDetailsService() {
//        return email -> {
//            User user = userRepository.findByEmail(email);
//            if (user != null) {
//                return new org.springframework.security.core.userdetails.User(
//                        user.getEmail(),
//                        user.getPassword(),
//                        true, true, true, true,
//                        AuthorityUtils.createAuthorityList("USER")
//                );
//            } else {
//                throw new UsernameNotFoundException("could not find the user '" + email + "'");
//            }
//        };
//    }
//}
//
//@EnableWebSecurity
//@Configuration
//class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .anyRequest()
//                        .permitAll()
////                .fullyAuthenticated()
//                .and()
//                .httpBasic()
////                .and()
////                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/api/logout")).logoutSuccessUrl("/")
////                .addLogoutHandler(new LogoutHandler() {
////                    @Override
////                    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
////                        request.getSession().invalidate();
////                    }
////                })
////                .logout()
////                .logoutUrl("/api/logout")
////                .deleteCookies("JSESSIONID")
////                .clearAuthentication(true)
////                .invalidateHttpSession(true)
////                .logoutSuccessUrl("/api/store")
////                .permitAll()
//                .and()
//                .csrf().disable();
//    }
//
//}