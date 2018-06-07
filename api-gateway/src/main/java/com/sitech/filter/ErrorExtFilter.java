package com.sitech.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
public class ErrorExtFilter extends SendErrorFilter {

    private static final Logger log = LoggerFactory.getLogger(ErrorExtFilter.class);

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 30;
    }

    @Override
    public boolean shouldFilter() {

        /**
         * 判断仅处理来自post路由器引起的异常
         */
        RequestContext requestContext = new RequestContext();
        ZuulFilter zuulFilter = (ZuulFilter)requestContext.get("failed.filter");
        if (zuulFilter != null && zuulFilter.filterType().equals("post")){
            return true;
        }

        return false;
    }

} 