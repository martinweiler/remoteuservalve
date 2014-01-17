package com.redhat.valves.tomcat7;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;
import org.apache.catalina.realm.GenericPrincipal;

public class RemoteUserValve extends ValveBase {
    private static final Logger logger = Logger.getLogger(RemoteUserValve.class.getName());
    private static final String NOOP = "noop";

    public void invoke(Request request, Response response) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = request.getRequest();
        final List<String> roles = new ArrayList<String>();
        String authUser = httpServletRequest.getHeader("HTTP_AUTH_USER");
        final Principal principal = new GenericPrincipal(authUser, NOOP, roles);
        request.setUserPrincipal(principal);
        getNext().invoke(request, response);
    }    
}
