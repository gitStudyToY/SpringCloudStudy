package com.sitech.command;

import com.netflix.hystrix.HystrixObservableCommand;
import com.sitech.entity.User;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

public class UserObservableCommand extends HystrixObservableCommand<User> {

    private RestTemplate restTemplate;

    private Long id;

    public UserObservableCommand(Setter setter, RestTemplate restTemplate, Long id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected Observable<User> construct() {
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
     * 服务降级
     * @return
     */
    @Override
    protected Observable<User> resumeWithFallback() {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {

            }
        });
    }
}