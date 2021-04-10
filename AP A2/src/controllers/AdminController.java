package controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {
	 @FXML
	    void duser(MouseEvent event) throws IOException {
	        Parent root = FXMLLoader.load(getClass().getResource("/displayUsers.fxml"));
	        Node node = (Node) event.getSource();
	        Stage stage = (Stage) node.getScene().getWindow();
	        stage.setScene(new Scene(root));

	    }

	    @FXML
	    void dash(MouseEvent event) throws IOException {
	        Parent root = FXMLLoader.load(getClass().getResource("/homeAdmin.fxml"));
	        Node node = (Node) event.getSource();
	        Stage stage = (Stage) node.getScene().getWindow();
	        stage.setScene(new Scene(root));

	    }
	    @FXML
	    void logout(MouseEvent event) throws IOException {
	        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
	        Node node = (Node) event.getSource();
	        Stage stage = (Stage) node.getScene().getWindow();
	        stage.setScene(new Scene(root));

	    }
	    @FXML
	    void homei(MouseEvent event) throws IOException {
	        Parent root = FXMLLoader.load(getClass().getResource("/homeView.fxml"));
	        Node node = (Node) event.getSource();
	        Stage stage = (Stage) node.getScene().getWindow();
	        stage.setScene(new Scene(root));

	    }

	    @FXML
	    void users(MouseEvent event) throws IOException {
	        Parent root = FXMLLoader.load(getClass().getResource("/users.fxml"));
	        Node node = (Node) event.getSource();
	        Stage stage = (Stage) node.getScene().getWindow();
	        stage.setScene(new Scene(root));

	    }

}
