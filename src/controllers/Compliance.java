package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Application.Constants;
import databaseSQL.SQLite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Data;
import model.Doctor;
import model.Nurse;
import model.Roster;
import model.Staff;

public class Compliance implements Initializable {
	
	public  ArrayList<Nurse> NurseList = new ArrayList<Nurse>();
	public ArrayList<Roster> RosterList = new ArrayList<Roster>();
	public ArrayList<Roster> RosList = new ArrayList<Roster>();
	public List<List<String>> RalList = new ArrayList<List<String>>();
	public ArrayList<Roster> NP =  new ArrayList<Roster>();
	public List<String> List = new ArrayList<String>();
	public  ObservableList<Doctor> DoctorList = FXCollections.observableArrayList(); 
	
	
	  @FXML
	    private TableView<Roster> nurseView;
	    @FXML
	    private TableColumn<Roster,Integer> shift;
	    @FXML
	    private TableColumn<Roster,String> day;
	    @FXML
	    private TableView<Roster> drView;
	    @FXML
	    private TableColumn<Roster,String> day2;
	    @FXML
	    private TableColumn<Roster,Integer> shift2;
	    

	    @FXML
	    private ListView<String> Compliance1;
	    @FXML
	    private ListView<String> Compliance2;
	   
	   
	    @FXML
	    private Button lll;
	    @FXML
		private Label rst2;
		@FXML
		private Label rst1;
		@FXML
		private Label Compliance;
		
		 String style = "-fx-background-color:  #a7a7a7";
		 String clicked = "-fx-background-color:  #59b7ff";
		
		
		ArrayList<Pane> PaneButtons = new ArrayList<Pane>();
	    ArrayList<Pane> NUrseList1 = new ArrayList<Pane>();
	    ArrayList<Pane> DoctorList1 = new ArrayList<Pane>();

	    ArrayList<Roster> personalRosters = new ArrayList<Roster>();
	    ObservableList<Roster> rosterArrayList = FXCollections.observableArrayList();
	    ObservableList<Roster> rosterArrayListd = FXCollections.observableArrayList();
	    public void JavafxChoiceFill() throws SQLException {
	        NurseList = selectAll();
	       
	    }
	    	    
	    public void ListViewFill() throws SQLException {

	        DoctorList = selectAll2();
	        for (Doctor t : DoctorList) {
	        	 rosterArrayListd = null;
		         rosterArrayListd = SelectAllByFnamed(t.getFname());
		         System.out.println(rosterArrayListd);
		         
		         for (Roster r : rosterArrayListd) {

			        if(!r.getDay().equals("Sunday")) {
		        	List.add("Sunday");
	            }

		        if(!r.getDay().equals("Monday"))  {
		        	List.add("Monday");
	            }

		        if(!r.getDay().equals("Tuesday"))  {
	            	List.add("Tuesday");
	            }

		        if(!r.getDay().equals("Wednesday"))  {
	            	List.add("Wednesday");
	            }

		        if(!r.getDay().equals("Thursday"))  {
	            	List.add("Thursday");
	            }

		        if(!r.getDay().equals("Friday"))  {
	            List.add("Friday");
	            }

		        if(!r.getDay().equals("Saturday"))  {
	            	List.add("Saturday");
	            }

		        }
		        List<String> listW = List.stream()
		        	     .distinct()
		        	     .collect(Collectors.toList());
		        Compliance2.getItems().addAll(listW);
	}

	        for (Nurse t : NurseList) {
	            rosterArrayList = null;
	            rosterArrayList = SelectAllByFname(t.getFname());
	            RosterList = null;
	            RosterList = SelectAll();
	            RalList = null;
	            RalList = loaddata2();
	            NP = null;}

}


	    @Override
	    public void initialize(URL url, ResourceBundle resourceBundle) {
	        try {
	            JavafxChoiceFill();
	            ListViewFill();
	            loadData();
	            loadData3();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }



	    }
	    
	    @FXML
	    public void loadData() {

	        shift.setCellValueFactory(new PropertyValueFactory<Roster,Integer>("Shift"));
	        day.setCellValueFactory(new PropertyValueFactory<Roster,String>("day"));
	        

	        try {
				nurseView.setItems(SelectRoster());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    @FXML
	    public void loadData3() {

	        day2.setCellValueFactory(new PropertyValueFactory<Roster,String>("day"));
	        shift2.setCellValueFactory(new PropertyValueFactory<Roster,Integer>("Shift"));
	        

	        try {
				drView.setItems(SelectRoster2());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		
	    public ArrayList<Nurse> selectAll() throws SQLException {
	        ArrayList<Nurse> list = new ArrayList<Nurse>();
	        System.out.println(list);

	        String sql = "select * from users where type = 'Nurse'";
	        Connection connection = SQLite.dbConnector();
	        PreparedStatement ps = connection.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {

		        Nurse d = new Nurse(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getString("userName"), rs.getString("type"), rs.getString("gender"));
		        d.setId(rs.getInt("id"));
	            d.setFname(rs.getString("FName"));
	            d.setLname(rs.getString("LName"));
	            d.setUsername(rs.getString("username"));
	            d.setType(rs.getString("type"));
	            d.setGender(rs.getString("gender"));
	            list.add(d);
	        }

	        ps.close();
	        rs.close();

	        return list;
	    }

	   
	    
		 public ArrayList<Roster> SelectAll() throws SQLException {
		        ArrayList<Roster> rosterArrayList = new ArrayList<Roster>();
		        Connection connection = SQLite.dbConnector();
		        String sql = "select * from users, nurroster where type = 'Nurse'";
		        PreparedStatement ps = connection.prepareStatement(sql);
		        ResultSet rs = ps.executeQuery();


		        while (rs.next()) {
		        	
		            Nurse d = new Nurse(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getString("userName"), rs.getString("type"), rs.getString("gender"));
		        	
		            Roster r = new Roster(d, rs.getInt("shift"), rs.getString("day"));
		            d.setId(rs.getInt("id"));
		            d.setFname(rs.getString("FName"));
		            d.setLname(rs.getString("LName"));
		            d.setUsername(rs.getString("username"));
		            d.setType(rs.getString("type"));
		            d.setGender(rs.getString("gender"));
		            r.setShift(rs.getInt("shift"));
		            r.setDay(rs.getString("day"));
		            
		            rosterArrayList.add(r);
		        }

		        ps.close();
		        rs.close();
		        return rosterArrayList;
		    }
		 
		 public ObservableList<Roster> SelectAllByFname(String Fname) throws SQLException {

		        ObservableList<Roster> rosterArrayList = FXCollections.observableArrayList();
		        String sql = "select * from nurroster ORDER BY FIELD(day, 'Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday')";
		        Connection connection = SQLite.dbConnector();
		        PreparedStatement ps = connection.prepareStatement(sql);
		        ResultSet rs = ps.executeQuery();

		        while (rs.next()) {
		            if (Fname.equals(rs.getString("FName"))) {
		                Roster r = new Roster(rs.getInt("shift"), rs.getString("day")); 
		                r.setShift(rs.getInt("shift"));
			            r.setDay(rs.getString("day"));
		                rosterArrayList.add(r);
		            }
		        }

		        ps.close();
		        rs.close();
		        return rosterArrayList;
		    }
		 
		 public List<List<String>> loaddata2() {
			 List<List<String>> ral = new ArrayList<List<String>>(); 
			 List<String> ro1 = new ArrayList<String>();
			 List<String> ro2 = new ArrayList<String>();
			 List<String> ro3 = new ArrayList<String>();
			 List<String> ro4 = new ArrayList<String>();
			 List<String> ro5 = new ArrayList<String>();
			 List<String> ro6 = new ArrayList<String>();
			 List<String> ro7 = new ArrayList<String>();
			 List<String> ro8 = new ArrayList<String>();
			 List<String> ro9 = new ArrayList<String>();
			 List<String> ro10 = new ArrayList<String>();
			 List<String> ro11 = new ArrayList<String>();
			 List<String> ro12 = new ArrayList<String>();
			 List<String> ro13 = new ArrayList<String>();
			 List<String> ro14 = new ArrayList<String>();
			 ral.add(new ArrayList<String>());
			 ro1.add("1");
			 ro1.add("Sunday");
			 ro2.add("2");
			 ro2.add("Sunday");
			 ro3.add("1");
			 ro3.add("Monday");
			 ro4.add("2");
			 ro4.add("Monday");
			 ro5.add("1");
			 ro5.add("Tuesday");
			 ro6.add("2");
			 ro6.add("Tuesday");			
			 ro7.add("1");
			 ro7.add("Wednesday");
			 ro8.add("2");
			 ro8.add("Wednesday");
			 ro9.add("1");
			 ro9.add("Thursday");
			 ro10.add("2");
			 ro10.add("Thursday");
			 ro11.add("1");
			 ro11.add("Friday");
			 ro12.add("2");
			 ro12.add("Friday");	
			 ro13.add("1");
			 ro13.add("Saturday");
			 ro14.add("2");
			 ro14.add("Saturday");	
			 
			 
			 ral.add(ro1);
			 ral.add(ro2);
			 ral.add(ro3);
			 ral.add(ro4);
			 ral.add(ro5);
			 ral.add(ro6);
			 ral.add(ro7);
			 ral.add(ro8);
			 ral.add(ro9);
			 ral.add(ro10);
			 ral.add(ro11);
			 ral.add(ro12);
			 ral.add(ro13);
			 ral.add(ro14);
			 
			 return ral;
			 
			 
		 }
		 
		 public ObservableList<Roster> SelectAllByFnamed(String Fname) throws SQLException {

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
		 
		 public ObservableList<Doctor> selectAll2() throws SQLException {
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


		 public ObservableList<Roster> SelectRoster() throws SQLException {

		        ObservableList<Roster> rosArrayList = FXCollections.observableArrayList();
		        String sql = "select shift, day from nurroster ORDER BY FIELD(day, 'Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday')";
		        Connection connection = SQLite.dbConnector();
		        PreparedStatement ps = connection.prepareStatement(sql);
		        ResultSet rs = ps.executeQuery();

		        while (rs.next()) {
		                Roster r = new Roster(rs.getInt("shift"), rs.getString("day"));
		                r.setShift(rs.getInt("shift"));
			            r.setDay(rs.getString("day"));
		                rosArrayList.add(r);
		            
		        }

		        ps.close();
		        rs.close();
		        return rosArrayList;
		    }

		 public ObservableList<Roster> SelectRoster2() throws SQLException {

		        ObservableList<Roster> rosArrayList = FXCollections.observableArrayList();
		        String sql = "select shift, day from roster ORDER BY FIELD(day, 'Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday')";
		        Connection connection = SQLite.dbConnector();
		        PreparedStatement ps = connection.prepareStatement(sql);
		        ResultSet rs = ps.executeQuery();

		        while (rs.next()) {
		                Roster r = new Roster(rs.getInt("shift"), rs.getString("day"));
		                r.setShift(rs.getInt("shift"));
			            r.setDay(rs.getString("day"));
		                rosArrayList.add(r);
		            
		        }

		        ps.close();
		        rs.close();
		        return rosArrayList;
		    }
		
	    @FXML
	    void adhome(MouseEvent event) throws IOException {
	        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Admin.fxml"));
	        Node node = (Node) event.getSource();
	        Stage stage = (Stage) node.getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.setTitle("Admin Home Page");

	    }		
		
		
		
		
		
		
}
