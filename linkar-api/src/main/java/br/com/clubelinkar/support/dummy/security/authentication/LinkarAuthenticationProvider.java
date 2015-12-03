//package br.com.clubelinkar.support.dummy.security.authentication;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Service;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author Lennon Jesus
// */
//@Service
//public class LinkarAuthenticationProvider implements AuthenticationProvider {
//
//    private final Log logger = LogFactory.getLog(this.getClass());
//
////    @Value("${sca.nome.aplicacao}")
////    private String scaNomeAplicacao;
//
//    public static final String ROLE = "ROLE_";
//
////    @Resource
////    private IScaService scaService;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        logger.debug("=== Entrando no método: authenticate ===");
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        HttpServletRequest request = servletRequestAttributes.getRequest();
//        String remoteAddr = request.getRemoteAddr();
//        String jSessionId = servletRequestAttributes.getSessionId();
//
//        try {
////            SCASessao scaSessao = scaService.autentica(jSessionId, remoteAddr, username, password, scaNomeAplicacao);
////            logger.debug("=== toString do objeto scaSessao: " + ReflectionToStringBuilder.toString(scaSessao));
//
//            List<GrantedAuthority> grantedAuths = new ArrayList<>();
////            for (String funcionalidade : scaSessao.getFuncionalidades()) {
////                grantedAuths.add(new SimpleGrantedAuthority(ROLE + funcionalidade));
////            }
//
////            Authentication auth = new UsernamePasswordAuthenticationToken(scaSessao, null, grantedAuths);
////            logger.debug("=== toString do objeto auth: " + ReflectionToStringBuilder.toString(auth));
//
//            logger.debug("=== Saindo do método: authenticate ===");
////            return auth;
//            return null;
//        } catch (Exception e) {
//            throw new AuthenticationServiceException("Erro fatal ao tentar autenticar!", e);
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
