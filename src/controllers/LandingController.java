package controllers;

import model.Admin;
import model.Nurse;
import model.Bed;
import model.Data;
import model.Resident;
import java.io.IOException;
import model.Doctor;
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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Application.Constants;

public class LandingController {
	@FXML
	private Label lblUser;

	@FXML
	private Label lblWarning;
	
	 @FXML
	private TableView<Resident> tableView;

	 @FXML
	private TableColumn<Resident,String> FNameColumn;
	 
	 @FXML
	private TableColumn<Resident,String> LNameColumn;
	 
	 @FXML
	private TableColumn<Resident, String> RoomColumn = new TableColumn<>("idbed");
	 
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
	
	public static List<String> loggedInUsers = new ArrayList<String>();
	

	
	public void btnEnd_button() {
	}
  
	public  ObservableList<Bed> ResInBed = FXCollections.observableArrayList();
	public  ObservableList<Resident> ResidentList = FXCollections.observableArrayList();
	
	@FXML
	void displayCustomers(ActionEvent event) {
			
		    }

	@FXML
	void searchCustomers(ActionEvent event) {

	       
	    }
	@FXML
	public void loadData() throws SQLException {

		FNameColumn.setCellValueFactory(new PropertyValueFactory<Resident,String>("Fname"));
		LNameColumn.setCellValueFactory(new PropertyValueFactory<Resident,String>("Lname"));

		tableView.setItems(selectAll());
	}

	public void JavafxChoiceFill() throws SQLException {
		ResidentList = selectAll();

		ResInBed = SelectBeds();
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
        type.setValue("ADMIN");
        type.getStyleClass().add("center-aligned");
        type.setItems(masterlist);
        try {
			JavafxChoiceFill();
			loadData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        
        
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

                Admin admin = new Admin();
                admin.setUsername(u1.getUser());
                admin.setId(resultSet.getInt("id"));
                admin.setFname(resultSet.getString("fName"));
                admin.setLname(resultSet.getString("LName"));
                admin.setType(resultSet.getString("type"));
                admin.setGender(resultSet.getString("gender"));
                String fullname = admin.getFname() + " " + admin.getLname();
                loggedInUsers.add(fullname);
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

            	Doctor doctor = new Doctor();
                doctor.setUsername(u1.getUser());
                doctor.setId(resultSet.getInt("id"));
                doctor.setFname(resultSet.getString("fName"));
                doctor.setLname(resultSet.getString("LName"));
                doctor.setType(resultSet.getString("type"));
                doctor.setGender(resultSet.getString("gender"));
                String fullname = doctor.getFname() + " " + doctor.getLname();
                loggedInUsers.add(fullname);
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

            	Nurse nurse = new Nurse();
                nurse.setUsername(u1.getUser());
                nurse.setId(resultSet.getInt("id"));
                nurse.setFname(resultSet.getString("fName"));
                nurse.setLname(resultSet.getString("LName"));
                nurse.setType(resultSet.getString("type"));
                nurse.setGender(resultSet.getString("gender"));
                String fullname = nurse.getFname() + " " + nurse.getLname();
                loggedInUsers.add(fullname);
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
