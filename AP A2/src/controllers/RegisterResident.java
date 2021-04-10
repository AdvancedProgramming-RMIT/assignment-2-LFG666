package controllers;

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
import databaseSQL.SQLite;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import Application.Constants;
public class RegisterResident {

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
	    private Button login;
	    @FXML
		private Label lblUser;
	    @FXML
	    private ChoiceBox<String> r_gender;

	    ObservableList<String> genderlist= FXCollections.observableArrayList("MALE","FEMALE");
	    @FXML
	    private void initialize(){
	        r_gender.setValue("MALE");
	        r_gender.setItems(genderlist);
	    }

	    @FXML
	    void register(MouseEvent event) throws SQLException, IOException {
	        String username, password, fname, lname, type, gender;
	        password = r_pass.getText();
	        username = r_user.getText();
	        fname = r_fname.getText();
	        lname = r_lname.getText();
	        type = "RESIDENT";
	        gender = (String) r_gender.getValue();

	        if(!username.isEmpty() &!password.isEmpty()&!fname.isEmpty()&!lname.isEmpty()&!gender.isEmpty()) {
	        Connection connection= SQLite.dbConnector();
	        Statement statement = connection.createStatement();

	        int status = statement.executeUpdate("INSERT INTO users (FName,LName,username,password,type,gender) VALUES ( '"+ fname +"','"+ lname +"','"+ username +"','"+ password +"','" + type +"','"+ gender +"')");

	        if (status==1) {
	            Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath + "/Landing.fxml"));
	            Node node = (Node) event.getSource();
	            Stage stage = (Stage) node.getScene().getWindow();
	            stage.setScene(new Scene(root));
	            Alert alert =new Alert(Alert.AlertType.INFORMATION);
	            alert.setTitle("Resident Registration");
	            alert.setHeaderText(null);
	            alert.setContentText("Resident has been Registered Succesfuly!");
	            alert.showAndWait();

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
	    void login(MouseEvent event) throws IOException {
	        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath + "/Landing.fxml"));

	        Node node = (Node) event.getSource();

	        Stage stage = (Stage) node.getScene().getWindow();

	        stage.setScene(new Scene(root));

	    }
}
