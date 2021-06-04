package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import Application.Constants;
import databaseSQL.SQLite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RegUsersController {
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
	private Button addres;
	@FXML
	private TextField u_fname;
	@FXML
	private TextField u_lname;
	@FXML
	private TextField u_user;
	@FXML
	private PasswordField u_pass;
	@FXML
	private Button register;
	@FXML
	private ChoiceBox<String> u_gender;
	@FXML
	private ChoiceBox<String> u_type;

	ObservableList<String> typelist= FXCollections.observableArrayList("ADMIN","DOCTOR","NURSE");
	ObservableList<String> genderlist= FXCollections.observableArrayList("MALE","FEMALE"); 

	
	//used to register staff. requires all fields to be inputted before submission.
	@FXML
	void register(MouseEvent event) throws SQLException, IOException {
		String username, password, fname, lname, type, gender;
		password = u_pass.getText();
		username = u_user.getText();
		fname = u_fname.getText(); 
		lname = u_lname.getText();
		type = (String) u_type.getValue();
		gender = (String) u_gender.getValue();

		if(!username.isEmpty() &!password.isEmpty()&!fname.isEmpty()&!lname.isEmpty()&!type.isEmpty()&!gender.isEmpty()) {
			Connection connection = SQLite.dbConnector();
			Statement statement = connection.createStatement(); 

			int status = statement.executeUpdate("INSERT INTO users (FName,LName,username,password,type,gender" +
					") VALUES ( '" + fname + "','" + lname + "','" + username + "','" + password + "','" + u_type.getValue() + "','" + u_gender.getValue() + "')");
			if (status==1 ) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("User Registration");
				alert.setHeaderText(null);
				alert.setContentText("User have been Registered Succesfuly!");
				alert.showAndWait();
			}
		}
		else{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("User Registration");
			alert.setHeaderText(null);
			alert.setContentText("Fill All the Fields! :(");
			alert.showAndWait();
		}

	}	    

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
	void addres(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/AddRes.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));

	}
	@FXML
	public void initialize() {
		u_type.setValue("NURSE");
		u_type.setItems(typelist);
		u_gender.setValue("MALE");
		u_gender.setItems(genderlist);
	}

}


