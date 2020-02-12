package com.alexcorp.springtemplate.controllers;

import com.alexcorp.springtemplate.controllers.requestbodys.RegistrationUser;
import com.alexcorp.springtemplate.domains.User;
import com.alexcorp.springtemplate.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.Set;

@Controller
public class RegistrationController {

    private final static String INVITE_CODE_ADMIN_ROLE = "hjkmflvsy";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping(value = "/registration")
    public @ResponseBody
    String registrationProcessing(@RequestBody RegistrationUser user) {
        if(user.email.equals("") || user.nickname.equals("") || user.password.equals("")){
            return "{\"error\" : \"Confirm All fields, please!\"}";
        }

        if(userRepository.findByEmail(user.email) != null){
            return "{\"error\" : \"Account already exists!\"}";
        }

        if(!user.password.equals(user.password2)){
            return "{\"error\" : \"Passwords don\\'t match!\"}";
        }

        Set<User.Role> roles = new HashSet<>();
        roles.add(User.Role.USER);

        if(user.invite.equals(INVITE_CODE_ADMIN_ROLE))roles.add(User.Role.ADMIN);

        User u = new User(user.email, passwordEncoder.encode(user.password), user.nickname, User.Active.ACTIVE, roles);
        userRepository.save(u);

        return "{\"error\" : \"\", \"email\" : \"" + user.email + "\"}";
    }
}
