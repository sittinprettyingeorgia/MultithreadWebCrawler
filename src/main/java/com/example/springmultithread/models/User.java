package com.example.springmultithread.models;
import com.example.springmultithread.helpers.Encryptor;
import com.example.springmultithread.models.designElements.Algorithm;
import com.example.springmultithread.models.designElements.DataStructure;
import com.example.springmultithread.models.designElements.DesignPattern;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * Parent class for all user objects that will be accessing any kind of AWS Database.
 * and is used to access AWS RDS Database and has oneToMany relationship with DesignElement class.
 */
@Entity
@Table(name="User")
public class User {

    @NotBlank(message ="username is mandatory")
    private String username;
    @Min(8)
    @Max(30)
    private String password;
    @Email(message="email must be valid")
    private String email;

    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(mappedBy="user")
    private Map<String, Algorithm> algorithms;
    @OneToMany(mappedBy="user")
    private Map<String, DesignPattern> designPatterns;
    @OneToMany(mappedBy="user")
    private Map<String, DataStructure> dataStructures;

    protected User(){}

    public User (String username, String password) throws NoSuchAlgorithmException {
        this.username = username;
        this.password = Encryptor.encrypt(password);//TODO sha512 encryption in constructor?
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    protected void setId(Long id){
        this.id = id;
    }

    public Map<String, Algorithm> getAlgorithms() {
        return algorithms;
    }

    public void setAlgorithms(Map<String, Algorithm> algorithms) {
        this.algorithms = algorithms;
    }

    public Map<String, DesignPattern> getDesignPatterns() {
        return designPatterns;
    }

    public void setDesignPatterns(Map<String, DesignPattern> designPatterns) {
        this.designPatterns = designPatterns;
    }

    public Map<String, DataStructure> getDataStructures() {
        return dataStructures;
    }

    public void setDataStructures(Map<String, DataStructure> dataStructures) {
        this.dataStructures = dataStructures;
    }
}
