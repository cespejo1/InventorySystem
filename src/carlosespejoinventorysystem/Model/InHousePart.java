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
public class InHousePart extends Part{
    
    //delcare variables
    SimpleIntegerProperty machineID;
    
    /**
     * Set machineID variable
     * @param machineID 
     */
    public void setMachineID(int machineID) {
        this.machineID.set(machineID);
    }
    
    /**
     * Get machineID Integer
     * @return machineID
     */
    public int getMachineID(){
        return machineID.get();
    }
    
    public InHousePart(int partID, String partName, int inventoryLevel, double price,
                    int max, int min, int machineID){
        //set attributes
        this.partId = new SimpleIntegerProperty(partID);
        this.name = new SimpleStringProperty(partName);
        this.instock = new SimpleIntegerProperty(inventoryLevel);
        this.price = new SimpleDoubleProperty(price);
        this.max = new SimpleIntegerProperty(max);
        this.min = new SimpleIntegerProperty(min);
        this.machineID = new SimpleIntegerProperty(machineID);
        
        
        
    }
    
}
