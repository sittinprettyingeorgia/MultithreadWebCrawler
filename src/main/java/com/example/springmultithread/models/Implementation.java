package com.example.springmultithread.models;

import com.example.springmultithread.models.designElements.DesignElement;

import javax.persistence.*;

@Entity
@Table(name="Implementation")
public class Implementation {

    @Id
    @GeneratedValue
    private Long id;
    private String name;//name of algorithm/data structure/design pattern
    private String description;//in depth description of characteristics/uses/etc.
    private String implementation;//code implementation
    private String language;//code language
    @ManyToOne
    @JoinColumn(name="designElement_id", nullable=false)
    private DesignElement designElement;

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

    public String getImplementation() {
        return implementation;
    }

    public void setImplementation(String implementation) {
        this.implementation = implementation;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public DesignElement getDesignElement() {
        return designElement;
    }

    public void setDesignElement(DesignElement designElement) {
        this.designElement = designElement;
    }
}
