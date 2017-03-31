package com.retail.web;

import com.retail.model.ItemCategory;
import com.retail.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class ItemCategoryController {

    @Autowired
    private CategoryService itemCategoryService;

    @RequestMapping(value = "/categories", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemCategory>> showCategories(){

        List<ItemCategory> categories = this.itemCategoryService.findAll();

        if(categories.isEmpty()){
            return new ResponseEntity<List<ItemCategory>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ItemCategory>>(categories, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<ItemCategory> getItemCategoryById(@PathVariable int id){
        return new ResponseEntity<ItemCategory>(itemCategoryService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemCategory> addItemCategory(@RequestBody ItemCategory itemCategory){
        return new ResponseEntity<ItemCategory>(itemCategoryService.save(itemCategory),HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemCategory> updateItemCategory(@RequestBody ItemCategory itemCategory){
        return new ResponseEntity<ItemCategory>(itemCategoryService.update(itemCategory), HttpStatus.OK);
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemCategory> removeItemCategory(@PathVariable("id") int id){
        return new ResponseEntity<ItemCategory>(itemCategoryService.delete(id), HttpStatus.OK);
    }
}
