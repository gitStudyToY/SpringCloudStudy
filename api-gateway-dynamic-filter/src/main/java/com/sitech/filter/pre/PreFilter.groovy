package com.sitech.filter.pre

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.servlet.http.HttpServletRequest

class PreFilter extends ZuulFilter{
    Logger log = LoggerFactory.getLogger(PreFilter.class)

    String filterType(){
        return "pre"
    }

    @Override
    int filterOrder() {

        return 1000

    }

    @Override
    boolean shouldFilter() {
        return true
    }

    @Override
    Object run() {

        RequestContext ctx = RequestContext.getCurrentContext()

        HttpServletRequest request = ctx.getRequest()

        log.info("send {} request to {}",request.getMethod(),request.getRequestURL().toString())

        return null

    }
}