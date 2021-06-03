package controllers;
import java.io.IOException;
import java.sql.Connection;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Data;
import model.SickResident;
import databaseSQL.SQLite;

public class DisConController {


	@FXML
	private Label lblUser;
	@FXML
	private Button medicines;
	@FXML
	private Button shifts;
	@FXML
	private Button logout;
	@FXML
	private Button patients;
	@FXML
	private Button conditions;
	@FXML
	private Button doctor;



	@FXML
	private TableView<SickResident> tableView;
	@FXML
	private TableColumn<SickResident, Integer> id2Column;
	@FXML
	private TableColumn<SickResident,String> Fname2Column;
	@FXML
	private TableColumn<SickResident,String> Lname2Column;
	@FXML
	private TableColumn<SickResident,String> TypecColumn;
	@FXML
	private TableColumn<SickResident,String> gender2Column;
	@FXML
	private TableColumn<SickResident,String> CNamecColumn;



	@FXML
	public void initialize() {
		loadData();
	}

	@FXML
	public void loadData() {

		id2Column.setCellValueFactory(new PropertyValueFactory<SickResident,Integer>("id2"));
		Fname2Column.setCellValueFactory(new PropertyValueFactory<SickResident,String>("Fname2"));
		Lname2Column.setCellValueFactory(new PropertyValueFactory<SickResident,String>("Lname2"));
		TypecColumn.setCellValueFactory(new PropertyValueFactory<SickResident,String>("gender2"));
		gender2Column.setCellValueFactory(new PropertyValueFactory<SickResident,String>("CNamec"));
		CNamecColumn.setCellValueFactory(new PropertyValueFactory<SickResident,String>("Typec"));

		tableView.setItems(getUsers());
	}

	public ObservableList<SickResident> getUsers(){
		ObservableList<SickResident> Data= FXCollections.observableArrayList();
		try {
			Connection connection = SQLite.dbConnector();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from condres");
			while (resultSet.next()) {

				Data.add(new SickResident(resultSet.getInt("ID"), resultSet.getString("FName"), resultSet.getString("LName"),  resultSet.getString("Type"), resultSet.getString("gender"), resultSet.getString("CName")));
			}



		} catch (SQLException e) {
			System.err.println("Cannot Connect to Database");
		}



		return Data;
	}

	@FXML
	public void rmData() throws SQLException {
		Object selectedItem = tableView.getSelectionModel().getSelectedItem();
		tableView.getItems().remove(selectedItem);


		Connection connection= SQLite.dbConnector();
		Statement statement = connection.createStatement();

		int status = statement.executeUpdate("DELETE FROM condress WHERE id= '"+((Node) selectedItem).getId()+"'");

		if (status==1) {
			Alert alert =new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Remove User");
			alert.setHeaderText(null);
			alert.setContentText("User "+((Data) selectedItem).getFname()+" "+((Data) selectedItem).getLname()+" have been removed Successfuly!");
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
	void conditions(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Conditions.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.setTitle("Condition Control");

	}

	@FXML
	void logout(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Landing.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));

	}
	@FXML
	void doctor(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Doctor.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.setTitle("Doctor Home Page");

	}



}




