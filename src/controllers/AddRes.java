package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

public class AddRes {

	@FXML
	private TextField r_fname;
	@FXML
	private TextField r_lname;
	@FXML
	private TextField r_user;
	@FXML
	private PasswordField r_pass;
	@FXML
	private Button register;
	@FXML
	private Button suser;
	@FXML
	private Button adhome;
	@FXML
	private Button logout;
	@FXML
	private Button wardv;
	@FXML
	private Button rusers;
	@FXML
	private Label lblUser;
	@FXML
	private ChoiceBox<String> r_gender;

	public static List<String> resarc = new ArrayList<String>();

	ObservableList<String> genderlist= FXCollections.observableArrayList("MALE","FEMALE");
	@FXML
	private void initialize(){
		r_gender.setValue("MALE");
		r_gender.setItems(genderlist);
	}

	@FXML
	void register(MouseEvent event) throws SQLException, IOException {
		String username, password, fname, lname, type, gender;
		password = "N/A";
		username = "N/A";
		fname = r_fname.getText();
		lname = r_lname.getText();
		type = "RESIDENT";
		gender = (String) r_gender.getValue();
		String AName = LandingController.loggedInUsers.get(LandingController.loggedInUsers.size()-1);
		String fullname = fname + " " + lname;
		java.util.Date date = new Date();
		Object time = new java.sql.Timestamp(date.getTime());    
		String type2 = "Register";

		if(!username.isEmpty() &!password.isEmpty()&!fname.isEmpty()&!lname.isEmpty()&!gender.isEmpty()) {
			Connection connection= SQLite.dbConnector();
			Statement statement = connection.createStatement();

			int status = statement.executeUpdate("INSERT INTO users (FName,LName,username,password,type,gender) VALUES ( '"+ fname +"','"+ lname +"','"+ username +"','"+ password +"','" + type +"','"+ gender +"')");
			int status2 = statement.executeUpdate("INSERT INTO overall (FName,LName,gender) VALUES ( '"+ fname +"','"+ lname +"','"+ gender +"')");
			int status3 = statement.executeUpdate("INSERT INTO residents (SName,RName,type,time) VALUES ( '"+ AName +"','"+ fullname +"','"+ type2 +"','"+ time +"')");
			if (status==1 & status2==1 & status3==1) {
				Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath + "/Admin.fxml"));
				Node node = (Node) event.getSource();
				Stage stage = (Stage) node.getScene().getWindow();
				stage.setScene(new Scene(root));
				Alert alert =new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Resident Registration");
				alert.setHeaderText(null);
				alert.setContentText("Resident has been Registered Succesfuly!");
				alert.showAndWait();
				stage.setTitle("Admin Home Page");

			}
		}
		else{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Resident Registration");
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
	void rusers(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/RUsers.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));

	}
}




