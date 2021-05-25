package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Application.Constants;
import databaseSQL.SQLite;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Bed;
import model.Doctor;
import model.Medicines;
import model.Room;
import model.Roster;
import model.Ward;
import model.Resident;

public class WardViewController {
	 @FXML
	    private Button showuser;
	    @FXML
	    private Button adduser;
	    @FXML
	    private Button logout;
	    @FXML
	    private Button wardadmin;
	    @FXML
	    private Button removeres;
	    @FXML
		private Label lblUser;
	    @FXML
	    private Button admin;
	    @FXML
	    private TableView<Resident> tableView;
	    @FXML
	    private TableColumn<Resident,String> FNameColumn;
	    @FXML
	    private TableColumn<Resident,String> LNameColumn;
	    @FXML
	    private TableColumn<Resident,String> BedColumn;
	    @FXML
	    private Button w1r1b1;
	    @FXML
	    private Button w1r2b1;
	    @FXML
	    private Button w1r2b2;
	    @FXML
	    private Rectangle w1r3b1 = new Rectangle();
	    @FXML
	    private Rectangle w1r3b2 = new Rectangle();
	    @FXML
	    private Rectangle w1r3b3 = new Rectangle();
	    @FXML
	    private Rectangle w1r3b4 = new Rectangle();
	    @FXML
	    private Rectangle w1r4b1 = new Rectangle();
	    @FXML
	    private Rectangle w1r4b2 = new Rectangle();
	    @FXML
	    private Rectangle w1r4b3 = new Rectangle();
	    @FXML
	    private Rectangle w1r4b4 = new Rectangle();
	    @FXML
	    private Rectangle w1r5b1 = new Rectangle();
	    @FXML
	    private Rectangle w1r5b2 = new Rectangle();
	    @FXML
	    private Rectangle w1r5b3 = new Rectangle();
	    @FXML
	    private Rectangle w1r5b4 = new Rectangle();
	    @FXML
	    private Rectangle w1r6b1 = new Rectangle();
	    @FXML
	    private Rectangle w1r6b2 = new Rectangle();
	    @FXML
	    private Rectangle w1r6b3 = new Rectangle();
	    @FXML
	    private Rectangle w1r6b4 = new Rectangle();
	    @FXML
	    private Rectangle w2r1b1 = new Rectangle();
	    @FXML
	    private Rectangle w2r2b1 = new Rectangle();
	    @FXML
	    private Rectangle w2r2b2 = new Rectangle();
	    @FXML
	    private Rectangle w2r3b1 = new Rectangle();
	    @FXML
	    private Rectangle w2r3b2 = new Rectangle();
	    @FXML
	    private Rectangle w2r3b3 = new Rectangle();
	    @FXML
	    private Rectangle w2r3b4 = new Rectangle();
	    @FXML
	    private Rectangle w2r4b1 = new Rectangle();
	    @FXML
	    private Rectangle w2r4b2 = new Rectangle();
	    @FXML
	    private Rectangle w2r4b3 = new Rectangle();
	    @FXML
	    private Rectangle w2r4b4 = new Rectangle();
	    @FXML
	    private Rectangle w2r5b1 = new Rectangle();
	    @FXML
	    private Rectangle w2r5b2 = new Rectangle();
	    @FXML
	    private Rectangle w2r5b3 = new Rectangle();
	    @FXML
	    private Rectangle w2r5b4 = new Rectangle();
	    @FXML
	    private Rectangle w2r6b1 = new Rectangle();
	    @FXML
	    private Rectangle w2r6b2 = new Rectangle();
	    @FXML
	    private Rectangle w2r6b3 = new Rectangle();
	    @FXML
	    private Rectangle w2r6b4 = new Rectangle();
	    
	     String style = "-fx-background-color:  DODGERBLUE";
	     String style2 = "-fx-background-color:  WHITE";
	     String style3 = "-fx-background-color:  #ff1f1f";

	    
	    public  ObservableList<Resident> ResidentList = FXCollections.observableArrayList();
	    public  ObservableList<Bed> BedList = FXCollections.observableArrayList();
	    public  ObservableList<Room> RoomList = FXCollections.observableArrayList();
	    public  ObservableList<Ward> WardList = FXCollections.observableArrayList();
	    public  ObservableList<Ward> WardsList = FXCollections.observableArrayList();
	    
	    @FXML
	    public void initialize() throws SQLException {
	    	 try {
	    	        JavafxChoiceFill();
	    	        loadData();
	    	    } catch (SQLException e) {
	    	        e.printStackTrace();
	    	    }
	    	 
	    	 tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Resident>() {
	              @Override
	              public void changed(ObservableValue<? extends Resident> observable,Resident oldValue, Resident newValue){
	                  if(newValue!=null){
	                        
	                  }


	              }
	          });

	    	 
	    	 
	    	 	w1r1b1.setOnAction(event -> {
	 	    	Resident r = tableView.getSelectionModel().getSelectedItem();
	 	        w1r1b1.setStyle(style2);
 
	 	            if (r.getGender().equals("MALE")) {
	 	            	w1r1b1.getStyleClass().removeAll(style2);
	 	                w1r1b1.setStyle(style);}
	 	        else {w1r1b1.setStyle(style3);
	 	        	
	 	        }
	 	            
	 	            for (Bed b : BedList) {
	 	            	b.setIdBed("1");
	 	            		if(b.isBedAvailable()) {
	 	            			b.setResident(r);
	 	            		}
	 	            		System.out.println(BedList);
	 	            	}
	 	            
	 	           
	 	            
	 	           	            	});
	 	            


	 	    w1r2b1.setOnAction(event -> {
	 	    	Resident r = tableView.getSelectionModel().getSelectedItem();
	 	        w1r2b1.setStyle(style2);
 
	 	            if (r.getGender().equals("MALE")) {
	 	                w1r2b1.setStyle(style);
	 	                }
	 	        else {w1r2b1.setStyle(style3);
		 	        	
		 	        }
	 	    });


	 	   w1r2b2.setOnAction(event -> {
	 	    	Resident r = tableView.getSelectionModel().getSelectedItem();
	 	        w1r2b2.setStyle(style2);
 
	 	            if (r.getGender().equals("MALE")) {
	 	                w1r2b2.setStyle(style);}
	 	        else {w1r2b2.setStyle(style3);
		 	        	
		 	        }
	 	    	});
	    	 
	    	 
	    	 
	    }
	    	 
	    	 
	
	    
	    
	    	 @FXML
	    	 public void loadData() throws SQLException {



	    	 	FNameColumn.setCellValueFactory(new PropertyValueFactory<Resident,String>("Fname"));
	    	 	LNameColumn.setCellValueFactory(new PropertyValueFactory<Resident,String>("Lname"));


	    	 	tableView.setItems(selectAll());
	    	 }



	    	 public void JavafxChoiceFill() throws SQLException {
	    	     ResidentList = selectAll();
	    	     BedList = SelectBed();
	    	     RoomList = SelectRoom();
	    	     WardList = SelectWard();
	    	     WardsList = SelectWards();

	    	    
	    	 }

	    	 public ObservableList<Resident> selectAll() throws SQLException {
	 	        ObservableList<Resident> list = FXCollections.observableArrayList();
	 	        System.out.println(list);

	 	        String sql = "select * from users where type = 'RESIDENT'";
	 	        Connection connection = SQLite.dbConnector();
	 	        PreparedStatement ps = connection.prepareStatement(sql);
	 	        ResultSet rs = ps.executeQuery();

	 	        while (rs.next()) {

	 		        Resident r = new Resident(rs.getInt("id"), rs.getString("FName"), rs.getString("LName"), rs.getString("userName"), rs.getString("type"), rs.getString("gender"));
	 	            list.add(r);
	 	        }

	 	        ps.close();
	 	        rs.close();

	 	        return list;
	 	    }
	    	 
	    	 public ObservableList<Bed> SelectBed() throws SQLException {
	 	        ObservableList<Bed> bedsList = FXCollections.observableArrayList();
	 	        Connection connection = SQLite.dbConnector();
		        String sql =  "select * from bed";  
	 	        PreparedStatement ps = connection.prepareStatement(sql);
	 	        ResultSet rs = ps.executeQuery();


	 	        while (rs.next()) {
	 	        	
	 	            Bed b = new Bed(rs.getString("idbed"));
	 	            bedsList.add(b);
	 	        }

	 	        ps.close();
	 	        rs.close();
	 	        return bedsList;
	 	    }
	    	 
	    	 public ObservableList<Ward> SelectWard() throws SQLException {
		 	        ObservableList<Ward> wardList = FXCollections.observableArrayList();
		 	        Connection connection = SQLite.dbConnector();
			        String sql =  "select * from bed";  
		 	        PreparedStatement ps = connection.prepareStatement(sql);
		 	        ResultSet rs = ps.executeQuery();


		 	        while (rs.next()) {
		 	        	
		 	        	Bed b = new Bed(rs.getString("idbed"));
		 	        	Room r = new Room(b, rs.getString("idroom"));
		 	            Ward w = new Ward(b, r, rs.getString("idward"));
		 	            wardList.add(w);
		 	        }

		 	        ps.close();
		 	        rs.close();
		 	        return wardList;
		 	    }
	    	
	    	 public ObservableList<Bed> SelectAll() throws SQLException {
		 	        ObservableList<Bed> bedList = FXCollections.observableArrayList();
		 	        Connection connection = SQLite.dbConnector();
			        String sql =  "select * from residents, overall where residents.FName = overall.FName"; 
		 	        PreparedStatement ps = connection.prepareStatement(sql);
		 	        ResultSet rs = ps.executeQuery();


		 	        while (rs.next()) {
		 	        	
		 	            Resident r = new Resident(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getString("userName"), rs.getString("type"), rs.getString("gender"));
		 	        	
		 	            Bed b = new Bed(r, rs.getString("idbed"));
		 	            bedList.add(b);
		 	        }

		 	        ps.close();
		 	        rs.close();
		 	        return bedList;
		 	    }
	    	 public ObservableList<Ward> SelectWards() throws SQLException {
		 	        ObservableList<Ward> wardList = FXCollections.observableArrayList();
		 	        Connection connection = SQLite.dbConnector();
			        String sql =  "select * from bed"; 
		 	        PreparedStatement ps = connection.prepareStatement(sql);
		 	        ResultSet rs = ps.executeQuery();


		 	        while (rs.next()) {
		 	        	
		 	            
		 	            Ward w = new Ward(rs.getString("idward"));
		 	            wardList.add(w);
		 	        }

		 	        ps.close();
		 	        rs.close();
		 	        return wardList;
		 	    }
	    	 public ObservableList<Room> SelectRoom() throws SQLException {
		 	        ObservableList<Room> roomList = FXCollections.observableArrayList();
		 	        Connection connection = SQLite.dbConnector();
			        String sql =  "select * from bed"; 
		 	        PreparedStatement ps = connection.prepareStatement(sql);
		 	        ResultSet rs = ps.executeQuery();


		 	        while (rs.next()) {
		 	        	
		 	            
		 	            Room r = new Room(rs.getString("idroom"));
		 	            roomList.add(r);
		 	        }

		 	        ps.close();
		 	        rs.close();
		 	        return roomList;
		 	    }
	    	 
	    	 public void removeResident() throws SQLException {
//	 	    	Bed selectedItem = tableView.getSelectionModel().getSelectedItem();
//	 	    	tableView.getItems().remove(selectedItem);
//	 	        
//	 	    	String sql1 = "DELETE FROM overall WHERE idbed= '"+selectedItem.getIdBed()+"'";
//	 	        Connection connection = SQLite.dbConnector();
//	 	        PreparedStatement ps = connection.prepareStatement(sql1); 
//	 	        ps.executeUpdate();
//	 	        ps.close();
	 	    }
	 


	 	    //Add Tab   --->   Evening shift
	 	    @FXML
	 	    void onClickW1R3B1(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	 


	 	    //Edit Tab   --->   Morning shift
	 	    @FXML
	 	    void onClickW1R3B2(MouseEvent event) { 
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Add Tab   --->   Morning shift
	 	    @FXML
	 	    void onClickW1R3B3(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Edit Tab   --->   Evening shift
	 	    @FXML
	 	    void onClickW1R3B4(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Add Tab   --->   Evening shift
	 	    @FXML
	 	    void onClickW1R4B1(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }



	 	    //Edit Tab   --->   Morning shift
	 	    @FXML
	 	    void onClickW1R4B2(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Add Tab   --->   Morning shift
	 	    @FXML
	 	    void onClickW1R4B3(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Edit Tab   --->   Evening shift
	 	    @FXML
	 	    void onClickW1R4B4(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Add Tab   --->   Evening shift
	 	    @FXML
	 	    void onClickW1R5B1(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }



	 	    //Edit Tab   --->   Morning shift
	 	    @FXML
	 	    void onClickW1R5B2(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Add Tab   --->   Morning shift
	 	    @FXML
	 	    void onClickW1R5B3(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Edit Tab   --->   Evening shift
	 	    @FXML
	 	    void onClickW1R5B4(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Add Tab   --->   Evening shift
	 	    @FXML
	 	    void onClickW1R6B1(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }


	 	    //Edit Tab   --->   Morning shift
	 	    @FXML
	 	    void onClickW1R6B2(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Add Tab   --->   Morning shift
	 	    @FXML
	 	    void onClickW1R6B3(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Edit Tab   --->   Evening shift
	 	    @FXML
	 	    void onClickW1R6B4(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Edit Tab   --->   Morning shift
	 	    @FXML
	 	    void onClickW2R1B1(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Add Tab   --->   Morning shift
	 	    @FXML
	 	    void onClickW2R2B1(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Edit Tab   --->   Evening shift
	 	    @FXML
	 	    void onClickW2R2B2(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Add Tab   --->   Evening shift
	 	    @FXML
	 	    void onClickW2R3B1(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	 


	 	    //Edit Tab   --->   Morning shift
	 	    @FXML
	 	    void onClickW2R3B2(MouseEvent event) { 
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Add Tab   --->   Morning shift
	 	    @FXML
	 	    void onClickW2R3B3(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Edit Tab   --->   Evening shift
	 	    @FXML
	 	    void onClickW2R3B4(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Add Tab   --->   Evening shift
	 	    @FXML
	 	    void onClickW2R4B1(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }



	 	    //Edit Tab   --->   Morning shift
	 	    @FXML
	 	    void onClickW2R4B2(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Add Tab   --->   Morning shift
	 	    @FXML
	 	    void onClickW2R4B3(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Edit Tab   --->   Evening shift
	 	    @FXML
	 	    void onClickW2R4B4(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Add Tab   --->   Evening shift
	 	    @FXML
	 	    void onClickW2R5B1(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }



	 	    //Edit Tab   --->   Morning shift
	 	    @FXML
	 	    void onClickW2R5B2(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Add Tab   --->   Morning shift
	 	    @FXML
	 	    void onClickW2R5B3(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Edit Tab   --->   Evening shift
	 	    @FXML
	 	    void onClickW2R5B4(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Add Tab   --->   Evening shift
	 	    @FXML
	 	    void onClickW2R6B1(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }


	 	    //Edit Tab   --->   Morning shift
	 	    @FXML
	 	    void onClickW2R6B2(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Add Tab   --->   Morning shift
	 	    @FXML
	 	    void onClickW2R6B3(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }

	 	    //Edit Tab   --->   Evening shift
	 	    @FXML
	 	    void onClickW2R6B4(MouseEvent event) {
	 	    	 w1r1b1.setStyle(style2);
		 	        for (Resident r : ResidentList) 
		 	            if (r.getGender().equals("MALE")) {
		 	                w1r1b1.setStyle(style);}
		 	        else {w1r1b1.setStyle(style3);
		 	        	
		 	        }
	 	    }
	 	    
	    	@FXML
		    void suser(MouseEvent event) throws IOException {
		        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/ShowUsers.fxml"));
		        Node node = (Node) event.getSource();
		        Stage stage = (Stage) node.getScene().getWindow();
		        stage.setScene(new Scene(root));
		        stage.setTitle("View Users");

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
		    @FXML
		    void bedmenu(MouseEvent event) throws IOException {
		        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/ShowUsers.fxml"));
		        Node node = (Node) event.getSource();
		        Stage stage = (Stage) node.getScene().getWindow();
		        stage.setScene(new Scene(root));

		    }
		    
	}


