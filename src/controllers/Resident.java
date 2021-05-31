package controllers;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Data;

public class Resident {
	 @FXML
	    private Button showuser;
	    @FXML
	    private Button nurhome;
	    @FXML
	    private Button logout;
	    @FXML
	    private Button nurse;
	    @FXML
		private Label lblUser;
	    
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
	            ResultSet resultSet = statement.executeQuery("select * from users where type = 'RESIDENT'");
	            while (resultSet.next()) {
	            	Data.add(new Data(resultSet.getInt("id"), resultSet.getString("FName"), resultSet.getString("LName"),  resultSet.getString("type"), resultSet.getString("gender")));
	            }
	  
	        } catch (SQLException e) {
	            System.err.println("Cannot Connect to Database");
	        }

	        return Data;
	    }

	    @FXML
	    public void rmData() throws SQLException {
	    	Data selectedItem = tableView.getSelectionModel().getSelectedItem();
	        tableView.getItems().remove(selectedItem);


	        Connection connection= SQLite.dbConnector();
	        Statement statement = connection.createStatement();

	        int status = statement.executeUpdate("DELETE FROM users WHERE id= '"+selectedItem.getId()+"'");
	        int status2 = statement.executeUpdate("DELETE FROM overall WHERE id= '"+selectedItem.getId()+"'");

	        if (status==1 && status2==1) {
	            Alert alert =new Alert(Alert.AlertType.INFORMATION);
	            alert.setTitle("Remove User");
	            alert.setHeaderText(null);
	            alert.setContentText("User "+selectedItem.getFname()+" "+selectedItem.getLname()+" have been removed Successfuly!");
	            alert.showAndWait();
	        }
	    }

	    @FXML
	    void signup(MouseEvent event) throws IOException {
	        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath + "/RegisterResident.fxml"));

	        Node node = (Node) event.getSource();

	        Stage stage = (Stage) node.getScene().getWindow();

	        stage.setScene(new Scene(root));
	        stage.setTitle("Register Resident");

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
