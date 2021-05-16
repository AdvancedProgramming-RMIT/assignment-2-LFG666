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

public class WardViewController {
	 @FXML
	    private Button showuser;
	    @FXML
	    private Button adduser;
	    @FXML
	    private Button logout;
	    @FXML
	    private Button wardadmin;
	    @FXML
		private Label lblUser;
	    @FXML
	    private Button admin;

	    
		
	    	@FXML
		    void suser(MouseEvent event) throws IOException {
		        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/ShowUsers.fxml"));
		        Node node = (Node) event.getSource();
		        Stage stage = (Stage) node.getScene().getWindow();
		        stage.setScene(new Scene(root));
		        stage.setTitle("View Users");

		    }

		    @FXML
		    void adhome(MouseEvent event) throws IOException {
		        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Admin.fxml"));
		        Node node = (Node) event.getSource();
		        Stage stage = (Stage) node.getScene().getWindow();
		        stage.setScene(new Scene(root));
		        stage.setTitle("Admin Home Page");

		    }
		    @FXML
		    void logout(MouseEvent event) throws IOException {
		        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Landing.fxml"));
		        Node node = (Node) event.getSource();
		        Stage stage = (Stage) node.getScene().getWindow();
		        stage.setScene(new Scene(root));

		    }
		    @FXML
		    void wardv(MouseEvent event) throws IOException {
		        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/WardView.fxml"));
		        Node node = (Node) event.getSource();
		        Stage stage = (Stage) node.getScene().getWindow();
		        stage.setScene(new Scene(root));
		        stage.setTitle("Ward Control");

		    }

		    @FXML
		    void rusers(MouseEvent event) throws IOException {
		        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/RUsers.fxml"));
		        Node node = (Node) event.getSource();
		        Stage stage = (Stage) node.getScene().getWindow();
		        stage.setScene(new Scene(root));
		        stage.setTitle("Register User");

		    }
		    @FXML
		    void bedmenu(MouseEvent event) throws IOException {
		        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/ShowUsers.fxml"));
		        Node node = (Node) event.getSource();
		        Stage stage = (Stage) node.getScene().getWindow();
		        stage.setScene(new Scene(root));

		    }
	}


