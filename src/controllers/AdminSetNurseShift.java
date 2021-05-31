package controllers;

import model.Nurse;
import model.Roster;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Application.Constants;
import databaseSQL.SQLite;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdminSetNurseShift implements Initializable{

	public  ArrayList<Nurse> NurseList = new ArrayList<Nurse>();
	

	public void insertRoster(String Fname, String Lname, ArrayList<Roster> rosterArrayList) throws SQLException{
        Connection connection = SQLite.dbConnector(); 
        for(Roster ro : rosterArrayList)  {
        	String St = "INSERT INTO nurroster(FName, LName, shift, day ) VALUES (?, ?, ?, ? )";
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
        	String St = "INSERT INTO nurroster(FName, LName, shift, day ) VALUES (?, ?, ?, ? )";
        	PreparedStatement ps = connection.prepareStatement(St);
        	ps.setString(1, Fname);
        	ps.setString(2, Lname);
        	ps.setInt(3, roster.getShift());
        	ps.setString(4,  roster.getDay());
        	int layout = ps.executeUpdate();
        	ps.close();
        	return layout;
        
  
	}
        
	 public ArrayList<Roster> SelectAll() throws SQLException {
	        ArrayList<Roster> rosterArrayList = new ArrayList<Roster>();
	        Connection connection = SQLite.dbConnector();
	        String sql = "select * from users where users.FName = Nurse.FName";
	        PreparedStatement ps = connection.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();


	        while (rs.next()) {
	        	
	            Nurse d = new Nurse(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getString("userName"), rs.getString("type"), rs.getString("gender"));
	        	
	            Roster r = new Roster(d, rs.getInt("shift"), rs.getString("day"));
	            rosterArrayList.add(r);
	        }

	        ps.close();
	        rs.close();
	        return rosterArrayList;
	    }
	 public void updateRoster(String Fname, Roster ro) throws SQLException {
	        String sql = "update nurroster set shift=? where FName=? and day=?";
	        Connection connection = SQLite.dbConnector();
	        PreparedStatement ps = connection.prepareStatement(sql);
	        ps.setInt(1, ro.getShift());
	        ps.setString(2, Fname);
	        ps.setString(3, ro.getDay());
	        ps.executeUpdate();
	        ps.close();
	    }
	 public ArrayList<Roster> SelectAllByFname(String Fname) throws SQLException {

	        ArrayList<Roster> rosterArrayList = new ArrayList<Roster>();
	        String sql = "select * from nurroster";
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
	 
	    public void removeRosterByNurse(Nurse s) throws SQLException {
	        String sql1 = "DELETE FROM nurroster WHERE FName=? ";
	        Connection connection = SQLite.dbConnector();
	        PreparedStatement ps = connection.prepareStatement(sql1);
	        ps.setString(1, s.getFname());
	        ps.executeUpdate();
	        ps.close();
	    }

	    public void removeRosterByDay(String day) throws SQLException {
	        String sql1 = "DELETE FROM nurroster WHERE day=? ";
	        Connection connection = SQLite.dbConnector();
	        PreparedStatement ps = connection.prepareStatement(sql1);
	        ps.setString(1, day);
	        ps.executeUpdate();
	        ps.close();
	    }

	    public void removeRosterByShift(String day, int shift) throws SQLException {
	        String sql1 = "DELETE FROM nurroster WHERE day=? and shift=? ";
	        Connection connection = SQLite.dbConnector();
	        PreparedStatement ps = connection.prepareStatement(sql1);
	        ps.setString(1, day);
	        ps.setInt(2, shift);
	        ps.executeUpdate();
	        ps.close();
	    }

	    public void removeAll() throws SQLException {
	        String sql1 = "DELETE FROM nurroster ";
	        Connection connection = SQLite.dbConnector();
	        PreparedStatement ps = connection.prepareStatement(sql1);
	        ps.executeUpdate();
	        ps.close();
	    }
	    @FXML
	    private Pane parent;
	    @FXML
	    private ListView<String> ListViewSunday1;
	    @FXML
	    private ListView<String> ListViewSunday2;
	    @FXML
	    private ListView<String> ListViewMonday1;
	    @FXML
	    private ListView<String> ListViewMonday2;
	    @FXML
	    private ListView<String> ListViewTuesday1;
	    @FXML
	    private ListView<String> ListViewTuesday2;
	    @FXML
	    private ListView<String> ListViewWednesday1;
	    @FXML
	    private ListView<String> ListViewWednesday2;
	    @FXML
	    private ListView<String> ListViewThursday1;
	    @FXML
	    private ListView<String> ListViewThursday2;
	    @FXML
	    private ListView<String> ListViewFriday1;
	    @FXML
	    private ListView<String> ListViewFriday2;
	    @FXML
	    private ListView<String> ListViewSaturday1;
	    @FXML
	    private ListView<String> ListViewSaturday2;
	    @FXML
	    private ChoiceBox<String> ChoiceAdd;
	    @FXML
	    private Pane Sunday11;
	    @FXML
	    private Pane Sunday21;
	    @FXML
	    private Pane Monday11;
	    @FXML
	    private Pane Monday21;
	    @FXML
	    private Pane Tuesday11;
	    @FXML
	    private Pane Tuesday21;
	    @FXML
	    private Pane Wednesday11;
	    @FXML
	    private Pane Wednesday21;
	    @FXML
	    private Pane Thursday11;
	    @FXML
	    private Pane Thursday21;
	    @FXML
	    private Pane Friday11;
	    @FXML
	    private Pane Friday21;
	    @FXML
	    private Pane Saturday11;
	    @FXML
	    private Pane Saturday21;
	    @FXML
	    private ChoiceBox<String> ChoiceEdit;
	    @FXML
	    private Pane Sunday1;
	    @FXML
	    private Pane Sunday2;
	    @FXML
	    private Pane Monday1;
	    @FXML
	    private Pane Monday2;
	    @FXML
	    private Pane Tuesday1;
	    @FXML
	    private Pane Tuesday2;
	    @FXML
	    private Pane Wednesday1;
	    @FXML
	    private Pane Wednesday2;
	    @FXML
	    private Pane Thursday1;
	    @FXML
	    private Pane Thursday2;
	    @FXML
	    private Pane Friday1;
	    @FXML
	    private Pane Friday2;
	    @FXML
	    private Pane Saturday1;
	    @FXML
	    private Pane Saturday2;
	    @FXML
	    private Button BtnBack;
	    @FXML
	    private Button ButtonDeleteSpNurse;
	    @FXML
	    private Button ButtonDeleteSpDay;
	    @FXML
	    private Button ButtonDeleteAll;
	    @FXML
	    private Button ButtonDeleteSpShift;
	    @FXML
	    private ChoiceBox<String> ChoiceDeleteSpNurse;
	    @FXML
	    private ChoiceBox<String> ChoiceDeleteSpDay;
	    @FXML
	    private ChoiceBox<String> ChoiceDeleteSpShift;
	    @FXML
	    private Button BtnDoc;
	    @FXML
	    private Button BtnLeave;

	    String style = "-fx-background-color:  #a7a7a7";
	    String clicked = "-fx-background-color:  #59b7ff";

	    ArrayList<Pane> PaneButtons = new ArrayList<Pane>();
	    ArrayList<Pane> SundayList1 = new ArrayList<Pane>();
	    ArrayList<Pane> SundayList2 = new ArrayList<Pane>();
	    ArrayList<Pane> MondayList1 = new ArrayList<Pane>();
	    ArrayList<Pane> MondayList2 = new ArrayList<Pane>();
	    ArrayList<Pane> TuesdayList1 = new ArrayList<Pane>();
	    ArrayList<Pane> TuesdayList2 = new ArrayList<Pane>();
	    ArrayList<Pane> WedenesdayList1 = new ArrayList<Pane>();
	    ArrayList<Pane> WedenesdayList2 = new ArrayList<Pane>();
	    ArrayList<Pane> ThursdayList1 = new ArrayList<Pane>();
	    ArrayList<Pane> ThursdayList2 = new ArrayList<Pane>();
	    ArrayList<Pane> FridayList1 = new ArrayList<Pane>();
	    ArrayList<Pane> FridayList2 = new ArrayList<Pane>();
	    ArrayList<Pane> SaturdayList1 = new ArrayList<Pane>();
	    ArrayList<Pane> SaturdayList2 = new ArrayList<Pane>();

	    ArrayList<Roster> personalRosters = new ArrayList<Roster>();
	    ArrayList<Roster> rosterArrayList = new ArrayList<Roster>();

	    @FXML
	    void onClickBtnBack(ActionEvent event) throws IOException {
	    	Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Admin.fxml"));
	        Node node = (Node) event.getSource();
	        Stage stage = (Stage) node.getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.setTitle("Admin Control");
	    }

	    @FXML
	    void onClickBtnAdd(ActionEvent event) throws SQLException {
	        personalRosters.clear();

	        boolean addFlag = true;

	        for (Pane p : SundayList2)
	            if (p.getStyle().equals(clicked))
	                personalRosters.add(new Roster((SundayList2.indexOf(p) + 1), "Sunday"));
	        for (Pane p : MondayList2)
	            if (p.getStyle().equals(clicked))
	                personalRosters.add(new Roster((MondayList2.indexOf(p) + 1), "Monday"));
	        for (Pane p : TuesdayList2)
	            if (p.getStyle().equals(clicked))
	                personalRosters.add(new Roster((TuesdayList2.indexOf(p) + 1), "Tuesday"));
	        for (Pane p : WedenesdayList2)
	            if (p.getStyle().equals(clicked))
	                personalRosters.add(new Roster((WedenesdayList2.indexOf(p) + 1), "Wednesday"));
	        for (Pane p : ThursdayList2)
	            if (p.getStyle().equals(clicked))
	                personalRosters.add(new Roster((ThursdayList2.indexOf(p) + 1), "Thursday"));
	        for (Pane p : FridayList2)
	            if (p.getStyle().equals(clicked))
	                personalRosters.add(new Roster((FridayList2.indexOf(p) + 1), "Friday"));
	        for (Pane p : SaturdayList2)
	            if (p.getStyle().equals(clicked))
	                personalRosters.add(new Roster((SaturdayList2.indexOf(p) + 1), "Saturday"));
	        rosterArrayList = null;

	        for (Nurse t : NurseList) {
	            if (ChoiceAdd.getValue().equals(t.getFname())) {
	                rosterArrayList = SelectAllByFname(t.getFname());
	                if (rosterArrayList.size() == 0) {
	                    insertRoster(t.getFname(), t.getLname(), personalRosters);
	                } else {
	                    for (Roster ws : personalRosters) {
	                        addFlag = true;
	                        for (Roster wsal : rosterArrayList) {
	                            if ((ws.getDay().equals(wsal.getDay())))
	                                addFlag = false;
	                        }
	                        if (addFlag == true)
	                            insertSpecificRoster(t.getFname(), t.getLname(), ws);
	                    }
	                }
	            }
	        }
	        ListViewFill();
	    }

	    @FXML
	    void onClickBtnClear(ActionEvent event) {
	        for (Pane p : PaneButtons)
	            p.setStyle(style);
	    }

	    @FXML
	    void onClickBtnSave(ActionEvent event) throws SQLException {
	        personalRosters.clear();

	        for (Pane p : SundayList1)
	            if (p.getStyle().equals(clicked))
	                personalRosters.add(new Roster((SundayList1.indexOf(p) + 1), "Sunday"));
	        for (Pane p : MondayList1)
	            if (p.getStyle().equals(clicked))
	                personalRosters.add(new Roster((MondayList1.indexOf(p) + 1), "Monday"));
	        for (Pane p : TuesdayList1)
	            if (p.getStyle().equals(clicked))
	                personalRosters.add(new Roster((TuesdayList1.indexOf(p) + 1), "Tuesday"));
	        for (Pane p : WedenesdayList1)
	            if (p.getStyle().equals(clicked))
	                personalRosters.add(new Roster((WedenesdayList1.indexOf(p) + 1), "Wednesday"));
	        for (Pane p : ThursdayList1)
	            if (p.getStyle().equals(clicked))
	                personalRosters.add(new Roster((ThursdayList1.indexOf(p) + 1), "Thursday"));
	        for (Pane p : FridayList1)
	            if (p.getStyle().equals(clicked))
	                personalRosters.add(new Roster((FridayList1.indexOf(p) + 1), "Friday"));
	        for (Pane p : SaturdayList1)
	            if (p.getStyle().equals(clicked))
	                personalRosters.add(new Roster((SaturdayList1.indexOf(p) + 1), "Saturday"));

	        rosterArrayList = null;

	        for (Nurse t : NurseList) {
	            if (ChoiceEdit.getValue().equals(t.getFname())) {
	                rosterArrayList = SelectAllByFname(t.getFname());
	                for (Roster ws : personalRosters)
	                    updateRoster(t.getFname(), ws);
	            }
	        }

	        ListViewFill();
	    }

	    @FXML
	    void onClickDeleteSpNurse(ActionEvent event) throws SQLException {
	        for (Nurse t : NurseList) {
	            if (ChoiceDeleteSpNurse.getValue().equals(t.getFname()))
	                removeRosterByNurse(t);
	        }
	        ListViewFill();
	    }

	    @FXML
	    void onClickDeleteSpDay(ActionEvent event) throws SQLException {
	        String day = ChoiceDeleteSpDay.getValue();
	        removeRosterByDay(day);
	        ListViewFill();
	    }

	    @FXML
	    void onClickDeleteSpShift(ActionEvent event) throws SQLException {
	        String shift = ChoiceDeleteSpShift.getValue();
	        String[] splited = shift.split(" ");
	        removeRosterByShift(splited[0], Integer.parseInt(splited[1]));
	        ListViewFill();
	    }

	    @FXML
	    void onClickDeleteAll(ActionEvent event) throws SQLException {
	        removeAll();
	        ListViewFill();
	    }

	    @FXML
	    void Nurse(ActionEvent event) throws IOException, SQLException {
	    
	    }

	    @FXML
	    void Leave(ActionEvent event) throws IOException, SQLException {
	    	Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Landing.fxml"));
	        Node node = (Node) event.getSource();
	        Stage stage = (Stage) node.getScene().getWindow();
	        stage.setScene(new Scene(root));

	    }

	    //Edit Tab   --->   Morning shift
	    @FXML
	    void onClickSunday1(MouseEvent event) {
	        Sunday1.setStyle(clicked);
	        for (Pane p : SundayList1)
	            if (!p.equals(Sunday1))
	                p.setStyle(style);
	    }

	    //Add Tab   --->   Morning shift
	    @FXML
	    void onClickSunday11(MouseEvent event) {
	        Sunday11.setStyle(clicked);
	        for (Pane p : SundayList2)
	            if (!p.equals(Sunday11))
	                p.setStyle(style);
	    }

	    //Edit Tab   --->   Evening shift
	    @FXML
	    void onClickSunday2(MouseEvent event) {
	        Sunday2.setStyle(clicked);
	        for (Pane p : SundayList1)
	            if (!p.equals(Sunday2))
	                p.setStyle(style);
	    }

	    //Add Tab   --->   Evening shift
	    @FXML
	    void onClickSunday21(MouseEvent event) {
	        Sunday21.setStyle(clicked);
	        for (Pane p : SundayList2)
	            if (!p.equals(Sunday21))
	                p.setStyle(style);
	    }

	 


	    //Edit Tab   --->   Morning shift
	    @FXML
	    void onClickMonday1(MouseEvent event) { 
	        Monday1.setStyle(clicked);
	        for (Pane p : MondayList1)
	            if (!p.equals(Monday1))
	                p.setStyle(style);
	    }

	    //Add Tab   --->   Morning shift
	    @FXML
	    void onClickMonday11(MouseEvent event) {
	        Monday11.setStyle(clicked);
	        for (Pane p : MondayList2)
	            if (!p.equals(Monday11))
	                p.setStyle(style);
	    }

	    //Edit Tab   --->   Evening shift
	    @FXML
	    void onClickMonday2(MouseEvent event) {
	        Monday2.setStyle(clicked);
	        for (Pane p : MondayList1)
	            if (!p.equals(Monday2))
	                p.setStyle(style);
	    }

	    //Add Tab   --->   Evening shift
	    @FXML
	    void onClickMonday21(MouseEvent event) {
	        Monday21.setStyle(clicked);
	        for (Pane p : MondayList2)
	            if (!p.equals(Monday21))
	                p.setStyle(style);
	    }



	    //Edit Tab   --->   Morning shift
	    @FXML
	    void onClickTuesday1(MouseEvent event) {
	        Tuesday1.setStyle(clicked);
	        for (Pane p : TuesdayList1)
	            if (!p.equals(Tuesday1))
	                p.setStyle(style);
	    }

	    //Add Tab   --->   Morning shift
	    @FXML
	    void onClickTuesday11(MouseEvent event) {
	        Tuesday11.setStyle(clicked);
	        for (Pane p : TuesdayList2)
	            if (!p.equals(Tuesday11))
	                p.setStyle(style);
	    }

	    //Edit Tab   --->   Evening shift
	    @FXML
	    void onClickTuesday2(MouseEvent event) {
	        Tuesday2.setStyle(clicked);
	        for (Pane p : TuesdayList1)
	            if (!p.equals(Tuesday2))
	                p.setStyle(style);
	    }

	    //Add Tab   --->   Evening shift
	    @FXML
	    void onClickTuesday21(MouseEvent event) {
	        Tuesday21.setStyle(clicked);
	        for (Pane p : TuesdayList2)
	            if (!p.equals(Tuesday21))
	                p.setStyle(style);
	    }



	    //Edit Tab   --->   Morning shift
	    @FXML
	    void onClickWednesday1(MouseEvent event) {
	        Wednesday1.setStyle(clicked);
	        for (Pane p : WedenesdayList1)
	            if (!p.equals(Wednesday1))
	                p.setStyle(style);
	    }

	    //Add Tab   --->   Morning shift
	    @FXML
	    void onClickWednesday11(MouseEvent event) {
	        Wednesday11.setStyle(clicked);
	        for (Pane p : WedenesdayList2)
	            if (!p.equals(Wednesday11))
	                p.setStyle(style);
	    }

	    //Edit Tab   --->   Evening shift
	    @FXML
	    void onClickWednesday2(MouseEvent event) {
	        Wednesday2.setStyle(clicked);
	        for (Pane p : WedenesdayList1)
	            if (!p.equals(Wednesday2))
	                p.setStyle(style);
	    }

	    //Add Tab   --->   Evening shift
	    @FXML
	    void onClickWednesday21(MouseEvent event) {
	        Wednesday21.setStyle(clicked);
	        for (Pane p : WedenesdayList2)
	            if (!p.equals(Wednesday21))
	                p.setStyle(style);
	    }


	    //Edit Tab   --->   Morning shift
	    @FXML
	    void onClickThursday1(MouseEvent event) {
	        Thursday1.setStyle(clicked);
	        for (Pane p : ThursdayList1)
	            if (!p.equals(Thursday1))
	                p.setStyle(style);
	    }

	    //Add Tab   --->   Morning shift
	    @FXML
	    void onClickThursday11(MouseEvent event) {
	        Thursday11.setStyle(clicked);
	        for (Pane p : ThursdayList2)
	            if (!p.equals(Thursday11))
	                p.setStyle(style);
	    }

	    //Edit Tab   --->   Evening shift
	    @FXML
	    void onClickThursday2(MouseEvent event) {
	        Thursday2.setStyle(clicked);
	        for (Pane p : ThursdayList1)
	            if (!p.equals(Thursday2))
	                p.setStyle(style);
	    }

	    //Add Tab   --->   Evening shift
	    @FXML
	    void onClickThursday21(MouseEvent event) {
	        Thursday21.setStyle(clicked);
	        for (Pane p : ThursdayList2)
	            if (!p.equals(Thursday21))
	                p.setStyle(style);
	    }

	

	    //Edit Tab   --->   Morning shift
	    @FXML
	    void onClickFriday1(MouseEvent event) {
	        Friday1.setStyle(clicked);
	        for (Pane p : FridayList1)
	            if (!p.equals(Friday1))
	                p.setStyle(style);
	    }

	    //Add Tab   --->   Morning shift
	    @FXML
	    void onClickFriday11(MouseEvent event) {
	        Friday11.setStyle(clicked);
	        for (Pane p : FridayList2)
	            if (!p.equals(Friday11))
	                p.setStyle(style);
	    }

	    //Edit Tab   --->   Evening shift
	    @FXML
	    void onClickFriday2(MouseEvent event) {
	        Friday2.setStyle(clicked);
	        for (Pane p : FridayList1)
	            if (!p.equals(Friday2))
	                p.setStyle(style);
	    }

	    //Add Tab   --->   Evening shift
	    @FXML
	    void onClickFriday21(MouseEvent event) {
	        Friday21.setStyle(clicked);
	        for (Pane p : FridayList2)
	            if (!p.equals(Friday21))
	                p.setStyle(style);
	    }

	

	    //Edit Tab   --->   Morning shift
	    @FXML
	    void onClickSaturday1(MouseEvent event) {
	        Saturday1.setStyle(clicked);
	        for (Pane p : SaturdayList1)
	            if (!p.equals(Saturday1))
	                p.setStyle(style);
	    }

	    //Add Tab   --->   Morning shift
	    @FXML
	    void onClickSaturday11(MouseEvent event) {
	        Saturday11.setStyle(clicked);
	        for (Pane p : SaturdayList2)
	            if (!p.equals(Saturday11))
	                p.setStyle(style);
	    }

	    //Edit Tab   --->   Evening shift
	    @FXML
	    void onClickSaturday2(MouseEvent event) {
	        Saturday2.setStyle(clicked);
	        for (Pane p : SaturdayList1)
	            if (!p.equals(Saturday2))
	                p.setStyle(style);
	    }

	    //Add Tab   --->   Evening shift
	    @FXML
	    void onClickSaturday21(MouseEvent event) {
	        Saturday21.setStyle(clicked);
	        for (Pane p : SaturdayList2)
	            if (!p.equals(Saturday21))
	                p.setStyle(style);
	    }



	    public void ListViewFill() throws SQLException {
	        ListViewSunday1.getItems().clear();
	        ListViewSunday2.getItems().clear();
	        ListViewMonday1.getItems().clear();
	        ListViewMonday2.getItems().clear();
	        ListViewTuesday1.getItems().clear();
	        ListViewTuesday2.getItems().clear();
	        ListViewWednesday1.getItems().clear();
	        ListViewWednesday2.getItems().clear();
	        ListViewThursday1.getItems().clear();
	        ListViewThursday2.getItems().clear();
	        ListViewFriday1.getItems().clear();
	        ListViewFriday2.getItems().clear();
	        ListViewSaturday1.getItems().clear();
	        ListViewSaturday2.getItems().clear();

	        for (Nurse t : NurseList) {
	            rosterArrayList = null;
	            rosterArrayList = SelectAllByFname(t.getFname());
	            for (Roster ws : rosterArrayList) {
	                if (ws.getDay().equals("Sunday") && ws.getShift() == 1)
	                    ListViewSunday1.getItems().add(t.getFname());
	                if (ws.getDay().equals("Sunday") && ws.getShift() == 2)
	                    ListViewSunday2.getItems().add(t.getFname());    
	                if (ws.getDay().equals("Monday") && ws.getShift() == 1)
	                    ListViewMonday1.getItems().add(t.getFname());
	                if (ws.getDay().equals("Monday") && ws.getShift() == 2)
	                    ListViewMonday2.getItems().add(t.getFname());
	                if (ws.getDay().equals("Tuesday") && ws.getShift() == 1)
	                    ListViewTuesday1.getItems().add(t.getFname());
	                if (ws.getDay().equals("Tuesday") && ws.getShift() == 2)
	                    ListViewTuesday2.getItems().add(t.getFname());
	                if (ws.getDay().equals("Wednesday") && ws.getShift() == 1)
	                    ListViewWednesday1.getItems().add(t.getFname());
	                if (ws.getDay().equals("Wednesday") && ws.getShift() == 2)
	                    ListViewWednesday2.getItems().add(t.getFname());
	                if (ws.getDay().equals("Thursday") && ws.getShift() == 1)
	                    ListViewThursday1.getItems().add(t.getFname());
	                if (ws.getDay().equals("Thursday") && ws.getShift() == 2)
	                    ListViewThursday2.getItems().add(t.getFname());
	                if (ws.getDay().equals("Friday") && ws.getShift() == 1)
	                    ListViewFriday1.getItems().add(t.getFname());
	                if (ws.getDay().equals("Friday") && ws.getShift() == 2)
	                    ListViewFriday2.getItems().add(t.getFname());
	                if (ws.getDay().equals("Saturday") && ws.getShift() == 1)
	                    ListViewSaturday1.getItems().add(t.getFname());
	                if (ws.getDay().equals("Saturday") && ws.getShift() == 2)
	                    ListViewSaturday2.getItems().add(t.getFname());

	            }
	        }
	    }

	    @Override
	    public void initialize(URL url, ResourceBundle resourceBundle) {
	        try {
	            JavafxChoiceFill();
	            ListViewFill();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        //--------------------------------
	        SundayList1.add(Sunday1);
	        SundayList1.add(Sunday2);

	        SundayList2.add(Sunday11);
	        SundayList2.add(Sunday21);

	        //--------------------------------
	        MondayList1.add(Monday1);
	        MondayList1.add(Monday2);


	        MondayList2.add(Monday11);
	        MondayList2.add(Monday21);

	        //--------------------------------
	        TuesdayList1.add(Tuesday1);
	        TuesdayList1.add(Tuesday2);


	        TuesdayList2.add(Tuesday11);
	        TuesdayList2.add(Tuesday21);
	        //-------------------------
	        WedenesdayList1.add(Wednesday1);
	        WedenesdayList1.add(Wednesday2);

	        WedenesdayList2.add(Wednesday11);
	        WedenesdayList2.add(Wednesday21);
	        //-------------------------
	        ThursdayList1.add(Thursday1);
	        ThursdayList1.add(Thursday2);

	        ThursdayList2.add(Thursday11);
	        ThursdayList2.add(Thursday21);
	        //-------------------------
	        FridayList1.add(Friday1);
	        FridayList1.add(Friday2);

	        FridayList2.add(Friday11);
	        FridayList2.add(Friday21);
	        //-------------------------
	        SaturdayList1.add(Saturday1);
	        SaturdayList1.add(Saturday2);

	        SaturdayList2.add(Saturday11);
	        SaturdayList2.add(Saturday21);
	        //-------------------------
	        PaneButtons.add(Tuesday11);
	        PaneButtons.add(Tuesday21);

	        //--------------------------------
	        PaneButtons.add(Sunday1);
	        PaneButtons.add(Sunday11);
	        PaneButtons.add(Sunday2);
	        PaneButtons.add(Sunday21);

	        PaneButtons.add(Monday1);
	        PaneButtons.add(Monday11);
	        PaneButtons.add(Monday2);
	        PaneButtons.add(Monday21);

	        PaneButtons.add(Tuesday1);
	        PaneButtons.add(Tuesday11);
	        PaneButtons.add(Tuesday2);
	        PaneButtons.add(Tuesday21);

	        PaneButtons.add(Wednesday1);
	        PaneButtons.add(Wednesday11);
	        PaneButtons.add(Wednesday2);
	        PaneButtons.add(Wednesday21);

	        PaneButtons.add(Thursday1);
	        PaneButtons.add(Thursday11);
	        PaneButtons.add(Thursday2);
	        PaneButtons.add(Thursday21);

	        PaneButtons.add(Friday1);
	        PaneButtons.add(Friday11);
	        PaneButtons.add(Friday2);
	        PaneButtons.add(Friday21);

	        PaneButtons.add(Saturday1);
	        PaneButtons.add(Saturday11);
	        PaneButtons.add(Saturday2);
	        PaneButtons.add(Saturday21);

	        //-----------------------------
	        for (Pane p : PaneButtons)
	            p.setStyle(style);


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
	            list.add(d);
	        }

	        ps.close();
	        rs.close();

	        return list;
	    }

	

	   
	    public void JavafxChoiceFill() throws SQLException {
	        NurseList = selectAll();
	        ChoiceAdd.getItems().clear();
	        ChoiceEdit.getItems().clear();
	        ChoiceDeleteSpDay.getItems().clear();
	        ChoiceDeleteSpNurse.getItems().clear();
	        ChoiceDeleteSpShift.getItems().clear();
	        for (Nurse t : NurseList) {
	            ChoiceAdd.getItems().add(t.getFname());
	            ChoiceEdit.getItems().add(t.getFname());
	            ChoiceDeleteSpNurse.getItems().add(t.getFname());
	        }

	        ChoiceDeleteSpDay.getItems().addAll("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturdayy");
	        ChoiceDeleteSpShift.getItems().addAll("Sunday 1", "Sunday 2", "Monday 1", "Monday 2", "Tuesday 1", "Tuesday 2", "Wednesday 1", "Wednesday 2", "Thursday 1", "Thursday 2", "Friday 1", "Friday 2", "Saturday 1", "Saturday 2");
	    }
	    
	    
	
	
	
	
}
