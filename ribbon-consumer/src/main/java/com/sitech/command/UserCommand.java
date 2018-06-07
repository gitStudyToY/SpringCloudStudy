package com.sitech.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixRequestCache;
import com.sitech.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 通过继承HystrixCommand来封装具体依赖服务的调用逻辑
 * 报错：UserCommand timed-out and no fallback available   需要设置超时时间hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds=10000
 *
 */
public class UserCommand extends HystrixCommand<String> {

    private RestTemplate restTemplate;

    public UserCommand(Setter setter, RestTemplate restTemplate) {
        super(setter);
        this.restTemplate = restTemplate;
    }


    @Override
    protected String run() throws Exception {

        return restTemplate.getForObject("http://hello-service/hello",String.class);
    }

    /**
     * 服务降级
     */
    @Override
    protected String getFallback() {
        return "";
    }

    /**
     * 开始请求缓存
     * @return
     */
    @Override
    protected String getCacheKey() {
        return "cacheKey";
    }


}