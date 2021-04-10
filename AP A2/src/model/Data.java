package model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Application.Constants;
import databaseSQL.SQLite;
public class Data {
	 Integer id;
	    String user;
		String pass;
		String fname;
		String lname;
		String type;
		String gender;


	    public Data(){
	        user=" ";
	        pass=" ";
	        fname=" ";
	        lname=" ";
	        type=" ";


	    }
	    public Data(Integer id,String Fname,String Lname,String type,String gender){
	        this.id=id;
	        this.fname=Fname;
	        this.lname=Lname;
	        this.gender=gender;
	        this.type=type;
	    }

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getUser() {
	        return user;
	    }

	    public void setUser(String user) {
	        this.user = user;
	    }

	    public String getPass() {
	        return pass;
	    }

	    public void setPass(String pass) {
	        this.pass = pass;
	    }

	    public String getFname() {
	        return fname;
	    }

	    public void setFname(String fname) {
	        this.fname = fname;
	    }

	    public String getLname() {
	        return lname;
	    }

	    public void setLname(String lname) {
	        this.lname = lname;
	    }


	    public String getType() {
	        return type;
	    }

	    public void setType(String type) {
	        this.type = type;
	    }

	    public String getGender() {
	        return gender;
	    }

	    public void setGender(String gender) {
	        this.gender = gender;
	    }

	    @FXML
	    void lc_login(MouseEvent event) throws SQLException, IOException {

	        Connection connection= SQLite.dbConnector();
	        Statement statement = connection.createStatement();

	        ResultSet resultSet = statement.executeQuery("select * from users where username" +
	                " = '" + user + "'");

	        if (resultSet.next()) {
	            Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath + "/homeResident.fxml"));
	            Node node = (Node) event.getSource();
	            Stage stage = (Stage) node.getScene().getWindow();
	            stage.setScene(new Scene(root));

	        }


	    }
}
