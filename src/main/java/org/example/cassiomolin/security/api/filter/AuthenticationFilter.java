package org.example.cassiomolin.security.api.filter;


import org.example.cassiomolin.security.api.AuthenticatedUserDetails;
import org.example.cassiomolin.security.api.AuthenticationTokenDetails;
import org.example.cassiomolin.security.api.TokenBasedSecurityContext;
import org.example.cassiomolin.security.service.AuthenticationTokenService;
import org.example.cassiomolin.user.domain.User;
import org.example.cassiomolin.user.service.UserService;

import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * JWT authentication filter.
 *
 * @author cassiomolin
 */
@Provider
@Dependent
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Inject
    private UserService userService;

    @Inject
    private AuthenticationTokenService authenticationTokenService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String authenticationToken = authorizationHeader.substring(7);
            handleTokenBasedAuthentication(authenticationToken, requestContext);
            return;
        }

        // Other authentication schemes (such as Basic) could be supported
    }

    private void handleTokenBasedAuthentication(String authenticationToken, ContainerRequestContext requestContext) {

    	System.out.println("Recieved for authentication =====" + authenticationToken);
        AuthenticationTokenDetails authenticationTokenDetails = authenticationTokenService.parseToken(authenticationToken);
        User user = userService.findByUsernameOrEmail(authenticationTokenDetails.getUsername());
        AuthenticatedUserDetails authenticatedUserDetails = new AuthenticatedUserDetails(user.getUsername(), user.getAuthorities());

        boolean isSecure = requestContext.getSecurityContext().isSecure();
        SecurityContext securityContext = new TokenBasedSecurityContext(authenticatedUserDetails, authenticationTokenDetails, isSecure);
        System.out.println(securityContext.getUserPrincipal().getName());
       // System.out.println(securityContext.isUserInRole("ADMIN"));
        requestContext.setSecurityContext(securityContext);
    }
}