package com.bootu.security.demo.controller;


import com.bootu.security.demo.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @PostMapping("/regist")
    public void regist(User user, HttpServletRequest request) {

        //不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
        String userId = user.getUsername();
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
//		appSingUpUtils.doPostSignUp(new ServletWebRequest(request), userId);
    }

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user){
        Date date = new Date();
        user.setId(date.getTime()+"");
        return user;
    }

    @GetMapping
    public List<User> getUsers(){
        List userList = new ArrayList();

        User user1 = new User();
        user1.setUsername("user1");
        User user2 = new User();
        user2.setUsername("user1");
        userList.add(user1);
        userList.add(user2);
        return userList;
    }
}
