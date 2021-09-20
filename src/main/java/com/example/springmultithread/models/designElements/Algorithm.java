package com.example.springmultithread.models.designElements;

import com.example.springmultithread.models.User;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Algorithm")
public class Algorithm extends DesignElement {

    protected Algorithm (){}

    public Algorithm (String name, String description, User user){
        this.name = name;
        this.description = description;
        this.user = user;
    }
}
