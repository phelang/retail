package com.retail.web;

import com.retail.model.ItemCategory;
import com.retail.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category/")
public class ItemCategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/categories/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemCategory>> showCategories(){

        List<ItemCategory> categories = this.categoryService.findAll();

        if(categories.isEmpty()){
            return new ResponseEntity<List<ItemCategory>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ItemCategory>>(categories, HttpStatus.OK);
    }

}
