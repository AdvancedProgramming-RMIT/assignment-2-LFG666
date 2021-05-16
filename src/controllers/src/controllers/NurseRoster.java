package controllers;




import model.Nurse;
import model.Roster;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



public class NurseRoster  {

	@FXML
	private Button nurhome;
	@FXML
	private Button logout;
	@FXML
	private Label lblUser;
	@FXML
	private TableView<Roster> tableView;
	@FXML
	private TableColumn<Roster,Integer> idColumn;
	@FXML
	private TableColumn<Roster,String> FNameColumn;
	@FXML
	private TableColumn<Roster,String> LNameColumn;
	@FXML
	private TableColumn<Roster,Integer> ShiftColumn;
	@FXML
	private TableColumn<Roster,String> dayColumn;


	@FXML
	public void initialize() {
		loadData();
	}

	@FXML
	public void loadData() {

		idColumn.setCellValueFactory(new PropertyValueFactory<Roster,Integer>("id"));
		FNameColumn.setCellValueFactory(new PropertyValueFactory<Roster,String>("fname"));
		LNameColumn.setCellValueFactory(new PropertyValueFactory<Roster,String>("lname"));
		ShiftColumn.setCellValueFactory(new PropertyValueFactory<Roster,Integer>("Shift"));
		dayColumn.setCellValueFactory(new PropertyValueFactory<Roster,String>("day"));

		tableView.setItems(getRoster());
	}

	public ObservableList<Roster> getRoster(){
		ObservableList<Roster> Roster= FXCollections.observableArrayList();
		try {
			Connection connection = SQLite.dbConnector();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from users where users.FName = Nurse.FName");
			while (rs.next()) {
				Nurse d = new Nurse(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getString("userName"), rs.getString("type"), rs.getString("gender"));

				Roster r = new Roster(d, rs.getInt("shift"), rs.getString("day"));
				Roster.add(r);
			}


		} catch (SQLException e) {
			System.err.println("Cannot Connect to Database");
		}



		return Roster;
	}
	@FXML
	void nurhome(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Nurse.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.setTitle("Nurse Home Page");

	}
	@FXML
	void logout(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Landing.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));

	}


}
