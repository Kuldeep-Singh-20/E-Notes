package com.enote.controller;

import com.enote.entity.User;
import com.enote.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    
    @GetMapping("/home")
    public String index(){
        return "index";
    }

    @GetMapping("/signin")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }


    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute  User user , HttpSession  session){

//        System.out.println(user);
     boolean mail =  userService.existEmailCheck(user.getEmail());

     //Return True
     if(mail){
         session.setAttribute("msg", "User Already Exist");

     //False
     }else {

         User saveUser1 =  userService.saveUser(user);

         if(saveUser1 != null){
             session.setAttribute("msg", "User Registered successfully");
         }
         else{
             session.setAttribute("msg", "Some Thing Went Wrong");
         }

     }

      return "redirect:/register";

    }





}
