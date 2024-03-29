package controllers;

import model.Data;
import model.Resident;
import java.io.IOException;

import databaseSQL.SQLite;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Application.Constants;

public class LandingController {
	@FXML
	private Label lblUser;

	@FXML
	private Label lblWarning;
	
	 @FXML
	private TableView<model.Resident> CustomersTableView;

	 @FXML
	private TableColumn<model.Resident, Integer> CustomerIDColumn;

	 @FXML
	private TableColumn<model.Resident, String> CustomerNameColumn;
	 
	 @FXML
	TextField searchCustomerTextBox;

	@FXML
	private Button Doctor;

	@FXML
	private Button Nurse;

	@FXML
	private Button Exit;

	@FXML
	private Button Manager;

	@FXML
	private Button ID;
	
	@FXML
	private Button Name;

	
	public void btnEnd_button() {
	}
  

	
	@FXML
	void displayCustomers(ActionEvent event) {
			
		    }

	@FXML
	void searchCustomers(ActionEvent event) {

	       
	    }
	


	ObservableList<String> masterlist= FXCollections.observableArrayList("DOCTOR","NURSE","ADMIN");
    @FXML
    private TextField lc_user;

    @FXML
    private PasswordField lc_pass;
    @FXML
    private ChoiceBox<String> type;
    @FXML
    private void initialize(){
        type.setValue("DOCTOR");
        type.setItems(masterlist);
    }
    @FXML
    void lc_login(MouseEvent event) throws SQLException, IOException {
        Data u1=new Data();
        u1.setPass(lc_pass.getText());
        u1.setUser(lc_user.getText());


        Connection connection= SQLite.dbConnector();
        Statement statement = connection.createStatement();


        if (type.getValue()=="ADMIN") {
            ResultSet resultSet = statement.executeQuery("select * from users where username" +
                    " = '" + u1.getUser() + "' and password = '" + u1.getPass() + "' and type = 'ADMIN'");
            if(resultSet.next()) {

                Resident resident = new Resident();
                resident.setUsername(u1.getUser());
                resident.setId(resultSet.getInt("id"));
                resident.setFname(resultSet.getString("fName"));
                resident.setLname(resultSet.getString("LName"));
                resident.setType(resultSet.getString("type"));
                resident.setGender(resultSet.getString("gender"));
                Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Admin.fxml"));
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Admin Control");

            }
            else{
                Alert alert =new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User Login");
                alert.setHeaderText(null);
                alert.setContentText("No Admin With this name!");
                alert.showAndWait();
            }
        }
        else if (type.getValue()=="DOCTOR") {
            ResultSet resultSet = statement.executeQuery("select * from users where username" +
                    " = '" + u1.getUser() + "' and password = '" + u1.getPass() + "' and type = 'DOCTOR'");
            if(resultSet.next()) {

            	Resident resident = new Resident();
                resident.setUsername(u1.getUser());
                resident.setId(resultSet.getInt("id"));
                resident.setFname(resultSet.getString("fName"));
                resident.setLname(resultSet.getString("LName"));
                resident.setType(resultSet.getString("type"));
                resident.setGender(resultSet.getString("gender"));
                Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Doctor.fxml"));
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Doctor Home Page");

            }
            else{
                Alert alert =new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User Login");
                alert.setHeaderText(null);
                alert.setContentText("No Doctor With this name!");
                alert.showAndWait();
            }
        }

        else if (type.getValue()=="NURSE") {
            ResultSet resultSet = statement.executeQuery("select * from users where username" +
                    " = '" + u1.getUser() + "' and password = '" + u1.getPass() + "' and type = 'Nurse'");
            if(resultSet.next()) {

            	Resident resident = new Resident();
                resident.setUsername(u1.getUser());
                resident.setId(resultSet.getInt("id"));
                resident.setFname(resultSet.getString("fName"));
                resident.setLname(resultSet.getString("LName"));
                resident.setType(resultSet.getString("type"));
                resident.setGender(resultSet.getString("gender"));

                Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Nurse.fxml"));
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Nurse Home Page");
            }
            else{
                Alert alert =new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User Login");
                alert.setHeaderText(null);
                alert.setContentText("No Nurse With this name!");
                alert.showAndWait();
            }
        }


    }

    double x = 0, y =0;
    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    void dragged(MouseEvent event) {

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }


    @FXML
    void signup(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath + "/RegisterResident.fxml"));

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.setTitle("Register Resident");

    }


}
