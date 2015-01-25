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
        String authUser = httpServletRequest.getHeader(AUTH_HEADER);
        if(authUser!=null) {
           // For testing purposes only - can be removed after proper functionality has been verified
           System.out.println("RemoteUserValve - creating principal for user: " + authUser);
           final List<String> roles = new ArrayList<String>();
           final Principal principal = new GenericPrincipal(authUser, NOOP, roles);
           request.setUserPrincipal(principal);
        } 
        // For testing purposes only - can be removed after proper functionality has been verified
        else {
           System.out.println("RemoteUserValve - no " + AUTH_HEADER + " header present in this request, skipping pre-authentication");
        }
        getNext().invoke(request, response);
    }
}
