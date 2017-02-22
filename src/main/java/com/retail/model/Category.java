package com.retail.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by DELL on 2017/01/31.
 */
@Entity
@Proxy(lazy = false)
/*@Table(name = "CATEGORY")*/
public class Category implements Serializable{

    @Id
    /*@Column(name="ID")*/
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /*@Column(name = "CATEGORY_NAME")*/
    private String categoryName;

    public Category(){}
    public Category(Builder builder)
    {
        this.id = builder.id;
        this.categoryName = builder.categoryName;
    }

    public int getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public static class Builder{
        private int id;
        private String categoryName;

        public Builder id(int id){
            this.id = id;
            return this;
        }
        public Builder categoryName(String categoryName){
            this.categoryName = categoryName;
            return this;
        }
        public Builder copy(Category category){
            this.id = category.id;
            this.categoryName = category.categoryName;
            return this;
        }
        public Category build(){
            return new Category(this);
        }
    }
}
