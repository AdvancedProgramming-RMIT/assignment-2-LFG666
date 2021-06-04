package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Application.Constants;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Condition;
import databaseSQL.SQLite;
import model.Data;

public class Conditions {
	ObservableList<String> typelist= FXCollections.observableArrayList("Infectious_Mild", "Infectious_Serious", "Infectious_Terminal",
			"Safe_Mild", "Safe_Serious", "Safe_Terminal"); 
	@FXML
	private TextField cond_name;
	@FXML
	private TextField cond_code;
	@FXML
	private ChoiceBox<String> type;
	@FXML
	private Button logout;
	@FXML
	private Button patients;
	@FXML
	private Button doctor;
	@FXML
	private Button medicines;
	@FXML
	private Button shifts;
	@FXML
	private Button view;
	@FXML
	private Label lblUser;
	@FXML
	private Label lblUser2;
	@FXML
	private ChoiceBox<Data> resi;
	@FXML
	private ChoiceBox<Condition> cond;

	ObservableList<Condition> conditions= FXCollections.observableArrayList();
	ObservableList<Data> residents= FXCollections.observableArrayList();

	void getResidents() throws SQLException, IOException {

		try {
			Connection connection = SQLite.dbConnector();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from users where type = 'RESIDENT'");
			while (resultSet.next()) {
				residents.add(new Data(resultSet.getInt("id"), resultSet.getString("FName"), resultSet.getString("LName"),  resultSet.getString("type"), resultSet.getString("gender")));
			}

		}
		catch (SQLException e) {
			Alert alert =new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("User Login");
			alert.setHeaderText(null);
			alert.setContentText("Database issue :/");
			alert.showAndWait();
		}
		try {
			Connection connection = SQLite.dbConnector();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from conditions ");
			while (resultSet.next()) {
				conditions.add(new Condition(resultSet.getInt("id"), resultSet.getString("CName"), resultSet.getString("type")));
			}
		}
		catch (SQLException e) {
			Alert alert =new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("User Login");
			alert.setHeaderText(null);
			alert.setContentText("Database issue :/");
			alert.showAndWait();
		}

	}
	
	//Registers a new condition
	@FXML
	void register(MouseEvent event) throws SQLException, IOException {
		String condname, ctype;

		condname = cond_name.getText();
		ctype = (String) type.getValue();

		if(!condname.isEmpty() &!ctype.isEmpty()) {
			Connection connection = SQLite.dbConnector();
			Statement statement = connection.createStatement();

			int status = statement.executeUpdate("INSERT INTO conditions (CName,type" +
					") VALUES ( '" + condname + "','" +  type.getValue() + "')");

			if (status==1) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Register Condition");
				alert.setHeaderText(null);
				alert.setContentText("Condition have been Registered Succesfuly!");
				alert.showAndWait();
			}
		}
		else{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Register Condition");
			alert.setHeaderText(null);
			alert.setContentText("Fill All the Fields! :(");
			alert.showAndWait();
		}

	}	    
//connects a condition to a patient. Allows for staff to confirm to infectious illnesses
	@FXML
	void register2(MouseEvent event) throws SQLException, IOException {
		Data Fname;
		Data Lname;
		Data  gender;
		Condition type;
		Condition CName;
		Fname =  resi.getValue();
		Lname = resi.getValue();
		gender =  resi.getValue();
		CName =  cond.getValue();
		type = cond.getValue();



		Connection connection = SQLite.dbConnector();
		Statement statement = connection.createStatement(); 

		int status = statement.executeUpdate("INSERT INTO condres (FName,LName,gender,CName,type" +
				") VALUES ( '" + Fname.getFname() + "','" + Lname.getLname() + "','" + gender.getGender() + "','" + CName.getCname() + "','"  + type.getType() +"')");

		if (status==1) {  
			Alert alert = new Alert(Alert.AlertType.INFORMATION); 
			alert.setTitle("User Registration");
			alert.setHeaderText(null);
			alert.setContentText("User have been Registered Succesfuly!");
			alert.showAndWait();
		}

		else{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("User Registration");
			alert.setHeaderText(null);
			alert.setContentText("Fill All the Fields! :(");
			alert.showAndWait();
		}

		String insertString = "UPDATE overall SET CName = ?, type = ?  where FName = '"+Fname.getFname()+"'"; 

		try (PreparedStatement pst = connection.prepareStatement(insertString))

		{
			connection.setAutoCommit(false); 
			pst.setString(2, type.getType());
			pst.setString(1, CName.getCname());

			pst.executeUpdate();
			connection.commit();
		}
		catch (SQLException e) {
			System.err.println("Cannot Connect to Database");
		}

	}	    

	@FXML
	private void initialize() throws IOException, SQLException {
		getResidents();
		type.setValue("Safe_Mild");
		type.setItems(typelist); 
		resi.setItems(residents);
		resi.setValue(null);
		cond.setItems(conditions);
		cond.setValue(null);
	}


	@FXML
	void cd_addcondition(MouseEvent event) throws SQLException, IOException {
		Connection connection= SQLite.dbConnector();
		Statement statement = connection.createStatement();
		int status = statement.executeUpdate("INSERT INTO conditions (CName,type" + ") VALUES( '"+ cond_name +"','"+ type.getValue() +"')");
		if(status==1){
			Alert alert =new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Condition");
			alert.setHeaderText(null);
			alert.setContentText("Condition '"+cond_name.getText()+ "' has been Added!");
			alert.showAndWait();
		}


	}
	@FXML
	void medicines(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Medicines.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.setTitle("Medicine Control");

	}

	@FXML
	void shifts(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/DShifts.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.setTitle("Doctor Shifts");

	}
	@FXML
	void patients(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Residents.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.setTitle("View Residents");

	}
	@FXML
	void doctor(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Doctor.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.setTitle("Doctor Home Page");

	}

	@FXML
	void logout(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Landing.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));

	}
	@FXML
	void CondView(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/DisConditions.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.setTitle("Patients with Conditions");

	}

}


