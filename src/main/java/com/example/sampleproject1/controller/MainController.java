package com.example.sampleproject1.controller;

import com.example.sampleproject1.model.SampleMember;
import com.example.sampleproject1.service.SampleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    SampleUserService sampleUserService;

    @GetMapping("/")
    public String index(){

        return "index";
    }

    @GetMapping("joinPage")
    public String joinPage(){
        return "join/joinPage";
    }

    @PostMapping("save")
    @ResponseBody
    public String saveUser(@RequestBody SampleMember sampleMember) {

        int resultInt = sampleUserService.joinUser(sampleMember);
        if (resultInt == 1) {
            return "{\"result\" : true}";
        } else if (resultInt == -1) {
            return "{\"result\" : false}";
        } else {
            return "{\"result\" : false}";
        }

    }

    @PostMapping("login")
    @ResponseBody
    public String login(@RequestBody SampleMember sampleMember,
                        HttpSession httpSession){
        System.out.println(sampleMember.getName());
        int resultInt = sampleUserService.loginUser(sampleMember);
        if (resultInt == 1) {
            httpSession.setAttribute("sessionLoginId", sampleMember.getName());
            return "{\"result\" : true}";
        } else if (resultInt == -1) {
            return "{\"result\" : false}";
        } else {
            return "{\"result\" : false}";
        }
    }

    @GetMapping("logout")
    public String logout( HttpSession httpSession){
        httpSession.removeAttribute("sessionLoginId");
        return "redirect:/";
    }

}
