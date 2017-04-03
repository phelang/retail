package com.retail.model;

import java.io.Serializable;

public class User implements Serializable{

    private int id;
    private String username;
    private String password;
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

    public User(Builder builder){
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

        public Builder copy(User user){
            this.id = user.id;
            this.username = user.username;
            this.password = user.password;
            this.email = user.email;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }
}
