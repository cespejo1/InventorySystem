/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carlosespejoinventorysystem.Model;

import java.util.ArrayList;

/**
 *
 * @author carlosespejo
 */
public class Inventory {
    
    
    //Initialize variables
    private ArrayList<Product> products;
    
    /**
     * Add product to Product ArrayList
     * @param product 
     */
    public void addProduct(Product product){
        this.products.add(product);
    }
    
    /**
     * Removes the product from product Array
     * @param id 
     */
    public boolean removeProduct(int id){
        this.products.remove(id);
        
        return true; //needs to change
    }
    
    
    /**
     * Looks up product within Product ArrayList 
     * by ID number
     * @param id 
     */
    public void lookupProduct(int id){
         // return a product
    }
    
    /**
     * Updates a product's attributes
     * @param id 
     */
    public void updateProduct(int id){
        //update product attributes
    }
}
