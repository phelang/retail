package com.retail.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "ITEM_CATEGORY")
@Proxy(lazy = false)
public class ItemCategory implements Serializable{

    public ItemCategory(){}

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    public ItemCategory(Builder builder)
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

    @Override
    public String toString() {
        return String.format("Id %d \n Category Name %s \n", id, categoryName);
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
        public Builder updater(ItemCategory itemCategory){
            this.id = itemCategory.id;
            this.categoryName = itemCategory.categoryName;
            return this;
        }
        public ItemCategory build(){
            return new ItemCategory(this);
        }
    }
}