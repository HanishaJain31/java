package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Interface.ProductInterface;
import com.example.demo.models.ProductModel;

@Controller
public class ProductController {

    @Autowired
    ProductInterface Pi;
    @GetMapping("Form/")
	public String addInfo()
	{		
		return "Form";
	}	
    @GetMapping("/add")
    public String InsertInfo(ProductModel Pm) {
        try {
            Pi.save(Pm);
            return "redirect:/display";
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; 
        }
    }

    @GetMapping("/display")
    public String displayInfo(Model model) {	
        List<ProductModel> dis = (List<ProductModel>) Pi.findAll();
        model.addAttribute("display", dis);
        return "display"; 
    }

    @GetMapping("/Delete/{id}")
    public String deletepublic (@PathVariable Integer id)
	{	
		Pi.deleteById(id);
		return "redirect:/display";
		
	}
    @GetMapping("/edit/{id}")
    public String editInfo(@PathVariable Integer id, Model model) {
        ProductModel demoData = Pi.findById(id).orElse(null);
        if (demoData == null) {
            model.addAttribute("error", "No record found with ID: " + id);
            return "error"; 
        }
        model.addAttribute("Product_Model", demoData);
        return "edit"; 
    }

    @PostMapping("/edit")
    public String updateInfo(ProductModel Pm) {
        try {
            ProductModel existingRecord = Pi.findById(Pm.getId()).orElse(null);
            if (existingRecord != null) {
                existingRecord.setName(Pm.getName());
                existingRecord.setCategory(Pm.getCategory());
                existingRecord.setPrice(Pm.getPrice());
                Pi.save(existingRecord);
            }
            return "redirect:/display"; 
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; 
        }
    }
}
