package com.bootu.security.demo.controller;


import com.bootu.security.demo.dto.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import sun.plugin.liveconnect.SecurityContextHelper;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

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
