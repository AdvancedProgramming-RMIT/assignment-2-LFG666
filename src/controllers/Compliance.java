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
import javafx.scene.control.Alert;
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
	public List<Roster> RalList = new ArrayList<Roster>();
	public List<Roster> RalList2 = new ArrayList<Roster>();
	public ArrayList<Roster> NP =  new ArrayList<Roster>();
	public List<String> List = new ArrayList<String>();
	public  ObservableList<Doctor> DoctorList = FXCollections.observableArrayList(); 
	public ArrayList<Integer> sunday = new ArrayList<Integer>();
	public ArrayList<Integer> monday = new ArrayList<Integer>();
	public ArrayList<Integer> tuesday = new ArrayList<Integer>();
	public ArrayList<Integer> wednesday = new ArrayList<Integer>();
	public ArrayList<Integer> thursday = new ArrayList<Integer>();
	public ArrayList<Integer> friday = new ArrayList<Integer>();
	public ArrayList<Integer> saturday = new ArrayList<Integer>();
	public ArrayList<String> sundayd = new ArrayList<String>();
	public ArrayList<String> mondayd = new ArrayList<String>();
	public ArrayList<String> tuesdayd = new ArrayList<String>();
	public ArrayList<String> wednesdayd = new ArrayList<String>();
	public ArrayList<String> thursdayd = new ArrayList<String>();
	public ArrayList<String> fridayd = new ArrayList<String>();
	public ArrayList<String> saturdayd = new ArrayList<String>();


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
	private Label doc;
	@FXML
	private Label nurse;
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
			RalList2 = null;
			RalList2 = SelectRoster2();


			for (Roster ws : SelectRoster2()) {
				if ((ws.getDay().equals("Sunday")))
					sundayd.add(ws.getDay());
				if ((ws.getDay().equals("Monday")))
					mondayd.add(ws.getDay());
				if ((ws.getDay().equals("Tuesday")))
					tuesdayd.add(ws.getDay());
				if ((ws.getDay().equals("Wednesday")))
					wednesdayd.add(ws.getDay());
				if ((ws.getDay().equals("Thursday")))
					thursdayd.add(ws.getDay());
				if ((ws.getDay().equals("Friday")))
					fridayd.add(ws.getDay());
				if ((ws.getDay().equals("Saturday")))
					saturdayd.add(ws.getDay());

			}}

		if(!sundayd.contains("Sunday")) {
			Compliance2.getItems().add("Sunday");  }
		if(!mondayd.contains("Monday")) {
			Compliance2.getItems().add("Monday"); } 
		if(!tuesdayd.contains("Tuesday")) {
			Compliance2.getItems().add("Tuesday");  }
		if(!wednesdayd.contains("Wednesday")) {
			Compliance2.getItems().add("Wednesday");  }
		if(!thursdayd.contains("Thursday")) {
			Compliance2.getItems().add("Thursday");  }
		if(!fridayd.contains("Friday")) {
			Compliance2.getItems().add("Friday"); } 
		if(!saturdayd.contains("Saturday")) {
			Compliance2.getItems().add("Saturday");  
		}

		for (Nurse t : NurseList) {
			rosterArrayList = null;
			rosterArrayList = SelectAllByFname(t.getFname());
			RosterList = null;
			RosterList = SelectAll();
			RalList = null;
			RalList = SelectRoster();
			NP = null;

			for (Roster ws : SelectRoster()) {
				if ((ws.getDay().equals("Sunday")) && (ws.getShift() == 1 || ws.getShift() ==2))
					sunday.add(ws.getShift());
				if ((ws.getDay().equals("Monday")) && (ws.getShift() == 1 || ws.getShift() ==2))
					monday.add(ws.getShift());
				if ((ws.getDay().equals("Tuesday")) && (ws.getShift() == 1 || ws.getShift() ==2))
					tuesday.add(ws.getShift());
				if ((ws.getDay().equals("Wednesday")) && (ws.getShift() == 1 || ws.getShift() ==2))
					wednesday.add(ws.getShift());
				if ((ws.getDay().equals("Thursday")) && (ws.getShift() == 1 || ws.getShift() ==2))
					thursday.add(ws.getShift());
				if ((ws.getDay().equals("Friday")) && (ws.getShift() == 1 || ws.getShift() ==2))
					friday.add(ws.getShift());
				if ((ws.getDay().equals("Saturday")) && (ws.getShift() == 1 || ws.getShift() ==2))
					saturday.add(ws.getShift());

			}

		}
		if(!sunday.contains(1) || !sunday.contains(2)) {
			Compliance1.getItems().add("Sunday");  }
		if(!monday.contains(1) || !monday.contains(2)) {
			Compliance1.getItems().add("Monday"); } 
		if(!tuesday.contains(1) || !tuesday.contains(2)) {
			Compliance1.getItems().add("Tuesday");  }
		if(!wednesday.contains(1) || !wednesday.contains(2)) {
			Compliance1.getItems().add("Wednesday");  }
		if(!thursday.contains(1) || !thursday.contains(2)) {
			Compliance1.getItems().add("Thursday");  }
		if(!friday.contains(1) || !friday.contains(2)) {
			Compliance1.getItems().add("Friday"); } 
		if(!saturday.contains(1) || !saturday.contains(2)) {
			Compliance1.getItems().add("Saturday");  
		}}


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
