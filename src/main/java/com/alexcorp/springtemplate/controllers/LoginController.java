package com.alexcorp.springtemplate.controllers;

import com.alexcorp.springtemplate.controllers.requestbodys.LoginUser;
import com.alexcorp.springtemplate.controllers.requestbodys.RegistrationUser;
import com.alexcorp.springtemplate.domains.User;
import com.alexcorp.springtemplate.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping(value = "/login")
    public @ResponseBody
    String loginProcessing(@RequestBody LoginUser user, HttpSession session) {
        User u = userRepository.findByEmail(user.email);
        if(u == null){
            return "{\"error\" : \"Account doesn't exist!\"}";
        }

        if(!passwordEncoder.matches(user.password, u.getPassword())){
            return "{\"error\" : \"Incorrect password!\"}";
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(u.getEmail(), u.getPassword(), u.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        session.setAttribute("nickname", u.getNickname());
        return "{\"error\" : \"\"}";
    }
}
