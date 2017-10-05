/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carlosespejoinventorysystem.Model;

import java.util.ArrayList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author carlosespejo
 */
public class Product {
    
    
    //Declare Variables
    private ArrayList<Part> partsList;
    private SimpleStringProperty name;
    private SimpleIntegerProperty productID;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty inStock; 
    private SimpleIntegerProperty min;
    private SimpleIntegerProperty max;
    private Boolean partIDFound;


 

    //constructor
    public Product(ArrayList<Part> partsToBeAdded, int productID, String name,  
            int inStock, Double price, int max, int min) {
        
        this.partsList = partsToBeAdded;
        this.productID = new SimpleIntegerProperty(productID);
        this.name = new SimpleStringProperty(name);
        this.inStock = new SimpleIntegerProperty(inStock);
        this.price = new SimpleDoubleProperty(price);
        this.max = new SimpleIntegerProperty(max);
        this.min = new SimpleIntegerProperty(min);
        partIDFound = false;
        
    }
    
    public ArrayList<Part> getPartsList() {
        return partsList;
    }

    public void setPartsList(ArrayList<Part> partsList) {
        this.partsList = partsList;
    }
    

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getProductID() {
        return productID.get();
    }

    public void setProductID(int partId) {
        this.productID.set(partId);
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getInStock() {
        return inStock.get();
    }

    public void setInStock(int instock) {
        this.inStock.set(instock);
    }

    public int getMin() {
        return min.get();
    }

    public void setMin(int min) {
        this.min.set(min);
    }

    public int getMax() {
        return max.get();
    }

    public void setMax(int max) {
        this.max.set(max);
    }
    
    public void addPart(Part partToBeAdded){
        this.partsList.add(partToBeAdded);
    }
    
    public Boolean removePart(int partIdToBeRemoved){
        
        int searchResultIndex = searchPart(partIdToBeRemoved);
        
        //if part was found in search, remove it from list and return true
        if (searchResultIndex > -1){
        this.partsList.remove(searchPart(partIdToBeRemoved));
        return true;
        }
        //else return false
        else {
            return false;
        }
    }
    
    public Part lookupPart(int partID){
        
        int searchResultIndex = searchPart(partID);
        return this.partsList.get(searchResultIndex);
        
    }
    
    public void updatePart(int partID){
        //not sure what the point of this class is
        int searchResultIndex = searchPart(partID);
    }
    
    public int searchPart(int partIDToSearch){
      
        
        for( int partListIndex = 0; partListIndex < this.partsList.size() && !this.partIDFound; partListIndex++){
            
            if (this.partsList.get(partListIndex).getPartId() == partIDToSearch){
                
             return partListIndex;
                
                
            }
        }
        
        return -1;
    } // end of searchMethod

    
  
}
