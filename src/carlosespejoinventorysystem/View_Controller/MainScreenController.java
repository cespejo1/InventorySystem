/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carlosespejoinventorysystem.View_Controller;

import carlosespejoinventorysystem.Model.InHousePart;
import carlosespejoinventorysystem.Model.Part;
import carlosespejoinventorysystem.Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.activation.DataSource;


/**
 * FXML Controller class
 *
 * @author carlosespejo
 */
public class MainScreenController implements Initializable {
    
    Stage stage;
    Parent root;
    Scene scene;
    AnchorPane pane ;
    Part selectedItem;
    Product selectedProduct;
    
    @FXML
    private Button searchButton1;
    
    @FXML
    private TextField searchField;
    
    @FXML
    private TextField searchProductField;

    @FXML
    private Button addButton1;

    @FXML
    private Button modifyButton1;

    @FXML
    private Button deleteButton1;

    @FXML
    private Button searchButton2;

    @FXML
    private Button addButton2;

    @FXML
    private Button modifyButton2;

    @FXML
    private Button deleteButton2;

    @FXML
    private Button exitButton;
     
    @FXML
    private TableView<Part> tableView1;
    
    @FXML
    private TableView<Product> tableView2;
    
    @FXML // fx:id="partIDColumn"
    private TableColumn<Part, Integer> partIDColumn;

    @FXML // fx:id="partNameColumn"
    private TableColumn<Part, String> partNameColumn;

    @FXML // fx:id="invLevelColumn"
    private TableColumn<Part, Integer> invLevelColumn;
    
    @FXML // fx:id="priceColumn"
    private TableColumn<Part, Double> priceColumn;
    
    @FXML // fx:id="partIDColumn"
    private TableColumn<Product, Integer> productIDColumn;

    @FXML // fx:id="partNameColumn"
    private TableColumn<Product, String> productNameColumn;

    @FXML // fx:id="invLevelColumn"
    private TableColumn<Product, Integer> productInvLevelColumn;
    
    @FXML // fx:id="priceColumn"
    private TableColumn<Product, Double> productPriceColumn;
    
    private ObservableList<Part> tableData = FXCollections.observableArrayList();
    
    private ObservableList<Part> searchTableData = FXCollections.observableArrayList();
    
    private ObservableList<Product> productsTableData = FXCollections.observableArrayList();
    
    private ObservableList<Product> searchProductTableData = FXCollections.observableArrayList();
    
    private PropertyValueFactory<Part, Integer> partIDProperty;
      
    private PropertyValueFactory<Part, String> partNameProperty;
      
    private PropertyValueFactory<Part, Integer> invLevelProperty;
      
    private PropertyValueFactory<Part, Double> priceProperty;
    
    private PropertyValueFactory<Product, Integer> productIDProperty;
      
    private PropertyValueFactory<Product, String> productNameProperty;
      
    private PropertyValueFactory<Product, Integer> productInvLevelProperty;
      
    private PropertyValueFactory<Product, Double> productPriceProperty;
   
    
    @FXML 
    private void searchButtonAction(ActionEvent event) {
        
        if(searchField.getText().isEmpty()){
            tableView1.setItems( tableData );
        }
        else{
            searchTableData.clear();
            for (Part mySearchValue : tableData) {

                if(mySearchValue.getPartId() == Integer.parseInt(searchField.getText())){
                  
                    searchTableData.add(mySearchValue);
                    tableView1.setItems( searchTableData );
                   
                    }
            
                }
            
            }
        
    }
    
        @FXML 
    private void searchButton2Action(ActionEvent event) {
        
        if(searchProductField.getText().isEmpty()){
            tableView2.setItems( productsTableData );
        }
        else{
            searchProductTableData.clear();
            for (Product mySearchValue : productsTableData) {

                if(mySearchValue.getProductID() == Integer.parseInt(searchProductField.getText())){
                  
                    searchProductTableData.add(mySearchValue);
                    tableView2.setItems( searchProductTableData );
                   
                    }
            
                }
            
            }
        
    }
    
    
    @FXML 
    private void addButtonAction(ActionEvent event) throws IOException {
        
        if (event.getSource() == addButton1){
  
            
            //get reference to the button's stage
            stage = (Stage) addButton1.getScene().getWindow();
            
            //load up OTHER FXML document
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPart.fxml"));
            
            pane = loader.load();
            AddPartController inController = loader.getController();
           
            inController.setData(tableData);
       
            
        }// end of if
        
        else if  (event.getSource() == addButton2){
            
            //get reference to the button's stage
            stage = (Stage) addButton2.getScene().getWindow();
            
            //load up OTHER FXML document
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Product Screen.fxml"));
            
            pane = loader.load();
            ProductScreenController ProductController = loader.getController();
            ProductController.setControllerData(tableData, productsTableData);
            
            
            
        }// end of else
        
      //create a new scene with root and set the stage
      scene = new Scene(pane);
      
      stage.setScene(scene);
      
      stage.show();
        
    }
    
    
    
    @FXML 
    private void modifyButtonAction(ActionEvent event) throws IOException {
        
        if (tableView1.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Information Dialog");
         alert.setHeaderText("Need selection");
         alert.setContentText("Please select a part to modify");
         alert.showAndWait();
        }
        else{
        
            selectedItem = tableView1.getSelectionModel().getSelectedItem();


            //get reference to the button's stage
              stage = (Stage) modifyButton1.getScene().getWindow();
            //load up OTHER FXML document
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyPart.fxml"));

                pane = loader.load();
                ModifyPartController mController = loader.getController();
                mController.setFieldsToSelectedItem(selectedItem);
                mController.setObservableList(tableData);
                scene = new Scene(pane);

          stage.setScene(scene);

          stage.show();
      
        }
    }
    
    @FXML 
    private void modifyButton2Action(ActionEvent event) throws IOException {
        if (tableView2.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Information Dialog");
         alert.setHeaderText("Need selection");
         alert.setContentText("Please select a product to modify");
         alert.showAndWait();
        }
        else{
        
            selectedProduct = tableView2.getSelectionModel().getSelectedItem();


            //get reference to the button's stage
              stage = (Stage) modifyButton2.getScene().getWindow();
            //load up OTHER FXML document
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyProduct.fxml"));

                pane = loader.load();
                ModifyProductController mProductController = loader.getController();
                mProductController.setControllerData(tableData, productsTableData, selectedProduct);          
                scene = new Scene(pane);

          stage.setScene(scene);

          stage.show();
        }
    }
    
    @FXML 
    private void deleteButtonAction(ActionEvent event) {
        
        //search for the array element that matches selected item
        for(int x = 0; x < tableData.size(); x++){
            
            if(tableData.get(x) == tableView1.getSelectionModel().getSelectedItem()){
                
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Part to be deleted");
                alert.setContentText("Are you sure you want to delete this Part?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    
                    //delete item from list
                    tableData.remove(x);
                }    
                
            }
        }
    }
    
    @FXML 
    private void deleteButton2Action(ActionEvent event) {
        //search for the array element that matches selected item
        
        for(int x = 0; x < productsTableData.size(); x++){
            
            if(productsTableData.get(x) == tableView2.getSelectionModel().getSelectedItem()){
                
                //confirm with user
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Product to be deleted");
                alert.setContentText("This Product has parts associated with it. "
                        + "Are you sure you want to delete it?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    
                    //delete item from list
                    productsTableData.remove(x);
                }    
               
                    
                
            }
        }
    }
    
    @FXML 
    private void exitButtonAction(ActionEvent event) {
        
                //confirm with user
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Are you sure you want to exit?");
                alert.setContentText("Your data will not be saved");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    
                    //exit
                    stage = (Stage) addButton1.getScene().getWindow();
                    stage.close();
                } 
        
    }
    
    public ObservableList<Part> getObservableList(){
        return tableData;
    }
    
    private void setObservableList(ObservableList<Part> partList){
         this.tableData = partList;
     }
    
    private void setProductObservableList(ObservableList<Product> productList){
         this.productsTableData = productList;
     }
    
    public void setBothTables(ObservableList<Part> partList, ObservableList<Product> productsTableData){
        
        //set partTable
        this.setObservableList(partList);
        partIDProperty = new PropertyValueFactory<Part, Integer>("partId");
        partNameProperty = new PropertyValueFactory<Part, String>("name");
        invLevelProperty = new PropertyValueFactory<Part, Integer>("instock");
        priceProperty = new PropertyValueFactory<Part, Double>("price");
        
        partIDColumn.setCellValueFactory( partIDProperty );
        partNameColumn.setCellValueFactory( partNameProperty );
        invLevelColumn.setCellValueFactory( invLevelProperty );
        priceColumn.setCellValueFactory( priceProperty );
      
        //setting up the table Part TableView data source
        tableView1.setItems( tableData );
      
        //set productTable
        this.setProductObservableList(productsTableData);
        productIDProperty = new PropertyValueFactory<Product, Integer>("ProductID");
        productNameProperty = new PropertyValueFactory<Product, String>("name");
        productInvLevelProperty = new PropertyValueFactory<Product, Integer>("inStock");
        productPriceProperty = new PropertyValueFactory<Product, Double>("price");
        
        productIDColumn.setCellValueFactory( productIDProperty );
        productNameColumn.setCellValueFactory( productNameProperty );
        productInvLevelColumn.setCellValueFactory( productInvLevelProperty );
        productPriceColumn.setCellValueFactory( productPriceProperty );
        
        //setting up the table Product TableView data source
        tableView2.setItems( productsTableData );
    
     }
    
    /**
     * Sets the tableView with data that is passed in
     * @param partList 
     */
    public void setPartTable(ObservableList<Part> partList){
        
        //set partTable
        this.setObservableList(partList);
        partIDProperty = new PropertyValueFactory<Part, Integer>("partId");
        partNameProperty = new PropertyValueFactory<Part, String>("name");
        invLevelProperty = new PropertyValueFactory<Part, Integer>("instock");
        priceProperty = new PropertyValueFactory<Part, Double>("price");
        partIDColumn.setCellValueFactory( partIDProperty );
        partNameColumn.setCellValueFactory( partNameProperty );
        invLevelColumn.setCellValueFactory( invLevelProperty );
        priceColumn.setCellValueFactory( priceProperty );
      
      //setting up the table Part TableView data source
      tableView1.setItems( tableData );
    
     }
    
    
    
    

    /**
     * Initializes the controller class.
     */
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
      
    }    
    
    
    
}
