package com.codespacelab.zuul;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;

@Component
public class ZuulAuthFilter extends ZuulFilter {



    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
       RequestContext ctx = RequestContext.getCurrentContext();

       String authHeader = "Basic admin:admin";
       ctx.addZuulRequestHeader(HttpHeaders.AUTHORIZATION, authHeader);
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
