package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class LandingController implements Initializable{
	@FXML
	private Label lblUser;

	@FXML
	private Label lblWarning;
	
	 @FXML
	private TableView<java.Resident> CustomersTableView;

	 @FXML
	private TableColumn<java.Resident, Integer> CustomerIDColumn;

	 @FXML
	private TableColumn<java.Resident, String> CustomerNameColumn;
	 
	 @FXML
	TextField searchCustomerTextBox;

	@FXML
	private Button Doctor;

	@FXML
	private Button Nurse;

	@FXML
	private Button Exit;

	@FXML
	private Button Manager;

	@FXML
	private Button ID;
	
	@FXML
	private Button Name;

	
	public void btnEnd_button() {
	}
  
	@FXML
	public void login(ActionEvent event) {
		Button but = (Button) event.getSource();
        Stage stage = new Stage();
        Stage curStage = (Stage) but.getScene().getWindow();
        Scene scene = new Scene(new LoginController(curStage, but.getText()));
        System.out.println(scene.toString());
        stage.setScene(scene);
        stage.setTitle("Login to account");
        stage.show();
	        }
	@FXML
	void displayCustomers(ActionEvent event) {
			
		    }

	@FXML
	void searchCustomers(ActionEvent event) {

	       
	    }
	void newScene(String path, Node nd, String title) throws IOException {
        Stage stage = (Stage) nd.getScene().getWindow();
        stage.setTitle(title);
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}


}
