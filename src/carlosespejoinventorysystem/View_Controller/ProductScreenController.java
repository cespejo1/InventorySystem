/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carlosespejoinventorysystem.View_Controller;

import carlosespejoinventorysystem.Model.InHousePart;
import carlosespejoinventorysystem.Model.OutsourcedPart;
import carlosespejoinventorysystem.Model.Part;
import carlosespejoinventorysystem.Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author carlosespejo
 */
public class ProductScreenController implements Initializable {

    Product newProduct;
    Part partToBeAdded;
    Stage stage;
    AnchorPane pane;
    Scene scene;
    Part selectedItem;

    @FXML
    private TextField productIDField;

    @FXML
    private TextField productNameField;

    @FXML
    private TextField productInvField;

    @FXML
    private TextField productMaxField;

    @FXML
    private TextField productMinField;

    @FXML
    private TextField productPriceField;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;
    
    @FXML
    private Button searchButton;
    
    @FXML
    private TextField searchPartField;
    
    private ObservableList<Part> partsTableData = FXCollections.observableArrayList();
    
    private ObservableList<Product> productsTableData = FXCollections.observableArrayList();
    
    private ObservableList<Part> searchTableData = FXCollections.observableArrayList();
    
    private ObservableList<Part> associatedTableData = FXCollections.observableArrayList();
    
    private ArrayList<Part> partsAssociatedWithProduct = new ArrayList<>();
    
    private PropertyValueFactory<Part, Integer> partIDProperty;
      
    private PropertyValueFactory<Part, String> partNameProperty;
      
    private PropertyValueFactory<Part, Integer> invLevelProperty;
      
    private PropertyValueFactory<Part, Double> priceProperty;
    
    private PropertyValueFactory<Part, Integer> partIDProperty2;
      
    private PropertyValueFactory<Part, String> partNameProperty2;
      
    private PropertyValueFactory<Part, Integer> invLevelProperty2;
      
    private PropertyValueFactory<Part, Double> priceProperty2;
    
    @FXML
    private TableView<Part> tableView1;
    
    @FXML
    private TableView<Part> tableView2;
    
    @FXML // fx:id="partIDColumn"
    private TableColumn<Part, Integer> partIDColumn;

    @FXML // fx:id="partNameColumn"
    private TableColumn<Part, String> partNameColumn;

    @FXML // fx:id="invLevelColumn"
    private TableColumn<Part, Integer> invLevelColumn;
    
    @FXML // fx:id="priceColumn"
    private TableColumn<Part, Double> priceColumn;
    
    @FXML // fx:id="partIDColumn"
    private TableColumn<Part, Integer> partIDColumn2;

    @FXML // fx:id="partNameColumn"
    private TableColumn<Part, String> partNameColumn2;

    @FXML // fx:id="invLevelColumn"
    private TableColumn<Part, Integer> invLevelColumn2;
    
    @FXML // fx:id="priceColumn"
    private TableColumn<Part, Double> priceColumn2;

    @FXML
    private void addButtonAction(ActionEvent event) {
        
        
        selectedItem = tableView1.getSelectionModel().getSelectedItem();
        if(selectedItem instanceof Part){
            partsAssociatedWithProduct.add(selectedItem);
            associatedTableData.add(selectedItem);

            partIDProperty2 = new PropertyValueFactory<Part, Integer>("partId");
            partNameProperty2 = new PropertyValueFactory<Part, String>("name");
            invLevelProperty2 = new PropertyValueFactory<Part, Integer>("instock");
            priceProperty2 = new PropertyValueFactory<Part, Double>("price");
            partIDColumn2.setCellValueFactory( partIDProperty );
            partNameColumn2.setCellValueFactory( partNameProperty );
            invLevelColumn2.setCellValueFactory( invLevelProperty );
            priceColumn2.setCellValueFactory( priceProperty );

            //setting up the table data source
            tableView2.setItems( associatedTableData );
        }
        
    }
    
    @FXML
    private void deleteButtonAction(ActionEvent event) {
        
        selectedItem = tableView2.getSelectionModel().getSelectedItem();
        partsAssociatedWithProduct.add(selectedItem);
        associatedTableData.remove(selectedItem);
        
        
        
    }
    
    @FXML
    private void saveButtonAction(ActionEvent event) throws IOException {
        
        boolean allFieldsAreFilled = checkIfTextFieldsAreFilledCorrectly();
        
       
        if(allFieldsAreFilled){
        
            newProduct = new Product(partsAssociatedWithProduct, Integer.parseInt(productIDField.getText()), 
                    productNameField.getText(), Integer.parseInt(productInvField.getText()),
                        Double.parseDouble(productPriceField.getText()), Integer.parseInt(productMaxField.getText()),
                            Integer.parseInt(productMinField.getText()));

            //add to observable list
            productsTableData.add(newProduct);


            //get reference to the button's stage
            stage = (Stage) saveButton.getScene().getWindow();

            callMainScreen();
        }// end of if
        
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
    private void searchButtonAction(ActionEvent event) {
        System.out.println(event.getSource());
        if(searchPartField.getText().isEmpty()){
            tableView1.setItems( partsTableData );
        }
        else{
            searchTableData.clear();
            for (Part mySearchValue : partsTableData) {

                if(mySearchValue.getPartId() == Integer.parseInt(searchPartField.getText())){
                  
                    searchTableData.add(mySearchValue);
                    tableView1.setItems( searchTableData );
                   
                    }
            
                }
            
            }
        
    }
    
    
    
    public void setControllerData(ObservableList<Part> partsList, 
                                    ObservableList<Product> productList ){
        
        productsTableData = productList;
         productIDField.setText(String.valueOf(productsTableData.size() + 1));
        
        if(!partsList.isEmpty()){
        
        partsTableData = partsList;
        partIDProperty = new PropertyValueFactory<Part, Integer>("partId");
        partNameProperty = new PropertyValueFactory<Part, String>("name");
        invLevelProperty = new PropertyValueFactory<Part, Integer>("instock");
        priceProperty = new PropertyValueFactory<Part, Double>("price");
        partIDColumn.setCellValueFactory( partIDProperty );
        partNameColumn.setCellValueFactory( partNameProperty );
        invLevelColumn.setCellValueFactory( invLevelProperty );
        priceColumn.setCellValueFactory( priceProperty );
      
      //setting up the table data source
        tableView1.setItems( partsTableData );
        }   
        
    }
    
    private void callMainScreen() throws IOException{
         //Load mainScreen and pass reference of Part List
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        
        pane = loader.load();
        
        MainScreenController mainController = loader.getController();
        
        //set the Table View on mainscreen by passing list to it
        mainController.setBothTables(partsTableData, productsTableData);
        
        scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
     }
    
    private boolean checkIfTextFieldsAreFilledCorrectly(){
         
         if (productIDField.getText().isEmpty()){
             showAlert(productIDField);
             return false;
         }
         
         if (productNameField.getText().isEmpty()){
             showAlert(productNameField);
             return false;
         }
         
         if (productInvField.getText().isEmpty()){
             showAlert(productInvField);
             return false;
         }
         
         if (productPriceField.getText().isEmpty()){
             showAlert(productPriceField);
             return false;
         }
         
         if (productMaxField.getText().isEmpty()){
             showAlert(productMaxField);
             return false;
         }
         
         if (productMinField.getText().isEmpty()){
             showAlert(productMinField);
             return false;
         }
         
         
         if(Integer.parseInt(productMaxField.getText()) < Integer.parseInt(productMinField.getText())){
            showAlert(productMaxField);
            return false;
         }
         
         if(partsAssociatedWithProduct.size() < 1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("an input needs to be corrected before continuing ");      
            alert.setContentText("All products must have parts added to it");
            alert.showAndWait();
             return false;
         }
         
         if(Double.parseDouble(productPriceField.getText()) < getTotalOfAllPartsPrices()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Price invalid");      
            alert.setContentText("Product price must be higher than total of all parts associated with it."
                    + " Parts total is: " + getTotalOfAllPartsPrices());
            alert.showAndWait();
             return false;
         }
         
         if(Integer.parseInt(productMaxField.getText()) < Integer.parseInt(productInvField.getText())){
            showAlert(productInvField);
            return false;
         }
         
         if(Integer.parseInt(productInvField.getText()) < Integer.parseInt(productMinField.getText())){
            showAlert(productInvField);
            return false;
         }
         
           
             return true;
         
        
     }
     
     private void showAlert(TextField textField){
         
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Information Dialog");
         alert.setHeaderText("an input needs to be corrected before continuing ");
         
         if(textField.getId().equals("productIDField")){
             alert.setContentText("Product ID is needed");
         }
         
         if(textField.getId().equals("productNameField")){
             alert.setContentText("Product name is needed");
         }
         
         if(textField.getId().equals("productInvField")){
             alert.setContentText("Inventory amount is needed");
         }
         
         if(textField.getId().equals("productPriceField")){
             alert.setContentText("Price is needed");
         }
         
         if(textField.getId().equals("productMaxField" ) && 
                 textField.getText().isEmpty()){
             
             alert.setContentText("Maximum amount of inventory amount is needed");
         }
         
         if(textField.getId().equals("productMinField")){
             alert.setContentText("Minimum amount of inventory amount is needed");
         }
         
         
         if(textField.getId().equals("productMaxField" ) && 
                 !textField.getText().isEmpty() && !productMinField.getText().isEmpty() 
                    && Integer.parseInt(productMaxField.getText()) 
                        < Integer.parseInt(productMinField.getText())){
            alert.setContentText("Max input needs to be higher than min input");
         
         }
         
         if(textField.getId().equals("productInvField" ) && 
                 !textField.getText().isEmpty() && !productMaxField.getText().isEmpty() 
                    && Integer.parseInt(productMaxField.getText()) 
                        < Integer.parseInt(productInvField.getText())){
            alert.setContentText("Inventory needs to be less than the max amount");
         
         }
         
         if(textField.getId().equals("productInvField" ) && 
                 !textField.getText().isEmpty() && !productMinField.getText().isEmpty() 
                    && Integer.parseInt(productInvField.getText()) 
                        < Integer.parseInt(productMinField.getText())){
            alert.setContentText("Inventory needs to be greater than minimum amount");
         
         }
         
         alert.showAndWait();
         
     }
     
     public double getTotalOfAllPartsPrices(){
         double total = 0;
         for(int x = 0; x < partsAssociatedWithProduct.size(); x++){
             total = total + partsAssociatedWithProduct.get(x).getPrice();
         }
         return total;
     }
    



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
