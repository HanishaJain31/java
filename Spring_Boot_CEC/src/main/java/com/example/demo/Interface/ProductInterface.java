package com.example.demo.Interface;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.ProductModel;

@Repository
public interface ProductInterface extends CrudRepository< ProductModel , Integer >{

}

