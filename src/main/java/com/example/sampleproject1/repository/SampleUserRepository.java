package com.example.sampleproject1.repository;

import com.example.sampleproject1.model.SampleMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SampleUserRepository extends JpaRepository<SampleMember, Integer> {

    SampleMember findByName(String name);

}
