package com.sitech.controller;

import com.sitech.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RestTemplateController {

    private static final Logger log = LoggerFactory.getLogger(RestTemplateController.class);

    @Autowired
    private RestTemplate restTemplate;

    /**
     * RestTemplate的用法
     * GET请求：
     * （1）getForEntity函数的使用，该方法返回ResponseEntity，该对象是Spring对HTTP请求响应的封装，
     * 其中主要存储了HTTP的几个重要元素，如HTTP的请求状态码的枚举对象HttpStatus(也就是我们常说的404、500这些错误码)，
     * 在它的父类HttpEntity中还存储着HTTP请求的头信息对象HttpHeaders以及泛型类型的请求体对象。
     * （2）getForObject函数的使用，该方法可理解为getForEntity的进一步封装，它通过HttpMessageConverterExtractor
     * 对Http的请求响应体body的转换，实现请求直接返回包装好的对象内容
     * POST请求：
     * （1）postForEntity函数的使用，该方法同getForEntity类似，会在调用后返回ResponseEntity<T>对象，其中T为请求响应的
     * body类型。
     * （2）postForObject函数的使用，与getForObject类似，简化postForEntity的后期处理
     * (3)postForLocation函数的使用，以POST方式请求提交资源，并返回新资源的URI
     * PUT请求：
     * put函数实现
     * DELETE请求：
     * delete函数实现
     */
    /**
     * getForEntity函数的使用
     * @return
     */
    @GetMapping("/useGetForEntity")
    public void useGetForEntity(){
        //public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... uriVariables)方法使用
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://hello-service/helloByName?name={1}",String.class,"Zhangsan");
        log.info(responseEntity.getBody());
        //public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables)方法使用
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","didi");
        ResponseEntity<String> responseEntity1 = restTemplate.getForEntity("http://hello-service/helloByMap?name={name}",String.class,map);
        log.info(responseEntity1.getBody());
        //public <T> ResponseEntity<T> getForEntity(URI url, Class<T> responseType) 方法使用
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://hello-service/helloByName?name={name}").build().expand("dodo").encode();
        URI uri = uriComponents.toUri();
        ResponseEntity<String> responseEntity2 = restTemplate.getForEntity(uri,String.class);
        log.info(responseEntity2.getBody());

    }
    /**
     * getForObject函数的使用
     * @return
     */
    @GetMapping("/useGetForObject")
    public void useGetForObject(){
        log.info("*************************************");
        //public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) 方法使用
        String userName= restTemplate.getForObject("http://hello-service/helloByName?name={1}",String.class,"Zhangsan");
        log.info(userName);
        //public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables)方法使用
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","didi");
        String userNameByMap = restTemplate.getForObject("http://hello-service/helloByMap?name={name}",String.class,map);
        log.info(userNameByMap);
        //public <T> T getForObject(URI url, Class<T> responseType) 方法使用
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://hello-service/helloByName?name={name}").build().expand("dodo").encode();
        URI uri = uriComponents.toUri();
        String userNameByUri = restTemplate.getForObject(uri,String.class);
        log.info(userNameByUri);

    }

    /**
     * postForEntity函数的使用
     * 若request对象的类型为一个普通对象，restTemplate会将该请求转换为一个HttpEntity对象来处理，其中Object为请求的类型，request内容被视为完整的body处理
     * 若request对象是HTTPEntity对象，视为完整的HTTP请求对象来处理，这个request中不仅包含了body的内容，还包含了header的内容
     * @return
     */
    @GetMapping("/usePostForEntity")
    public void usePostForEntity(){
        log.info("*************************************1");
        //public <T> ResponseEntity<T> postForEntity(String url, Object request, Class<T> responseType, Object... uriVariables)方法使用
        User user = new User("Zhangsan",15);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://hello-service/helloByUser",user,String.class);
        log.info(responseEntity.getBody());
        User user1 = new User("qqq",15);
        ResponseEntity<String> responseEntity4 = restTemplate.postForEntity("http://hello-service/{1}",user1,String.class,"helloByUser");
        log.info(responseEntity4.getBody());
        //public <T> ResponseEntity<T> postForEntity(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables)方法使用
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("uri","helloByUser");
        User user2 = new User("didi",15);
        ResponseEntity<String> responseEntity1 = restTemplate.postForEntity("http://hello-service/{uri}",user2,String.class,map);
        log.info(responseEntity1.getBody());
        //public <T> ResponseEntity<T> postForEntity(URI url, Object request, Class<T> responseType)方法使用
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://hello-service/{1}").build().expand("helloByUser").encode();
        URI uri = uriComponents.toUri();
        User user3 = new User("dodo",15);
        ResponseEntity<String> responseEntity2 = restTemplate.postForEntity(uri,user3,String.class);
        log.info(responseEntity2.getBody());
    }

    /**
     * postForObject函数的使用
     * @return
     */
    @GetMapping("/usePostForObject")
    public void usePostForObject(){
        log.info("*************************************2");
        User user = new User("Zhangsan",15);
        //public <T> T postForObject(String url, Object request, Class<T> responseType, Object... uriVariables)方法使用
        String userName= restTemplate.postForObject("http://hello-service/{1}",user,String.class,"helloByUser");
        log.info(userName);
        //public <T> T postForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables)方法使用
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("uri","helloByUser");
        String userNameByMap = restTemplate.postForObject("http://hello-service/{uri}",user,String.class,map);
        log.info(userNameByMap);
        //public <T> T postForObject(URI url, Object request, Class<T> responseType) 方法使用
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://hello-service/{1}").build().expand("helloByUser").encode();
        URI uri = uriComponents.toUri();
        String userNameByUri = restTemplate.postForObject(uri,user,String.class);
        log.info(userNameByUri);

    }

    /**
     * postForLocation          使用该方式返回null,具体原因尚未找到
     * @return
     */
    @GetMapping("/usePostForLocation")
    public void usePostForLocation(){
        log.info("*************************************3");
        User user = new User("didi",15);
        //public URI postForLocation(String url, Object request, Object... uriVariables)方法使用
        URI  uri1= restTemplate.postForLocation("http://hello-service/helloByUser",user);
        log.info(uri1 == null ? "" : uri1.toString());
        //public <T> T postForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables)方法使用
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("uri","helloByUser");
        URI  uri2 = restTemplate.postForLocation("http://hello-service/{uri}",user,map);
        log.info(uri2 == null ? "" : uri1.toString());
        //public <T> T postForObject(URI url, Object request, Class<T> responseType) 方法使用
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://hello-service/{1}").build().expand("helloByUser").encode();
        URI uri = uriComponents.toUri();
        URI  uri3 = restTemplate.postForLocation(uri,user);
        log.info(uri3 == null ? "" : uri1.toString());

    }
} 