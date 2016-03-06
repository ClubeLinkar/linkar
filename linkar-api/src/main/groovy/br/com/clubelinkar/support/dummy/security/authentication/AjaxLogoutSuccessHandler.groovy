//package br.com.clubelinkar.support.dummy.security.authentication
//
//import org.springframework.security.core.Authentication
//import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
//import org.springframework.stereotype.Component
//
//import javax.servlet.ServletException
//import javax.servlet.http.HttpServletRequest
//import javax.servlet.http.HttpServletResponse
//import java.io.IOException
//
///**
// * Spring Security logout handler, specialized for Ajax requests.
// *
// * @author Lennon Jesus
// */
//@Component
//public class AjaxLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler
//        implements LogoutSuccessHandler {
//
////    @Resource
////    private IScaService scaService
//
//    @Override
//    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//
//        String jSessionId = request.getSession().getId()
//
//        if (authentication != null && authentication.getDetails() != null) {
//            try {
////                scaService.logout(jSessionId)
//                request.getSession().invalidate()
////                SCAThreadedAssets.close()
//                response.setStatus(HttpServletResponse.SC_OK)
//            } catch (Exception e) {
//                logger.error("Erro ao tentar efetuar o logout no SCA", e)
//                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
//            }
//        }
//    }
//
//}
