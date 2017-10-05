/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carlosespejoinventorysystem.Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author carlosespejo
 */
public class OutsourcedPart extends Part {
    
 //Declare variables
 SimpleStringProperty companyName;
 
 public OutsourcedPart(int partID, String partName, int inventoryLevel, double price,
                    int max, int min, String companyName){
        //set attributes
        this.partId = new SimpleIntegerProperty(partID);
        this.name = new SimpleStringProperty(partName);
        this.instock = new SimpleIntegerProperty(inventoryLevel);
        this.price = new SimpleDoubleProperty(price);
        this.max = new SimpleIntegerProperty(max);
        this.min = new SimpleIntegerProperty(min);
        this.companyName = new SimpleStringProperty(companyName);
        
        
        
    }
 
 /**
  * returns the company name
  * @return  companyName
  */
public String getCompanyName(){
     
     return this.companyName.get();
 }

/**
 * sets the company name to a string
 * @param companyName 
 */
public void setCompanyName(String companyName){
    
    this.companyName.set(companyName);
    
}

}
