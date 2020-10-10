package com.codespacelab.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.springframework.stereotype.Component;


@Component
public class ZuulAuthFilter extends ZuulFilter {


    @Override
    public boolean shouldFilter() {
        return true;
    }

     //This method does not do anything related to security actually
    @Override
    public Object run() {
       RequestContext ctx = RequestContext.getCurrentContext();
       String credentials = "admin" + ":" + "wrongPassword";
       byte[] credentialsByte = credentials.getBytes();
       byte[] base64CredsBytes = Base64.encodeBase64(credentialsByte);
       String authorizationHeader = "Basic " + new String(base64CredsBytes);
       ctx.addZuulRequestHeader(HttpHeaders.AUTHORIZATION, authorizationHeader);
        return null;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10;
    }
}
