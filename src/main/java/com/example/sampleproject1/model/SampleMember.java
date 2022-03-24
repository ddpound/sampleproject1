package com.example.sampleproject1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SampleMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    private Timestamp createDate;


    // ----getter , setter----

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
