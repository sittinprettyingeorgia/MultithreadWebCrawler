package com.example.springmultithread.models.designElements;

import com.example.springmultithread.models.User;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="DesignPattern")
public class DesignPattern extends DesignElement {

    protected DesignPattern (){}

    public DesignPattern(String name, String description, User user){
        this.name = name;
        this.description = description;
        this.user = user;
    }
}
