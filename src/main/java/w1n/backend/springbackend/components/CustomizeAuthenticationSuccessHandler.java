package w1n.backend.springbackend.components;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {




    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        //set our response to OK status
        response.setStatus(HttpServletResponse.SC_OK);


        // In questo metodo decidiamo dove indirizzare gli utenti in base al tipo utente

        System.out.println("LOGGATO OK");

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("ADMIN".equals(auth.getAuthority())) {
                response.sendRedirect("/dashboard");
            }
            if ("USER".equals(auth.getAuthority())) {
                response.sendRedirect("/dashboarduser");
            }
            if ("DATORE".equals(auth.getAuthority())) {
                response.sendRedirect("/dashboarddatore");
            }
        }
    }

}