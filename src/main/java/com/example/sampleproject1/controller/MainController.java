package com.example.sampleproject1.controller;

import com.example.sampleproject1.model.SampleMember;
import com.example.sampleproject1.service.SampleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity saveUser(@RequestBody SampleMember sampleMember) {

        int resultInt = sampleUserService.joinUser(sampleMember);
        if (resultInt == 1) {
            return new  ResponseEntity(HttpStatus.OK);
        } else if (resultInt == -1) {
            // 리소스의 현재 상태와 충돌
            return new  ResponseEntity(HttpStatus.CONFLICT);
        } else {
            // 그외 다양한 서버에러 발생시 500
            return new  ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
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

    @DeleteMapping(value = "delete-all")
    @ResponseBody
    public ResponseEntity deleteAllDB(){

        sampleUserService.deleteAllUser();
        return new ResponseEntity(HttpStatus.OK);
    }

}
