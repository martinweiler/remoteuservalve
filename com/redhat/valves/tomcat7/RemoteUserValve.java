package com.redhat.valves.tomcat7;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;
import org.apache.catalina.realm.GenericPrincipal;

public class RemoteUserValve extends ValveBase {
    private static final String NOOP = "noop";
    private static final String AUTH_HEADER = "HTTP_AUTH_USER";
    public void invoke(Request request, Response response) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = request.getRequest();
        final List<String> roles = new ArrayList<String>();
        String authUser = httpServletRequest.getHeader(AUTH_HEADER);
        final Principal principal = new GenericPrincipal(authUser, NOOP, roles);
        request.setUserPrincipal(principal);
        getNext().invoke(request, response);
    }
}
