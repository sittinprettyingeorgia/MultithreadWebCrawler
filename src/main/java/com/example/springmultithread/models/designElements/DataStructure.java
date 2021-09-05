package com.example.springmultithread.models.designElements;

import com.example.springmultithread.models.User;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="DataStructure")
public class DataStructure extends DesignElement {

    protected DataStructure (){}

    public DataStructure (String name, String description, User user){
        this.name = name;
        this.description = description;
        this.user = user;
    }
}
