package com.sitech.service;

import com.netflix.hystrix.HystrixObservableCommand;
import com.sitech.entity.User;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

public class UserObservableCommand extends HystrixObservableCommand<User> {

    private RestTemplate restTemplate;
    private Long id;

    private UserObservableCommand(RestTemplate restTemplate,Long id,Setter setter){
        super(setter);
        this.restTemplate= restTemplate;
        this.id=id;
    }

    @Override
    protected Observable<User> construct() {
        return Observable.create(new Observable.OnSubscribe<User>() {

            @Override
            public void call(Subscriber<? super User> subscriber) {
                try {

                    if (!subscriber.isUnsubscribed()) {
                        User user = restTemplate.getForObject("http://USER-SERVICE/user/{1}",User.class,id);
                        subscriber.onNext(user);
                        subscriber.onCompleted();
                    }

                }catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}