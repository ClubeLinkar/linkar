//package br.com.clubelinkar.support.dummy.security;
//
//import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author Lennon Jesus
// */
//@RestController
//public class LoginRestController {
//
//    private static final Logger logger = LoggerFactory.getLogger(LoginRestController.class);
//
//    private static final String USERNAME = "username";
//    public static final String AUTHORIZATIONS = "permissions";
//    public static final String USER_DETAILS = "userDetails";
//
////    @Resource
////    IScaService scaService;
//
//    @RequestMapping(value = "/authenticate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public HttpEntity<Map> isAuthenticated(HttpServletRequest request) {
//        logger.debug("=== Entrando no método: isAuthenticated ===");
//
//        Map<String, Object> result = new HashMap<String, Object>();
//        String remoteUser = request.getRemoteUser();
//        if (remoteUser == null) return new ResponseEntity(HttpStatus.UNAUTHORIZED);
//
//        result.put(USERNAME, remoteUser);
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        logger.debug("=== toString da variável auth:" + ReflectionToStringBuilder.toString(auth));
//
//        Map<String, Boolean> authorizations = new HashMap();
//        for (GrantedAuthority grantedAuthority : auth.getAuthorities()) {
//            authorizations.put(grantedAuthority.getAuthority(), Boolean.TRUE);
//        }
//
//        result.put(AUTHORIZATIONS, authorizations);
//
//        logger.debug("=== toString da variável authorizations: " + ReflectionToStringBuilder.toString(auth));
////        ScaUserDetail scaSessao = (ScaUserDetail) auth.getPrincipal();
//
////        Map<String, Object> userDetails = new HashMap();
////        userDetails.put("nome", scaSessao.getNomeUsuario());
////        userDetails.put("login", scaSessao.getLoginUsuario());
////        userDetails.put("perfil", scaSessao.getNomePerfil());
////        userDetails.put("orgao", scaSessao.getNomeOrgaoUsuario());
////
////        result.put(USER_DETAILS, userDetails);
//
//        logger.debug("=== Saindo do método: isAuthenticated ===");
//        return new HttpEntity(result);
//    }
//
//    @RequestMapping(value = "/menu", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public HttpEntity<Map<String, Boolean>> getFuncionalidadesMenu() {
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        Map<String, Boolean> result = new HashMap();
//        for (GrantedAuthority grantedAuthority : auth.getAuthorities()) {
//            result.put(grantedAuthority.getAuthority(), Boolean.TRUE);
//        }
//
//        return new HttpEntity(result);
//    }
//
//    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public HttpEntity<Map<String, Object>> getUserDetails() {
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////        SCASessao scaSessao = (SCASessao) auth.getPrincipal();
//
////        Map<String, Object> userDetails = new HashMap();
////        userDetails.put("nome", scaSessao.getNomeUsuario());
////        userDetails.put("login", scaSessao.getLoginUsuario());
////        userDetails.put("perfil", scaSessao.getNomePerfil());
////        userDetails.put("orgao", scaSessao.getNomeOrgaoUsuario());
//
////        return new HttpEntity(userDetails);
//        return null;
//
//    }
//
//}
