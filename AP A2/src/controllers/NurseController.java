package controllers;

import java.io.IOException;

import Application.Constants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NurseController {
	
	 @FXML
	    private Button roster;
	    @FXML
	    private Button resident;
	    @FXML
	    private Button beds;
	    @FXML
		private Label lblUser;
	    @FXML
	    private Button logout;

	    
		
		 @FXML
		    void roster(MouseEvent event) throws IOException {
		        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/NRoster.fxml"));
		        Node node = (Node) event.getSource();
		        Stage stage = (Stage) node.getScene().getWindow();
		        stage.setScene(new Scene(root));

		    }

		    @FXML
		    void resident(MouseEvent event) throws IOException {
		        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Resident.fxml"));
		        Node node = (Node) event.getSource();
		        Stage stage = (Stage) node.getScene().getWindow();
		        stage.setScene(new Scene(root));

		    }
		    @FXML
		    void logout(MouseEvent event) throws IOException {
		        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Landing.fxml"));
		        Node node = (Node) event.getSource();
		        Stage stage = (Stage) node.getScene().getWindow();
		        stage.setScene(new Scene(root));

		    }
		    @FXML
		    void beds(MouseEvent event) throws IOException {
		        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Beds.fxml"));
		        Node node = (Node) event.getSource();
		        Stage stage = (Stage) node.getScene().getWindow();
		        stage.setScene(new Scene(root));
		        stage.setTitle("Bed Control");
		    }

		    @FXML
		    void signup(MouseEvent event) throws IOException {
		        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath + "/RegisterResident.fxml"));

		        Node node = (Node) event.getSource();

		        Stage stage = (Stage) node.getScene().getWindow();

		        stage.setScene(new Scene(root));
		        stage.setTitle("Register Resident");

		    }

}
