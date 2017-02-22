package com.retail.controller;

import com.retail.model.Category;
import com.retail.repository.CategoryRepository;
import com.retail.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model){

        Category category = (Category) this.categoryService.findById(4);


        model.addAttribute("message", "What is this axacly Spring MVC Java Config Example");
        model.addAttribute("categoryName", category.getCategoryName() + "");
        model.addAttribute("id", category.getId());
        return "index";
    }

}
