package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Optional;

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
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Data;
import databaseSQL.SQLite;


public class ShowUsersController {
	@FXML
	private Button suser;
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
	private TableView<Data> tableView;
	@FXML
	private TableColumn<Data,Integer> idColumn;
	@FXML
	private TableColumn<Data,String> FNameColumn;
	@FXML
	private TableColumn<Data,String> LNameColumn;
	@FXML
	private TableColumn<Data,String> typeColumn;
	@FXML
	private TableColumn<Data,String> GenderColumn;


	@FXML
	public void initialize() {
		loadData();
	}

	@FXML
	public void loadData() {

		idColumn.setCellValueFactory(new PropertyValueFactory<Data,Integer>("id"));
		FNameColumn.setCellValueFactory(new PropertyValueFactory<Data,String>("fname"));
		LNameColumn.setCellValueFactory(new PropertyValueFactory<Data,String>("lname"));
		typeColumn.setCellValueFactory(new PropertyValueFactory<Data,String>("type"));
		GenderColumn.setCellValueFactory(new PropertyValueFactory<Data,String>("gender"));

		tableView.setItems(getUsers());
	}

	public ObservableList<Data> getUsers(){
		ObservableList<Data> Data= FXCollections.observableArrayList(); 
		try {
			Connection connection = SQLite.dbConnector();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from users");
			while (resultSet.next()) {
				Data.add(new Data(resultSet.getInt("id"), resultSet.getString("FName"), resultSet.getString("LName"),  resultSet.getString("type"), resultSet.getString("gender")));
			}


		} catch (SQLException e) {
			System.err.println("Cannot Connect to Database");
		}



		return Data;
	}
//discharges patient and archives
	@FXML
	public void rmData() throws SQLException {
		Data selectedItem = tableView.getSelectionModel().getSelectedItem();
		tableView.getItems().remove(selectedItem);
		String AName = LandingController.loggedInUsers.get(LandingController.loggedInUsers.size()-1);
		String fname = selectedItem.getFname();
		String lname = selectedItem.getLname();
		String fullname = fname + " " + lname;
		java.util.Date date = new Date();
		Object time = new java.sql.Timestamp(date.getTime());    
		String type2 = "Discharge";


		Connection connection= SQLite.dbConnector();
		Statement statement = connection.createStatement(); 

		int status = statement.executeUpdate("DELETE FROM users WHERE id= '"+selectedItem.getId()+"'");
		int status2 = statement.executeUpdate("DELETE FROM overall WHERE id= '"+selectedItem.getId()+"'");
		int status3 = statement.executeUpdate("INSERT INTO residents (SName,RName,type,time) VALUES ( '"+ AName +"','"+ fullname +"','"+ type2 +"','"+ time +"')");

		if (status==1 & status2==1 & status3==1) {
			Alert alert =new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Remove User");
			alert.setHeaderText(null);
			alert.setContentText("User "+selectedItem.getFname()+" "+selectedItem.getLname()+" have been removed Successfuly!");
			alert.showAndWait();
		}
	}

	//edits password for Nurse or Doctor
	@FXML
	public void edData() throws SQLException {
		Data sI = tableView.getSelectionModel().getSelectedItem();
		if(sI.getType().equalsIgnoreCase("RESIDENT") || sI.getType().equalsIgnoreCase("ADMIN")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Cannot change Password for this user");
			alert.setContentText("Ooops, there was an error!");
			alert.showAndWait();
		}
		else {
			try {

				TextInputDialog dialog = new TextInputDialog("");
				dialog.setTitle("Update Staff Details");
				dialog.setContentText("Please enter new password : ");
				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){

					Connection connection= SQLite.dbConnector();				
					String St = "UPDATE users SET password = ? WHERE id= '"+sI.getId()+"'";
					try(PreparedStatement ps = connection.prepareStatement(St))
					{
						connection.setAutoCommit(false); 
						ps.setString(1, result.get()); 
						ps.executeUpdate();
						connection.commit();
						ps.close();}
					catch (SQLException e) {
						System.err.println("Cannot Connect to Database");
					}

					Alert alert =new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Remove User");
					alert.setHeaderText(null);
					alert.setContentText("User "+sI.getFname()+" "+sI.getLname()+" Password has been changed successfuly!");
					alert.showAndWait();


				}
				else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Please enter a valid password");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();
					return;
				}
			}
			catch(InputMismatchException e) {
				System.err.println("Only positive numbers allowed");}}




	}




	@FXML
	void suser(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/AddRes.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));

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

}


