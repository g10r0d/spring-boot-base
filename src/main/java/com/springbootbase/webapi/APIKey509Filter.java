package com.springbootbase.webapi;

import java.security.cert.X509Certificate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.x509.X509AuthenticationFilter;

public class APIKey509Filter extends X509AuthenticationFilter {
	private String principalRequestHeader;

    public APIKey509Filter(String principalRequestHeader) 
    {
        this.principalRequestHeader = principalRequestHeader;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request)
    {
        return request.getHeader(principalRequestHeader);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request)
    {
        X509Certificate[] certs = (X509Certificate[]) request
                .getAttribute("javax.servlet.request.X509Certificate");

        if(certs != null && certs.length > 0)
        {
            return certs[0].getSubjectDN();
        }

        return super.getPreAuthenticatedCredentials(request);
    }
}
