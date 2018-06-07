package com.sitech.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.sitech.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.Future;
@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 通过@HystrixCommand注解实现Hystrix命令实现，同步执行
     * 通过fallbackMethod参数定义服务降级逻辑
     * ignoreExceptions参数定义不触发降级逻辑的异常
     * @HystrixCommand(groupKey = "groupkey",commandKey = "commandName",threadPoolKey = "threadPoolKey")
     * groupKey设置组名、commandKey设置命令名称、threadPoolKey设置线程池划分
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "defaultUser",ignoreExceptions = HystrixBadRequestException.class)
    public User getUserById(Long id) {
        return restTemplate.getForObject("http://hello-service/userById?id={1}", User.class, id);
    }

    /**
     * 通过@HystrixCommand注解实现Hystrix命令实现，异步执行
     * @param id
     * @return
     */
    @HystrixCommand
    public Future<User> getUserByIdAsync(final Long id) {
        return new AsyncResult<User>() {

            @Override
            public User invoke() {
                return restTemplate.getForObject("http://hello-service/userById?id={1}", User.class, id);
            }
        };
    }

    /**
     * 通过@HystrixCommand注解实现HystrixObservableCommand
     * 通过observableExecutionMode控制使用observe()还是toObservable()的执行方式
     * ObservableExecutionMode.EAGER表示使用observe执行方式，ObservableExecutionMode.LAZY表示使用toObservable()执行方式
     * @param id
     * @return
     */
    @HystrixCommand(observableExecutionMode = ObservableExecutionMode.EAGER)
    public Observable<User> getUserByIdObservable(final Long id) {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                try {

                    if (!subscriber.isUnsubscribed()) {
                        User user = restTemplate.getForObject("http://hello-service/userById/id={1}",User.class,id);
                        subscriber.onNext(user);
                        subscriber.onCompleted();
                    }
                }catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    /**
     * 在fallback实现方法的参数中增加Throwable e对象的定义，这样便可在方法的内部获取触发服务异常降级的异常内容了
     * @param id
     * @param e
     * @return
     */
    @HystrixCommand(fallbackMethod = "defaultUserSec")
    public User defaultUser(Long id , Throwable e){

        assert ("getUserById command failed").equals(e.getMessage());
        return new User("First Fallback");
    }

    public User defaultUserSec(){
        return new User("Second Fallback");
    }

    /**
     * 同步实现
     *
     * @param id
     * @return
     */
    //@HystrixCommand(ignoreExceptions = {HystrixBadRequestException.class})  -- 当该服务抛出HystrixBadRequestException异常时，Hystrix将其包装在HystrixBadRequestException中抛出，而不触发fallback逻辑
    @HystrixCommand
    @CacheResult(cacheKeyMethod = "getUserByIdCacheKey")   //由于已定义cacheKeyMethod，CacheKey不起效，两者可选其一，CacheKey优先级低
    public User getUserCacheById(@CacheKey("id") Long id) {

        System.out.println("*---------请求缓存测试---------------*");
        return restTemplate.getForObject("http://hello-service/userById?id={1}", User.class, id);
    }

    /**
     * return type of cacheKey method must be String
     * @param id
     * @return
     */
    public String getUserByIdCacheKey (Long id){
        return String.valueOf(id);
    }

    /**
     * 缓存清理
     * @param user
     */
    @HystrixCommand
    @CacheRemove(commandKey = "getUserCacheById")  //commandKey必须指定，通过该属性找到对应的请求命令缓存位置
    public void update(@CacheKey("id") User user){
        restTemplate.postForObject("http://hello-service/helloByUser",user,String.class);
    }



}