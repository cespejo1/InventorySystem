/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carlosespejoinventorysystem.View_Controller;

import carlosespejoinventorysystem.Model.InHousePart;
import carlosespejoinventorysystem.Model.OutsourcedPart;
import carlosespejoinventorysystem.Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author carlosespejo
 */
public class AddPartController implements Initializable {
    
    Stage stage;
    Parent root;
    Scene scene;
    AnchorPane pane;
    
    Part newPart;
    
    
    @FXML
    private RadioButton inhouseToggle;

    @FXML
    private RadioButton outsourcedToggle;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField idField;

    @FXML
    private TextField partNameField;

    @FXML
    private TextField inv;

    @FXML
    private TextField priceField;

    @FXML
    private TextField maxField;

    @FXML
    private TextField minField;

    @FXML
    private TextField machineID;
    
    @FXML
    private TextField companyname;
    
    @FXML
    private Label machineID_CompanyNameLabel;
    
    private ObservableList<Part> partData;

    
    @FXML 
    private void saveButtonAction(ActionEvent event) throws IOException {
        
        boolean allFieldsAreFilled = checkIfTextFieldsAreFilledCorrectly();
        
       
        if(allFieldsAreFilled){
            
            //create a new part with the data from the fields
       
            //inhouse part
            if(inhouseToggle.isSelected()){
            newPart = new InHousePart(Integer.parseInt(idField.getText()), partNameField.getText(), Integer.parseInt(inv.getText()),
                Double.parseDouble(priceField.getText()), Integer.parseInt(maxField.getText()),
                    Integer.parseInt(minField.getText()), Integer.parseInt(machineID.getText())  );
            }
            //outsourced part
            else{
                newPart = new OutsourcedPart(Integer.parseInt(idField.getText()), partNameField.getText(), Integer.parseInt(inv.getText()),
                Double.parseDouble(priceField.getText()), Integer.parseInt(maxField.getText()),
                    Integer.parseInt(minField.getText()), companyname.getText()  );

            }
        
     
        
        //add to observable list
        partData.add(newPart);
        
        //get reference to the button's stage
        stage = (Stage) saveButton.getScene().getWindow();
                 
        callMainScreen();
        
        }
        
    }
    
    @FXML 
    private void cancelButtonAction(ActionEvent event) throws IOException {
         //confirm with user
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Are you sure you want to cancel?");
                alert.setContentText("You will lose any input you have entered");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    
                    //get reference to the button's stage
                    stage = (Stage) cancelButton.getScene().getWindow();

                    callMainScreen();
                }   
    }
    
    @FXML
    private void inhouseToggleAction(){
        
    machineID_CompanyNameLabel.setText("Machine ID");
    inhouseToggle.setSelected(true);
    machineID.setVisible(true);
    companyname.setVisible(false);
    outsourcedToggle.setSelected(false);
    
    
}
    
    @FXML
    private void outsourcedToggleAction(){
        
    machineID_CompanyNameLabel.setText("Company Name");
    outsourcedToggle.setSelected(true);
    companyname.setVisible(true);
    inhouseToggle.setSelected(false);
    machineID.setVisible(false);
    
    
}
    
    public ObservableList<Part> getObservableList(){
        return partData;
    }
     public void setData(ObservableList<Part> partList){
         
         this.partData = partList;
         //Autogenerate ID
         idField.setText(String.valueOf(partData.size() + 1));
         
     }
     
     private void callMainScreen() throws IOException{
         //Load mainScreen and pass reference of Part List
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        
        pane = loader.load();
        
        MainScreenController mainController = loader.getController();
        
        //set the Table View on mainscreen by passing list to it
        mainController.setPartTable(partData);
        
        scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
     }
     
     private boolean checkIfTextFieldsAreFilledCorrectly(){
         
         if (idField.getText().isEmpty()){
             showAlert(idField);
             return false;
         }
         
         if (partNameField.getText().isEmpty()){
             showAlert(partNameField);
             return false;
         }
         
         if (inv.getText().isEmpty()){
             showAlert(inv);
             return false;
         }
         
         if (priceField.getText().isEmpty()){
             showAlert(priceField);
             return false;
         }
         
         if (maxField.getText().isEmpty()){
             showAlert(maxField);
             return false;
         }
         
         if (minField.getText().isEmpty()){
             showAlert(minField);
             return false;
         }
         
         if (machineID.getText().isEmpty() && inhouseToggle.isSelected()){
             showAlert(machineID);
             return false;
         }
         
         if (companyname.getText().isEmpty() && outsourcedToggle.isSelected()){
             showAlert(companyname);
             return false;
         }
         
         if(Integer.parseInt(maxField.getText()) < Integer.parseInt(minField.getText())){
            showAlert(maxField);
            return false;
         }
         
         if(Integer.parseInt(maxField.getText()) < Integer.parseInt(inv.getText())){
            showAlert(inv);
            return false;
         }
         
         if(Integer.parseInt(inv.getText()) < Integer.parseInt(minField.getText())){
            showAlert(inv);
            return false;
         }
         
           
             return true;
         
        
     }
     
     private void showAlert(TextField textField){
         
         Alert alert = new Alert(AlertType.INFORMATION);
         alert.setTitle("Information Dialog");
         alert.setHeaderText("An input needs to be corrected before continuing ");
         
         if(textField.getId().equals("idField")){
             alert.setContentText("Part ID is needed");
         }
         
         if(textField.getId().equals("partNameField")){
             alert.setContentText("Part name is needed");
         }
         
         if(textField.getId().equals("inv")){
             alert.setContentText("Inventory amount is needed");
         }
         
         if(textField.getId().equals("priceField")){
             alert.setContentText("Price is needed");
         }
         
         if(textField.getId().equals("maxField" ) && 
                 textField.getText().isEmpty()){
             
             alert.setContentText("Maximum amount of inventory amount is needed");
         }
         
         if(textField.getId().equals("minField")){
             alert.setContentText("Minimum amount of inventory amount is needed");
         }
         
         if(textField.getId().equals("machineID")){
             alert.setContentText("Machine ID is needed");
            
         }
         
         if(textField.getId().equals("companyname")){
             alert.setContentText("Company name is needed");
         }
         
         if(textField.getId().equals("maxField" ) && 
                 !textField.getText().isEmpty() && !minField.getText().isEmpty() 
                    && Integer.parseInt(maxField.getText()) 
                        < Integer.parseInt(minField.getText())){
            alert.setContentText("Max input needs to be higher than min input");
         
         }
         
         if(textField.getId().equals("inv" ) && 
                 !textField.getText().isEmpty() && !maxField.getText().isEmpty() 
                    && Integer.parseInt(maxField.getText()) 
                        < Integer.parseInt(inv.getText())){
            alert.setContentText("Inventory needs to be less than the max amount");
         
         }
         
         if(textField.getId().equals("inv" ) && 
                 !textField.getText().isEmpty() && !minField.getText().isEmpty() 
                    && Integer.parseInt(inv.getText()) 
                        < Integer.parseInt(minField.getText())){
            alert.setContentText("Inventory needs to be greater than minimum amount");
         
         }
         
         alert.showAndWait();
         
     }
     
     
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Start screen with Inhouse toggle selected
        inhouseToggle.setSelected(true);
        companyname.setVisible(false);
        machineID_CompanyNameLabel.setText("Machine ID");
        
        
        
    }    
    
}
