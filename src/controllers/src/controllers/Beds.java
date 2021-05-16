package controllers;

import java.io.IOException;

import Application.Constants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



public class Beds {
	@FXML
    private Button nurse;

	@FXML
    void bedmenu(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/ShowUsers.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Resident Control");

    }
	 @FXML
	    void nurse(MouseEvent event) throws IOException {
	        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Nurse.fxml"));
	        Node node = (Node) event.getSource();
	        Stage stage = (Stage) node.getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.setTitle("Nurse Homepage");

	    }

}
