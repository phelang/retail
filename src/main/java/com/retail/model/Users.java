package com.retail.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "USERS")
@Proxy(lazy = false)
public class Users implements Serializable{

    public Users() {
    }

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("Id %d \n Users Name %s \n Password %s \n Email %s", id, username, password, email);
    }

    public Users(Builder builder){
        this.id = builder.id;
        this.username = builder.username;
        this.password = builder.password;
        this.email = builder.email;
    }

    public static class Builder{
        private int id;
        private String username;
        private String password;
        private String email;

        public Builder id(int id){
            this.id = id;
            return this;
        }

        public Builder username(String username){
            this.username = username;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public Builder updater(Users users){
            this.id = users.id;
            this.username = users.username;
            this.password = users.password;
            this.email = users.email;
            return this;
        }

        public Users build(){
            return new Users(this);
        }
    }
}
