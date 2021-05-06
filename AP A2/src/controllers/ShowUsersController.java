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
import databaseSQL.SQLite;


public class ShowUsersController {
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

	    @FXML
	    public void rmData() throws SQLException {
	    	Data selectedItem = tableView.getSelectionModel().getSelectedItem();
	        tableView.getItems().remove(selectedItem);


	        Connection connection= SQLite.dbConnector();
	        Statement statement = connection.createStatement();

	        int status = statement.executeUpdate("DELETE FROM users WHERE id= '"+selectedItem.getId()+"'");

	        if (status==1) {
	            Alert alert =new Alert(Alert.AlertType.INFORMATION);
	            alert.setTitle("Remove User");
	            alert.setHeaderText(null);
	            alert.setContentText("User "+selectedItem.getFname()+" "+selectedItem.getLname()+" have been removed Successfuly!");
	            alert.showAndWait();
	        }
	    }

		
		 @FXML
		    void suser(MouseEvent event) throws IOException {
		        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/ShowUsers.fxml"));
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


