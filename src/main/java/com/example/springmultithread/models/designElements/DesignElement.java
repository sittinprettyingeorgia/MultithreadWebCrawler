package com.example.springmultithread.models.designElements;

import com.example.springmultithread.models.Implementation;
import com.example.springmultithread.models.User;

import javax.persistence.*;
import java.util.Map;

@Entity
public abstract class DesignElement {

    @Id
    @GeneratedValue
    protected Long id;
    protected String name;
    protected String description;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    protected User user;
    @OneToMany(mappedBy="designElement")
    protected Map<String, Implementation> implementations;

    public Long getId() {
        return id;
    }

    protected void setId(Long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<String, Implementation> getImplementations() {
        return implementations;
    }

    public void setImplementations(Map<String, Implementation> implementations) {
        this.implementations = implementations;
    }
}
