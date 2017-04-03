package com.retail.model;

import java.io.Serializable;

public class UserRole implements Serializable{

    public UserRole() {
    }

    private int id;
    private int userId;
    private String role;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    public UserRole(Builder builder){
        this.id = builder.id;
        this.userId = builder.userId;
        this.role = builder.role;
    }

    public static class Builder{

        private int id;
        private int userId;
        private String role;

        public Builder id(int id){
            this.id = id;
            return this;
        }

        public Builder userId(int userId){
            this.userId = userId;
            return this;
        }

        public Builder role(String role){
            this.role = role;
            return this;
        }
        public  Builder updater(UserRole userRole){
            this.id = userRole.id;
            this.userId = userRole.userId;
            this.role = userRole.role;
            return this;
        }
        public UserRole build(){
            return new UserRole(this);
        }
    }
}
