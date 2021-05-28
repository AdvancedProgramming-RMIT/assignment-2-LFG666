package controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import Application.Constants;

public class AdminController {
    @FXML
    private Button showuser;
    @FXML
    private Button adduser;
    @FXML
    private Button logout;
    @FXML
    private Button addres;
    @FXML
    private Button wardadmin;
    @FXML
	private Label lblUser;
    @FXML
    private Button doctorshift;
    @FXML
    private Button nurseshift;

    
	
	 @FXML
	    void suser(MouseEvent event) throws IOException {
	        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/ShowUsers.fxml"));
	        Node node = (Node) event.getSource();
	        Stage stage = (Stage) node.getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.setTitle("Show Users");

	    }

	    @FXML
	    void docroster(MouseEvent event) throws IOException {
	        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/AdminDoctorRoster.fxml"));
	        Node node = (Node) event.getSource();
	        Stage stage = (Stage) node.getScene().getWindow();
	        stage.setScene(new Scene(root));

	    }
	    
	    @FXML
	    void nurroster(MouseEvent event) throws IOException {
	        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/AdminNurseRoster.fxml"));
	        Node node = (Node) event.getSource();
	        Stage stage = (Stage) node.getScene().getWindow();
	        stage.setScene(new Scene(root));

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
	        stage.setTitle("Ward View");

	    }

	    @FXML
	    void rusers(MouseEvent event) throws IOException {
	        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/RUsers.fxml"));
	        Node node = (Node) event.getSource();
	        Stage stage = (Stage) node.getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.setTitle("Register Users");

	    }
	    @FXML
	    void addres(MouseEvent event) throws IOException {
	        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/AddRes.fxml"));
	        Node node = (Node) event.getSource();
	        Stage stage = (Stage) node.getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.setTitle("Register Users");

	    }

}
