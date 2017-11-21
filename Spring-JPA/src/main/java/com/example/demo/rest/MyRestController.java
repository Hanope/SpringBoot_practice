package com.example.demo.rest;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyRestController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> userList() {
        return userRepository.findAllByOrderByUserNoAsc();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public List<User> addUser(@RequestParam(value = "name", required = false, defaultValue = "") String _sName) {
        User user = new User();
        user.setUserName(_sName);
        userRepository.save(user);
        return userRepository.findAllByOrderByUserNoAsc();
    }

    @RequestMapping(value="/findByUserName", method = RequestMethod.GET)
    public List<User> findUser(@RequestParam(value="userName", required=false, defaultValue="0") String _userName) {
        List<User> user = userRepository.findByUserName(_userName);
        return user;
    }

    @RequestMapping(value="/sqlfind", method = RequestMethod.GET)
    public List<User> sqlFindUser(@RequestParam(value="user_name", required=false, defaultValue="0") String _userName) {
        List<User> users = userRepository.sqlFindName(_userName);
        return users;
    }

    @RequestMapping(value="/jpqlfind", method = RequestMethod.GET)
    public List<User> jpqlFind(@RequestParam(value="user_name", required=false, defaultValue="0") String _userName) {
        List<User> users = userRepository.jpqlFindName(_userName);
        return users;
    }

    @RequestMapping(value="/jpqlfindex", method = RequestMethod.GET)
    public List<User> jpqlFindEx(@RequestParam(value="user_name", required=false, defaultValue="0") String _userName) {
        List<User> users = userRepository.jpqlFindNameEx(_userName);
        return users;
    }

    @RequestMapping(value="/jpqlfindex2", method = RequestMethod.GET)
    public List<User> jpqlFindEx2(@RequestParam(value="user_name", required=false, defaultValue="0") String _userName) {
        List<User> users = userRepository.jpqlFindNameEx2(_userName);
        return users;
    }

    @RequestMapping(value = "/bad", method = RequestMethod.GET)
    public List<User> bad() {

        try {
            User u1 = new User();
            u1.setUserName("Chris");

            User u2 = new User();
            u2.setUserName("Chris");

            userService.addUser(u1, u2);
        } catch (Exception _e) {
            _e.printStackTrace();
        }

        return userRepository.findAllByOrderByUserNoAsc();
    }
}
