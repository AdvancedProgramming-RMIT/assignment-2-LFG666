package controllers;


import model.Doctor;
import model.Nurse;
import model.Resident;
import model.Roster;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import Application.Constants;
import databaseSQL.SQLite;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AdminSetDocShift {

public  ObservableList<Doctor> DoctorList = FXCollections.observableArrayList(); 

ObservableList<Roster> personalRosters = FXCollections.observableArrayList();
ObservableList<Roster> rosterArrayList = FXCollections.observableArrayList();

@FXML
private ChoiceBox<String> ChoiceAdd;
@FXML
private Button ButtonDeleteSpDoctor;
@FXML
private Button Remove;
@FXML
private Button ButtonDeleteSpDay;
@FXML
private Button ButtonDeleteAll;
@FXML
private Button ButtonDeleteSpShift;
@FXML
private ChoiceBox<String> ChoiceDay;
@FXML
private Button BtnDoc;
@FXML
private Button BtnLeave;
@FXML
private TextField Shift;
@FXML
private TableView<Roster> tableView;
@FXML
private TableColumn<Roster,Number> idColumn = new TableColumn<>("id");
@FXML
private TableColumn<Roster,String> FNameColumn = new TableColumn<>("FName");
@FXML
private TableColumn<Roster,String> LNameColumn = new TableColumn<>("LName");
@FXML
private TableColumn<Roster,Integer> ShiftColumn;
@FXML
private TableColumn<Roster,String> dayColumn;
@FXML
private Button adduser;
@FXML
private Button logout;
@FXML
private Button wardadmin;
@FXML
private Button admin;
@FXML
private Label lblUser;
@FXML
private Button save;



@FXML
public void initialize() throws SQLException {
	 try {
	        JavafxChoiceFill();
	        loadData();
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }
	
	 idColumn.setCellValueFactory(new Callback<CellDataFeatures<Roster, Number>, 
             ObservableValue<Number>>() {  
@Override  
public ObservableValue<Number> call(CellDataFeatures<Roster, Number> data){  
return data.getValue().getStaff().getidProperty();  
}  
});  
		
		 
	 
	 FNameColumn.setCellValueFactory(new Callback<CellDataFeatures<Roster, String>, 
             ObservableValue<String>>() {  
@Override  
public ObservableValue<String> call(CellDataFeatures<Roster, String> data2){  
return data2.getValue().getStaff().getFnameProperty();  
}  
});  
	
	 LNameColumn.setCellValueFactory(new Callback<CellDataFeatures<Roster, String>, 
             ObservableValue<String>>() {  
@Override  
public ObservableValue<String> call(CellDataFeatures<Roster, String> data3){  
return data3.getValue().getStaff().getlnameProperty();  
}  
});  }
	 
	 
@FXML
public void loadData() throws SQLException {


	ShiftColumn.setCellValueFactory(new PropertyValueFactory<Roster,Integer>("Shift"));
	dayColumn.setCellValueFactory(new PropertyValueFactory<Roster,String>("day"));

	tableView.setItems(SelectAll());
}



public void JavafxChoiceFill() throws SQLException {
    DoctorList = selectAll();
    ChoiceAdd.getItems().clear();
    ChoiceDay.getItems().clear();
    for (Doctor t : DoctorList) {
        ChoiceAdd.getItems().add(t.getFname());
    }
    ChoiceDay.getItems().addAll("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
    for (Doctor t : DoctorList) {
        rosterArrayList = null;
        rosterArrayList = SelectAllByFname(t.getFname());}
}


	public void insertRoster(String Fname, String Lname, ArrayList<Roster> rosterArrayList) throws SQLException{
        Connection connection = SQLite.dbConnector(); 
        for(Roster ro : rosterArrayList)  {
        	String St = "INSERT INTO roster(FName, LName, shift, day ) VALUES (?, ?, ?, ? )";
        	PreparedStatement ps = connection.prepareStatement(St);
        	ps.setString(1, Fname);
        	ps.setString(2, Lname);
        	ps.setInt(3, ro.getShift());
        	ps.setString(4,  ro.getDay());
        	ps.executeUpdate();
        	ps.close();
        }
  
	}
	
	public int insertSpecificRoster(String Fname, String Lname, Roster roster) throws SQLException{
        
		Connection connection = SQLite.dbConnector();
        	String St = "INSERT INTO roster(FName, LName, shift, day ) VALUES (?, ?, ?, ? )";
        	PreparedStatement ps = connection.prepareStatement(St);
        	ps.setString(1, Fname); 
        	ps.setString(2, Lname);
        	ps.setInt(3, roster.getShift());
        	ps.setString(4,  roster.getDay());
        	int layout = ps.executeUpdate();
        	ps.close();
        	return layout;
        
  
	}
        
	 public ObservableList<Roster> SelectAll() throws SQLException {
	        ObservableList<Roster> rosterArrayList = FXCollections.observableArrayList(); 
	        Connection connection = SQLite.dbConnector(); 
	        String sql =  "select * from users, roster where users.type = 'Doctor' and users.FName = roster.FName"; 
	        PreparedStatement ps = connection.prepareStatement(sql); 
	        ResultSet rs = ps.executeQuery();


	        while (rs.next()) {
	        	
	            Doctor d = new Doctor(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getString("userName"), rs.getString("type"), rs.getString("gender"));
	        	
	            Roster r = new Roster(d, rs.getInt("Shift"), rs.getString("day"));
	            rosterArrayList.add(r);
	        }

	        ps.close();
	        rs.close();
	        return rosterArrayList;
	    }
	
	 public ObservableList<Roster> SelectAllByFname(String Fname) throws SQLException {

	        ObservableList<Roster> rosterArrayList = FXCollections.observableArrayList();
	        String sql = "select * from roster";
	        Connection connection = SQLite.dbConnector();
	        PreparedStatement ps = connection.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            if (Fname.equals(rs.getString("FName"))) {
	                Roster ro = new Roster(rs.getInt("shift"), rs.getString("day"));
	                rosterArrayList.add(ro);
	            }
	        }

	        ps.close();
	        rs.close();
	        return rosterArrayList; 
	    }
	 

	    public void removeRoster() throws SQLException {
	    	Roster selectedItem = tableView.getSelectionModel().getSelectedItem();
	    	tableView.getItems().remove(selectedItem);
	        
	    	String sql1 = "DELETE FROM roster WHERE day= '"+selectedItem.getDay()+"'";
	        Connection connection = SQLite.dbConnector();
	        PreparedStatement ps = connection.prepareStatement(sql1); 
	        ps.executeUpdate();
	        ps.close();
	    }


	    public ObservableList<Doctor> selectAll() throws SQLException {
	        ObservableList<Doctor> list = FXCollections.observableArrayList();
	        System.out.println(list);

	        String sql = "select * from users where type = 'Doctor'";
	        Connection connection = SQLite.dbConnector();
	        PreparedStatement ps = connection.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {

		        Doctor d = new Doctor(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getString("userName"), rs.getString("type"), rs.getString("gender"));
	            list.add(d);
	        }

	        ps.close();
	        rs.close();

	        return list;
	    }


	    @FXML
	    void save(MouseEvent event) throws SQLException, NumberFormatException, IOException {
	    	 rosterArrayList = null;
	    	 personalRosters.clear();
	    	 Integer shift = 0;
	    	 
	    	 try {
	
	    	 shift = Integer.parseInt(Shift.getText());
	    	 }catch (NumberFormatException e){
	                Alert alert = new Alert(Alert.AlertType.ERROR);
	                alert.setTitle("Correct the numeric data");
	                alert.setHeaderText("Correct the numeric data");
	                alert.setContentText("Correct the numeric data");
	                alert.showAndWait();
	    }
	    	 
	    	 
	    	 
	    	 if(shift < 800 || shift > 2100){
	    	 
	    	 
	    		 Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Please Choose a time between 0800 & 2100");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();
					Shift.clear();
	    	 }

	    	 else {		 
	    	 String cd = ChoiceDay.getSelectionModel().getSelectedItem();
	    	
	    	 if(cd=="Monday") {
	    		 personalRosters.add(new Roster(shift, "Monday"));	 
	    	 }
	    	 if(cd=="Tuesday") {
	    		 personalRosters.add(new Roster(shift, "Tuesday"));	 
	    	 }
	    	 if(cd=="Wednesday") {
	    		 personalRosters.add(new Roster(shift, "Wednesday"));	 
	    	 }
	    	 if(cd=="Thursday") {
	    		 personalRosters.add(new Roster(shift, "Thursday"));	 
	    	 }
	    	 if(cd=="Friday") {
	    		 personalRosters.add(new Roster(shift, "Friday"));	 
	    	 }
	    	 if(cd=="Saturday") {
	    		 personalRosters.add(new Roster(shift, "Saturday"));	 
	    	 }
	    	 if(cd=="Sunday") {
	    		 personalRosters.add(new Roster(shift, "Sunday"));	 
	    	 }
	    	 

		        for (Doctor t : DoctorList) {
		            if (ChoiceAdd.getValue().equals(t.getFname())) {
		                rosterArrayList = SelectAllByFname(t.getFname());
		                for (Roster ws : personalRosters)
		                    insertSpecificRoster(t.getFname(), t.getLname(), ws);
		            }
		        }

		        loadData();
		    
	    }}

	    @FXML
	    void remove(MouseEvent event) throws SQLException, NumberFormatException, IOException {
	    removeRoster();
	    	
	    	
	    	
	    }
	    
	    @FXML
	    void Leave(ActionEvent event) throws IOException, SQLException {
	    	Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Landing.fxml"));
	        Node node = (Node) event.getSource();
	        Stage stage = (Stage) node.getScene().getWindow();
	        stage.setScene(new Scene(root));

	    }
	    	
		 @FXML
		    void rusers(MouseEvent event) throws IOException {
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

		    
	
}
