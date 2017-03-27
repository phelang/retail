package com.retail.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name= "ITEM")
@Proxy(lazy = false)
public class Item {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="NAME")
    private String itemName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private BigDecimal price;

    /* FOREIGN KEY */
    @Column(name = "ITEM_CATEGORY_ID")
    private int item_category_id;

    public int getId() {
        return id;
    }

    public int getItemCategoryId() {
        return item_category_id;
    }

    public Item(){}

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Id %d \n Iten Name %s \n Description %s \n", id, itemName, description);
    }

    public Item(Builder builder)
    {
        this.id = builder.id;
        this.item_category_id = builder.item_category_id;
        this.itemName = builder.itemName;
        this.description = builder.description;
        this.price = builder.price;
    }

    public static class Builder{
        private int id;
        private int item_category_id;
        private String itemName;
        private String description;
        private BigDecimal price;

        public Builder id(int id){
            this.id = id;
            return this;
        }

        public Builder item_category_id(int item_category_id){
            this.item_category_id = item_category_id;
            return this;
        }

        public Builder itemName(String itemName){
            this.itemName = itemName;
            return this;
        }

        public Builder description(String description){
            this.description = description;
            return this;
        }

        public Builder price(BigDecimal price){
            this.price = price;
            return this;
        }

        public Builder copy(Item item){
            this.id = item.id;
            this.item_category_id = item.item_category_id;
            this.itemName= item.itemName;
            this.description = item.description;
            this.price = item.price;
            return this;
        }

        public Item build(){
            return new Item(this);
        }
    }
}