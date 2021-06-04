package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import Application.Constants;
import databaseSQL.SQLite;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Bed;
import model.Resident;

public class ResidentV {
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
	private TableView<Resident> tableView;
	@FXML
	private TableColumn<Resident,Integer> idColumn;
	@FXML
	private TableColumn<Resident,String> FNameColumn;
	@FXML
	private TableColumn<Resident,String> LNameColumn;
	@FXML
	private TableColumn<Resident,String> typeColumn;
	@FXML
	private TableColumn<Resident,String> GenderColumn;
	@FXML
	private TableColumn<Resident, String> RoomColumn = new TableColumn<>("idbed");

	public  ObservableList<Bed> ResInBed = FXCollections.observableArrayList();
	public  ObservableList<Resident> ResidentList = FXCollections.observableArrayList();


	@FXML
	public void initialize() {
		try {
			ResidentList = selectAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			ResInBed = SelectBeds();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		loadData();

		Callback<CellDataFeatures<Resident, String>, ObservableValue<String>> callback = 
				new Callback<CellDataFeatures<Resident, String>, ObservableValue<String>> () {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Resident, String> param) {

				for(Bed c : ResInBed) {
					System.out.println(c.getResident());
					if(c.getResident().getFname().equals(param.getValue().getName())){
						return c.idBedProperty();
					}

				}

				return null;
			}
		};
		//set resident columnn's call-back for factory method
		RoomColumn.setCellValueFactory(callback);

	}




	@FXML
	public void loadData() {

		idColumn.setCellValueFactory(new PropertyValueFactory<Resident,Integer>("id"));
		FNameColumn.setCellValueFactory(new PropertyValueFactory<Resident,String>("fname"));
		LNameColumn.setCellValueFactory(new PropertyValueFactory<Resident,String>("lname"));
		typeColumn.setCellValueFactory(new PropertyValueFactory<Resident,String>("type"));
		GenderColumn.setCellValueFactory(new PropertyValueFactory<Resident,String>("gender"));

		tableView.setItems(getUsers());
	}

	public ObservableList<Resident> getUsers(){
		ObservableList<Resident> Data= FXCollections.observableArrayList();
		try {
			Connection connection = SQLite.dbConnector();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from users where type = 'RESIDENT'");
			while (resultSet.next()) {
				Data.add(new Resident(resultSet.getInt("id"), resultSet.getString("FName"), resultSet.getString("LName"),  resultSet.getString("type"), resultSet.getString("gender")));
			}

		} catch (SQLException e) {
			System.err.println("Cannot Connect to Database");
		}

		return Data;
	}

	//discharges resident from care home. archives patient
	@FXML
	public void rmData() throws SQLException {
		Resident selectedItem = tableView.getSelectionModel().getSelectedItem();
		tableView.getItems().remove(selectedItem);
		String NName = LandingController.loggedInUsers.get(LandingController.loggedInUsers.size()-1);
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
		int status3 = statement.executeUpdate("INSERT INTO residents (SName,RName,type,time) VALUES ( '"+ NName +"','"+ fullname +"','"+ type2 +"','"+ time +"')");
		if (status==1 & status2==1 & status3==1) {
			Alert alert =new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Remove User");
			alert.setHeaderText(null);
			alert.setContentText("User "+selectedItem.getFname()+" "+selectedItem.getLname()+" have been removed Successfuly!");
			alert.showAndWait();
		}
	}

	public ObservableList<Resident> selectAll() throws SQLException {
		ObservableList<Resident> list = FXCollections.observableArrayList();
		System.out.println(list);

		String sql = "select * from users where type = 'RESIDENT'";
		Connection connection = SQLite.dbConnector();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			Resident r = new Resident(rs.getInt("id"), rs.getString("FName"), rs.getString("LName"), rs.getString("type"), rs.getString("gender"));
			list.add(r);
		}

		ps.close();
		rs.close();

		return list;
	}

	public ObservableList<Bed> SelectBeds() throws SQLException {
		ObservableList<Bed> bedsLists = FXCollections.observableArrayList();
		Connection connection = SQLite.dbConnector();
		String sql =  "select FName, LName, idbed, gender from bed where FName is not NULL";   
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();


		while (rs.next()) {
			Resident r = new Resident(rs.getString("fname"), rs.getString("lname"), rs.getString("gender"));
			Bed b = new Bed(r, rs.getString("idbed"));
			r.setFname(rs.getString("FName"));
			r.setLname(rs.getString("LName"));
			r.setGender(rs.getString("gender"));
			b.setIdBed(rs.getString("idbed"));
			bedsLists.add(b);
		}

		ps.close();
		rs.close();
		return bedsLists;
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
