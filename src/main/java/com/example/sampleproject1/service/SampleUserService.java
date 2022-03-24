package com.example.sampleproject1.service;

import com.example.sampleproject1.model.SampleMember;
import com.example.sampleproject1.repository.SampleUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SampleUserService {

    @Autowired
    private SampleUserRepository sampleUserRepository;

    // 회원가입 인서트
    @Transactional
    public int joinUser(SampleMember sampleMember){
        if(sampleUserRepository.findByName(sampleMember.getName()) == null){
            sampleUserRepository.save(sampleMember);
            return 1;
        }
        return -1;// 중복아이디
    }

    @Transactional(readOnly = true)
    public SampleMember selectSampleUser(SampleMember sampleMember){

        return null;
    }

    // 로그인
    @Transactional
    public int loginUser(SampleMember sampleMember){
        SampleMember selectSampleMember =sampleUserRepository.findByName(sampleMember.getName());
        if(selectSampleMember != null ){
            if(selectSampleMember.getPassword().equals(sampleMember.getPassword())){
                return 1;
            }else {
                return -1;
            }

        }else{
            return -1;
        }

    }

    // 유저삭제
    @Transactional
    public int deleteUser(SampleMember sampleMember){

        sampleUserRepository.delete(sampleMember);
        return 1;
    }

    // 모든테이블 삭제
    @Transactional
    public int deleteAllUser(){
        sampleUserRepository.deleteAll();
        return 1;
    }



}
