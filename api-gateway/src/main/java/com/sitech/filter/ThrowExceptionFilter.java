package com.sitech.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
public class ThrowExceptionFilter extends ZuulFilter{


    private static Logger log = LoggerFactory.getLogger(ThrowExceptionFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        log.info("This is a pre filter, It will throw a RuntimeException");

        RequestContext requestContext = RequestContext.getCurrentContext();

        /**
         * 利用try-catch使得过滤器能够抛出异常
         */
        try{
            doSomthing();
        }catch (Exception e) {
            requestContext.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            requestContext.set("error.exception",e);
        }

        return null;
    }

    private void doSomthing(){
        throw new RuntimeException("Exist some Error ...");
    }
}