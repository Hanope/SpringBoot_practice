package com.example.demo;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class AppCtxRefreshEvent implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        User admin = new User();
        admin.setUserName("admin");
        userRepository.save(admin);

        User user1 = new User();
        user1.setUserName("tiger");
        userRepository.save(user1);
    }
}