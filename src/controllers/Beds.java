package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.prefs.Preferences;
import Application.Constants;
import databaseSQL.SQLite;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Administer;
import model.Bed;
import model.Nurse;
import model.Resident;



public class Beds {
	 @FXML
	    private Button roster;
	    @FXML
	    private Button resident;
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
	private Button admmed;
	@FXML
	private TableView<Resident> tableView;
	@FXML
	private TableColumn<Resident,String> FNameColumn;
	@FXML
	private TableColumn<Resident,String> LNameColumn;
	@FXML
	private TableColumn<Resident,String> BedColumn = new TableColumn<>("idbed");
	@FXML
	private Button w1r1b1;
	@FXML
	private Button w1r2b1;
	@FXML
	private Button w1r2b2;
	@FXML
	private Button w1r3b1;
	@FXML
	private Button w1r3b2;
	@FXML
	private Button w1r3b3;
	@FXML
	private Button w1r3b4;
	@FXML
	private Button w1r4b1;
	@FXML
	private Button w1r4b2;
	@FXML
	private Button w1r4b3;
	@FXML
	private Button w1r4b4;
	@FXML
	private Button w1r5b1;
	@FXML
	private Button w1r5b2;
	@FXML
	private Button w1r5b3;
	@FXML
	private Button w1r5b4;
	@FXML
	private Button w1r6b1;
	@FXML
	private Button w1r6b2;
	@FXML
	private Button w1r6b3;
	@FXML
	private Button w1r6b4;
	@FXML
	private Button w2r1b1;
	@FXML
	private Button w2r2b1;
	@FXML
	private Button w2r2b2;
	@FXML
	private Button w2r3b1;
	@FXML
	private Button w2r3b2;
	@FXML
	private Button w2r3b3;
	@FXML
	private Button w2r3b4;
	@FXML
	private Button w2r4b1;
	@FXML
	private Button w2r4b2;
	@FXML
	private Button w2r4b3;
	@FXML
	private Button w2r4b4;
	@FXML
	private Button w2r5b1;
	@FXML
	private Button w2r5b2;
	@FXML
	private Button w2r5b3;
	@FXML
	private Button w2r5b4;
	@FXML
	private Button w2r6b1;
	@FXML
	private Button w2r6b2;
	@FXML
	private Button w2r6b3;
	@FXML
	private Button w2r6b4;
	@FXML
	ArrayList<Button> ward1r1b1 = new ArrayList<Button>();
	@FXML
	ArrayList<Button> ward1r2b1 = new ArrayList<Button>();
	private Preferences prefs;

	String style = "-fx-background-color:  DODGERBLUE";
	String style2 = "-fx-background-color:  WHITE";
	String style3 = "-fx-background-color:  #ff1f1f";



	public  ObservableList<Administer> MedList = FXCollections.observableArrayList();
	public  ObservableList<Resident> ResidentList = FXCollections.observableArrayList();
	public  ObservableList<Bed> BedList = FXCollections.observableArrayList();
	public  ObservableList<Bed> ResBed = FXCollections.observableArrayList();
	public  ObservableList<Bed> ResInBed = FXCollections.observableArrayList();

	
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

	public ObservableList<Bed> SelectAllByFname(String Fname) throws SQLException {

		ObservableList<Bed> ResBed = FXCollections.observableArrayList();
		String sql = "select * from bed";
		Connection connection = SQLite.dbConnector();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			if (Fname.equals(rs.getString("Fname"))) {
				Resident r = new Resident(rs.getString("Fname"), rs.getString("Lname"), rs.getString("gender"));
				Bed bo = new Bed(r, rs.getString("idbed"));
				r.setFname(rs.getString("FName"));
				r.setLname(rs.getString("LName"));
				r.setGender(rs.getString("gender"));
				bo.setIdBed(rs.getString("idbed"));
				ResBed.add(bo);
			}
		}

		ps.close();
		rs.close();
		return ResBed;
	}



	public ObservableList<Bed> SelectBed() throws SQLException {
		ObservableList<Bed> bedList = FXCollections.observableArrayList();
		Connection connection = SQLite.dbConnector();
		String sql =  "select * from bed";   
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();


		while (rs.next()) {
			Bed b = new Bed(rs.getString("idbed"));
			b.setIdBed(rs.getString("idbed"));
			bedList.add(b);
		}

		ps.close();
		rs.close();
		return bedList;
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

	public void insertSpecificBed(String Fname, String Lname, Bed b, String gender) throws SQLException{

		Connection connection = SQLite.dbConnector();
		String St = "UPDATE bed SET FName = ?, LName = ?, gender = ? WHERE idbed = ?";
		
		try(PreparedStatement ps = connection.prepareStatement(St))
		{
			 connection.setAutoCommit(false); 

		ps.setString(1, Fname); 
		ps.setString(2, Lname);
		ps.setString(4, b.getIdBed());
		ps.setString(3, gender);
		ps.executeUpdate();
		connection.commit();
		ps.close();}
		catch (SQLException e) {
	        System.err.println("Cannot Connect to Database");
	    }


	} 
	
	public int releaseSpecificBed(Bed b) throws SQLException{

		Connection connection = SQLite.dbConnector();
		String St = "UPDATE bed SET FName= null, LName= null, gender= null WHERE idbed=?";
		PreparedStatement ps = connection.prepareStatement(St);
		ps.setString(1, b.getIdBed());
		int layout = ps.executeUpdate(); 
		ps.close();
		return layout;


	} 



	public ObservableList<Bed> SelectAll() throws SQLException {
		ObservableList<Bed> bedList = FXCollections.observableArrayList();
		Connection connection = SQLite.dbConnector();
		String sql =  "select * from residents, overall where residents.FName = overall.FName"; 
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();


		while (rs.next()) {

			Resident r = new Resident(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getString("type"), rs.getString("gender"));

			Bed b = new Bed(r, rs.getString("idbed"));
			bedList.add(b);
		}

		ps.close();
		rs.close();
		return bedList;
	}
	
	public ObservableList<Administer> Selectover() throws SQLException {
		ObservableList<Administer> medList = FXCollections.observableArrayList();
		Connection connection = SQLite.dbConnector();
		String sql =  "select DName, RName, MName, dosage, times from doctorper where time is not NULL"; 
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();


		while (rs.next()) {

			Administer a = new Administer(rs.getString("dname"), rs.getString("rname"), rs.getString("mname"), rs.getInt("dosage"), rs.getInt("times"));
			a.setDname(rs.getString("DName"));
			a.setRname(rs.getString("RName"));
			a.setMname(rs.getString("MName"));
			a.setDosage(rs.getInt("dosage"));
			a.setTimes(rs.getInt("times"));
			medList.add(a);
		}

		ps.close();
		rs.close();
		return medList;
	}
	
	
	
	@FXML
	public void removeResident(MouseEvent event) {
			 	    	Resident sI = tableView.getSelectionModel().getSelectedItem();
			 	    	for(Bed b : ResInBed) {
			 	    	if(sI.getFname().equalsIgnoreCase(b.getResident().getFname()))	{
			 	    		b.setIsBedAvail(true);
			 	    		b.setResident(null);
			 	    		try {
								releaseSpecificBed(b);
							} catch (SQLException e) {
								e.printStackTrace();
							}
			 	    		tableView.getItems().clear();
							try {
								bedmenu2(event);
							} catch (IOException e) {
								e.printStackTrace();
							}
			 	    	}
			 	    	}}
			 	    	
	@FXML
	public void adminmed(MouseEvent event) {
		Resident sI = tableView.getSelectionModel().getSelectedItem();
		for(Bed b : ResInBed) {
			if(sI.getFname().equalsIgnoreCase(b.getResident().getFname()))	{
				for(Administer a : MedList) {
					if(a.getRname().equalsIgnoreCase(sI.getFname())){	
						prefs = Preferences.userRoot().node(this.getClass().getName());
						String KEY_AUTO_EXIT = null;
						Alert alert = createAlertWithOptOut(AlertType.CONFIRMATION, "Administer Medicine", null, 
								"Do you wish to administer medicine " + a.getMname() + " " + a.getDosage() + " mg/ml " + a.getTimes() + " a day, issued by " + a.getDname() + "?", "Request review", 
								param -> prefs.put(KEY_AUTO_EXIT, param ? "Always" : "Never"), ButtonType.YES, ButtonType.NO);
						if (alert.showAndWait().filter(t -> t == ButtonType.YES).isPresent()) {
						    alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("Doctor Request");
							alert.setHeaderText(null);
							alert.setContentText("Your request has been filed! :(");
							alert.showAndWait();
						}}}}}

			 	   							try {
			 	   								bedmenu2(event);
			 	   							} catch (IOException e) {
			 	   								e.printStackTrace();}
			 	   							
			 	   			 	    	
			 	   			 	    	

	}
			 	    	public static Alert createAlertWithOptOut(AlertType type, String title, String headerText, 
			 	               String message, String optOutMessage, Consumer<Boolean> optOutAction, 
			 	               ButtonType... buttonTypes) {
			 	   Alert alert = new Alert(type);
			 	   // Need to force the alert to layout in order to grab the graphic,
			 	    // as we are replacing the dialog pane with a custom pane
			 	    alert.getDialogPane().applyCss();
			 	    Node graphic = alert.getDialogPane().getGraphic();
			 	    // Create a new dialog pane that has a checkbox instead of the hide/show details button
			 	    // Use the supplied callback for the action of the checkbox
			 	    alert.setDialogPane(new DialogPane() {
			 	      @Override
			 	      protected Node createDetailsButton() {
			 	        CheckBox optOut = new CheckBox();
			 	        optOut.setText(optOutMessage);
			 	        optOut.setOnAction(e -> optOutAction.accept(optOut.isSelected()));
			 	        return optOut;
			 	      }
			 	    });
			 	    alert.getDialogPane().getButtonTypes().addAll(buttonTypes);
			 	    alert.getDialogPane().setContentText(message);
			 	    // Fool the dialog into thinking there is some expandable content
			 	    // a Group won't take up any space if it has no children
			 	    alert.getDialogPane().setExpandableContent(new Group());
			 	    alert.getDialogPane().setExpanded(true);
			 	    // Reset the dialog graphic using the default style
			 	    alert.getDialogPane().setGraphic(graphic);
			 	    alert.setTitle(title);
			 	    alert.setHeaderText(headerText);
			 	    return alert;
			 	}
	
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
		BedColumn.setCellValueFactory(callback);

	}; 

	@FXML
	public void loadData() throws SQLException {

		FNameColumn.setCellValueFactory(new PropertyValueFactory<Resident,String>("Fname"));
		LNameColumn.setCellValueFactory(new PropertyValueFactory<Resident,String>("Lname"));

		tableView.setItems(selectAll());
	}

	public void JavafxChoiceFill() throws SQLException {
		ResidentList = selectAll();
		BedList = SelectBed();
		ResInBed = SelectBeds();
		MedList = null;
		MedList = Selectover();
		for (Resident r : ResidentList) { 
			ResBed = null;
			ResBed = SelectAllByFname(r.getFname());

		
			for(Bed b : ResBed) {
				if (b.getIdBed().equals("1")) {
					w1r1b1.getStyleClass().clear();
					w1r1b1.setStyle(style2);
					b.getResident().getGender();
				if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
					w1r1b1.getStyleClass().removeAll(style2);
					w1r1b1.setStyle(style);}
				else if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
				
					w1r1b1.getStyleClass().removeAll(style2);
					w1r1b1.setStyle(style3); 	 	        		 	 	
				}
				else {
					w1r1b1.setStyle(style2); 
				}
				b = BedList.get(0);
				b.setIsBedAvail(false);
				}
				if (b.getIdBed().equals("2")) {
					w1r2b1.setStyle(style2);
					b.setIsBedAvail(false);
					b.getResident().getGender();
				if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
					w1r2b1.getStyleClass().removeAll(style2);
					w1r2b1.setStyle(style);
					}
				else if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
				
					w1r2b1.getStyleClass().removeAll(style2);
					w1r2b1.setStyle(style3); 	 	        		 	 	
				}
				else {
					w1r2b1.setStyle(style2);
				}
				b = BedList.get(1);
				b.setIsBedAvail(false);
				}
				if (b.getIdBed().equals("3"))  {
					w1r2b2.setStyle(style2);
					b.getResident().getGender();
				if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
					w1r2b2.getStyleClass().removeAll(style2);
					w1r2b2.setStyle(style);}
				else if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
					w1r2b2.getStyleClass().removeAll(style2);
					w1r2b2.setStyle(style3); 	 	        		 	 	
				}
				else {
					w1r2b2.setStyle(style2);
				}
				b = BedList.get(2);
				b.setIsBedAvail(false);
				}
				if (b.getIdBed().equals("4")) {
					w1r3b1.setStyle(style2);
					b.getResident().getGender();
				if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
					w1r3b1.getStyleClass().removeAll(style2);
					w1r3b1.setStyle(style);}
				else if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
					w1r3b1.getStyleClass().removeAll(style2);
					w1r3b1.setStyle(style3); 	 	        		 	 	
				}
				if(b.getResident().getGender().isEmpty()) {
					w1r3b1.setStyle(style2);
				}
				b = BedList.get(3);
				b.setIsBedAvail(false);
				}
				if (b.getIdBed().equals("5")) {
					w1r3b2.setStyle(style2);
					b.getResident().getGender();
				if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
					w1r3b2.getStyleClass().removeAll(style2);
					w1r3b2.setStyle(style);}
				if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
					w1r3b2.getStyleClass().removeAll(style2);
					w1r3b2.setStyle(style3); 	 	        		 	 	
				}
				if(b.getResident().getGender().isEmpty()) {
					w1r3b2.setStyle(style2);
				}
				b = BedList.get(4);
				b.setIsBedAvail(false);
				}
				if (b.getIdBed().equals("6")) {
					w1r3b3.setStyle(style2);
					b.getResident().getGender();
				if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
					w1r3b3.getStyleClass().removeAll(style2);
					w1r3b3.setStyle(style);}
				if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
					w1r3b3.getStyleClass().removeAll(style2);
					w1r3b3.setStyle(style3); 	 	        		 	 	
				}
				if(b.getResident().getGender().isEmpty()) {
					w1r3b3.setStyle(style2);
					b = BedList.get(5);
					b.setIsBedAvail(false);}
				}
				if (b.getIdBed().equals("7")) {
					w1r3b4.setStyle(style2);
					b.getResident().getGender();
				if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
					w1r3b4.getStyleClass().removeAll(style2);
					w1r3b4.setStyle(style);}
				if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
					w1r3b4.getStyleClass().removeAll(style2);
					w1r3b4.setStyle(style3); 	 	        		 	 	
				}
				if(b.getResident().getGender().isEmpty()) {
					w1r3b4.setStyle(style2);
				b = BedList.get(6);
				b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("8")) {
					w1r4b1.setStyle(style2);
					b.getResident().getGender();
				if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
					w1r4b1.getStyleClass().removeAll(style2);
					w1r4b1.setStyle(style);}
				if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
					w1r4b1.getStyleClass().removeAll(style2);
					w1r4b1.setStyle(style3); 	 	        		 	 	
				}
				if(b.getResident().getGender().isEmpty()) {
					w1r4b1.setStyle(style2);
				b = BedList.get(7);
				b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("9")) {
					w1r4b2.setStyle(style2);
					b.getResident().getGender();
				if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
					w1r4b2.getStyleClass().removeAll(style2);
					w1r4b2.setStyle(style);}
				if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
					w1r4b2.getStyleClass().removeAll(style2);
					w1r4b2.setStyle(style3); 	 	        		 	 	
				}
				if(b.getResident().getGender().isEmpty()) {
					w1r4b2.setStyle(style2);				
				b = BedList.get(8);
				b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("10")) {
					w1r4b3.setStyle(style2);
					b.getResident().getGender();
				if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
					w1r4b3.getStyleClass().removeAll(style2);
					w1r4b3.setStyle(style);}
				if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
					w1r4b3.getStyleClass().removeAll(style2);
					w1r4b3.setStyle(style3); 	 	        		 	 	
				}
				if(b.getResident().getGender().isEmpty()) {
					w1r4b3.setStyle(style2);
				b = BedList.get(9);
				b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("11")) {
					w1r4b4.setStyle(style2);
					b.getResident().getGender();
				if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
					w1r4b4.getStyleClass().removeAll(style2);
					w1r4b4.setStyle(style);}
				if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
					w1r4b4.getStyleClass().removeAll(style2);
					w1r4b4.setStyle(style3); 	 	        		 	 	
				}
				if(b.getResident().getGender().isEmpty()) {
					w1r4b4.setStyle(style2);
				b = BedList.get(10);
				b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("12")) {
					w1r5b1.setStyle(style2);
					b.getResident().getGender();
				if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
					w1r5b1.getStyleClass().removeAll(style2);
					w1r5b1.setStyle(style);}
				if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
					w1r5b1.getStyleClass().removeAll(style2);
					w1r5b1.setStyle(style3); 	 	        		 	 	
				}
				if(b.getResident().getGender().isEmpty()) {
					w1r5b1.setStyle(style2);
				b = BedList.get(11);
				b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("13")) {
					w1r5b2.setStyle(style2);
					b.getResident().getGender();
				if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
					w1r5b2.getStyleClass().removeAll(style2);
					w1r5b2.setStyle(style);}
				if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {					
					w1r5b2.getStyleClass().removeAll(style2);
					w1r5b2.setStyle(style3); 	 	        		 	 	
				}
				if(b.getResident().getGender().isEmpty()) {
					w1r5b2.setStyle(style2);
				b = BedList.get(12);
				b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("14")) {
					w1r5b3.setStyle(style2);
					b.getResident().getGender();
				if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
					w1r5b3.getStyleClass().removeAll(style2);
					w1r5b3.setStyle(style);}
				if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
					w1r5b3.getStyleClass().removeAll(style2);
					w1r5b3.setStyle(style3);
				}
				if(b.getResident().getGender().isEmpty()) {
					w1r5b3.setStyle(style2);	 	        		 	 	
				b = BedList.get(13);
				b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("15")) {
					w1r5b4.setStyle(style2);
					b.getResident().getGender();
				if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
					w1r5b4.getStyleClass().removeAll(style2);
					w1r5b4.setStyle(style);}
				if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
					w1r5b4.getStyleClass().removeAll(style2);
					w1r5b4.setStyle(style3);
				}
				if(b.getResident().getGender().isEmpty()) {
					w1r5b4.setStyle(style2);	 	        		 	 	
				b = BedList.get(14);
				b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("16")) {
					w1r6b1.setStyle(style2);
				b.getResident().getGender();
			if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
				w1r6b1.getStyleClass().removeAll(style2);
				w1r6b1.setStyle(style);}
			if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
				w1r6b1.getStyleClass().removeAll(style2);
				w1r6b1.setStyle(style3);
			}
			if(b.getResident().getGender().isEmpty()) {
				w1r6b1.setStyle(style2);	 	        		 	 	
			b = BedList.get(15);
			b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("17")) {
					w1r6b2.setStyle(style2);
				b.getResident().getGender();
			if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
				w1r6b2.getStyleClass().removeAll(style2);
				w1r6b2.setStyle(style);}
			if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
				w1r6b2.getStyleClass().removeAll(style2);
				w1r6b2.setStyle(style3);
			}
			if(b.getResident().getGender().isEmpty()) {
				w1r6b2.setStyle(style2);	 	        		 	 	
			b = BedList.get(16);
			b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("18")) {
					w1r6b3.setStyle(style2);
				b.getResident().getGender();
			if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
				w1r6b3.getStyleClass().removeAll(style2);
				w1r6b3.setStyle(style);}
			if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
				w1r6b3.getStyleClass().removeAll(style2);
				w1r6b3.setStyle(style3);
			}
			if(b.getResident().getGender().isEmpty()) {
				w1r6b3.setStyle(style2);	 	        		 	 	
			b = BedList.get(17);
			b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("19")) {
					w1r6b4.setStyle(style2);
				b.getResident().getGender();
			if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
				w1r6b4.getStyleClass().removeAll(style2);
				w1r6b4.setStyle(style);}
			if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
				w1r6b4.getStyleClass().removeAll(style2);
				w1r6b4.setStyle(style3);
			}
			if(b.getResident().getGender().isEmpty()) {
				w1r6b4.setStyle(style2);	 	        		 	 	
			b = BedList.get(18);
			b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("20")) {
					w2r1b1.setStyle(style2);
				b.getResident().getGender();
			if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
				w2r1b1.getStyleClass().removeAll(style2);
				w2r1b1.setStyle(style);}
			if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
				w2r1b1.getStyleClass().removeAll(style2);
				w2r1b1.setStyle(style3);
			}
			if(b.getResident().getGender().isEmpty()) {
				w2r1b1.setStyle(style2);	 	        		 	 	
			b = BedList.get(19);
			b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("21")) {
					w2r2b1.setStyle(style2);
				b.getResident().getGender();
			if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
				w2r2b1.getStyleClass().removeAll(style2);
				w2r2b1.setStyle(style);}
			if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
				w2r2b1.getStyleClass().removeAll(style2);
				w2r2b1.setStyle(style3);
			}
			if(b.getResident().getGender().isEmpty()) {
				w2r2b1.setStyle(style2);	 	        		
			b = BedList.get(20);
			b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("22")) {
					w2r2b2.setStyle(style2);
				b.getResident().getGender();
			if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
				w2r2b2.getStyleClass().removeAll(style2);
				w2r2b2.setStyle(style);}
			if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
				w2r2b2.getStyleClass().removeAll(style2);
				w2r2b2.setStyle(style3);
			}
			if(b.getResident().getGender().isEmpty()) {
				w2r2b2.setStyle(style2);	 	        		 	 	
			b = BedList.get(21);
			b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("23")) {
				w2r3b1.setStyle(style2);
				b.getResident().getGender();
			if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
				w2r3b1.getStyleClass().removeAll(style2);
				w2r3b1.setStyle(style);}
			if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
				w2r3b1.getStyleClass().removeAll(style2);
				w2r3b1.setStyle(style3);
			}
			if(b.getResident().getGender().isEmpty()) {
				w2r3b1.setStyle(style2);	 	        		 	 	
			b = BedList.get(22);
			b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("24")) {
				w2r3b2.setStyle(style2);
				b.getResident().getGender();
			if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
				w2r3b2.getStyleClass().removeAll(style2);
				w2r3b2.setStyle(style);}
			if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
				w2r3b2.getStyleClass().removeAll(style2);
				w2r3b2.setStyle(style3);
			}
			if(b.getResident().getGender().isEmpty()) {
				w2r3b2.setStyle(style2);	 	        		 	 	
			b = BedList.get(23);
			b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("25")) {
				w2r3b3.setStyle(style2);
				b.getResident().getGender();
			if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
				w2r3b3.getStyleClass().removeAll(style2);
				w2r3b3.setStyle(style);}
			if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
				w2r3b3.getStyleClass().removeAll(style2);
				w2r3b3.setStyle(style3);
			}
			if(b.getResident().getGender().isEmpty()) {
				w2r3b3.setStyle(style2);	 	        		 	 	
			b = BedList.get(24);
			b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("26")) {
				w2r3b4.setStyle(style2);
				b.getResident().getGender();
			if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
				w2r3b4.getStyleClass().removeAll(style2);
				w2r3b4.setStyle(style);}
			if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
				w2r3b4.getStyleClass().removeAll(style2);
				w2r3b4.setStyle(style3);
			}
			if(b.getResident().getGender().isEmpty()) {
				w2r3b4.setStyle(style2);	 	        		 	 	
			b = BedList.get(25);
			b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("27")) {
				w2r4b1.setStyle(style2);
				b.getResident().getGender();
			if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
				w2r4b1.getStyleClass().removeAll(style2);
				w2r4b1.setStyle(style);}
			if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
				w2r4b1.getStyleClass().removeAll(style2);
				w2r4b1.setStyle(style3);
			}
			if(b.getResident().getGender().isEmpty()) {
				w2r4b1.setStyle(style2);	 	        		 	 	
			b = BedList.get(26);
			b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("28")) {
					w2r4b2.setStyle(style2);
					b.getResident().getGender();
				if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
					w2r4b2.getStyleClass().removeAll(style2);
					w2r4b2.setStyle(style);}
				if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
					w2r4b2.getStyleClass().removeAll(style2);
					w2r4b2.setStyle(style3);
				}
				if(b.getResident().getGender().isEmpty()) {
					w2r4b2.setStyle(style2);	 	        		 	 	
				b = BedList.get(27);
				b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("29")) {
					w2r4b3.setStyle(style2);
					b.getResident().getGender();
				if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
					w2r4b3.getStyleClass().removeAll(style2);
					w2r4b3.setStyle(style);}
				if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
					w2r4b3.getStyleClass().removeAll(style2);
					w2r4b3.setStyle(style3);
				}
				if(b.getResident().getGender().isEmpty()) {
					w2r4b3.setStyle(style2);	 	        		 	 	
				b = BedList.get(28);
				b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("30")) {
					w2r4b4.setStyle(style2);
					b.getResident().getGender();
				if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
					w2r4b4.getStyleClass().removeAll(style2);
					w2r4b4.setStyle(style);}
				if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
					w2r4b4.getStyleClass().removeAll(style2);
					w2r4b4.setStyle(style3);
				}
				if(b.getResident().getGender().isEmpty()) {
					w2r4b4.setStyle(style2);	 	        		 	 	
				b = BedList.get(29);
				b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("31")) {
					w2r5b1.setStyle(style2);
					b.getResident().getGender();
				if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
					w2r5b1.getStyleClass().removeAll(style2);
					w2r5b1.setStyle(style);}
				if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
					w2r5b1.getStyleClass().removeAll(style2);
					w2r5b1.setStyle(style3);
				}
				if(b.getResident().getGender().isEmpty()) {
					w2r5b1.setStyle(style2);	 	        		 	 	
				b = BedList.get(30);
				b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("32")) {
					w2r5b2.setStyle(style2);
					b.getResident().getGender();
				if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
					w2r5b2.getStyleClass().removeAll(style2);
					w2r5b2.setStyle(style);}
				if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
					w2r5b2.getStyleClass().removeAll(style2);
					w2r5b2.setStyle(style3);
				}
				if(b.getResident().getGender().isEmpty()) {
					w2r5b2.setStyle(style2);	 	        		 	 	
				b = BedList.get(31);
				b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("33")) {
					w2r5b3.setStyle(style2);
					b.getResident().getGender();
				if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
					w2r5b3.getStyleClass().removeAll(style2);
					w2r5b3.setStyle(style);}
				if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
					w2r5b3.getStyleClass().removeAll(style2);
					w2r5b3.setStyle(style3);
				}
				if(b.getResident().getGender().isEmpty()) {
					w2r5b3.setStyle(style2);	 	        		 	 	
				b = BedList.get(32);
				b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("34")) {
					w2r5b4.setStyle(style2);
					b.getResident().getGender();
				if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
					w2r5b4.getStyleClass().removeAll(style2);
					w2r5b4.setStyle(style);}
				if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
					w2r5b4.getStyleClass().removeAll(style2);
					w2r5b4.setStyle(style3);
				}
				if(b.getResident().getGender().isEmpty()) {
					w2r5b4.setStyle(style2);	 	        		 	 	
				b = BedList.get(33);
				b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("35")) {
					w2r6b1.setStyle(style2);
					b.getResident().getGender();
				if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
					w2r6b1.getStyleClass().removeAll(style2);
					w2r6b1.setStyle(style);}
				if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
					w2r6b1.getStyleClass().removeAll(style2);
					w2r6b1.setStyle(style3);
				}
				if(b.getResident().getGender().isEmpty()) {
					w2r6b1.setStyle(style2);	 	        		 	 	
				b = BedList.get(34);
				b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("36")) {
				w2r6b2.setStyle(style2);
				b.getResident().getGender();
			if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
				w2r6b2.getStyleClass().removeAll(style2);
				w2r6b2.setStyle(style);}
			if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
				w2r6b2.getStyleClass().removeAll(style2);
				w2r6b2.setStyle(style3);
			}
			if(b.getResident().getGender().isEmpty()) {
				w2r6b2.setStyle(style2);	 	        		 	 	
			b = BedList.get(35);
			b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("37")) {
				w2r6b3.setStyle(style2);
				b.getResident().getGender();
			if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
				w2r6b3.getStyleClass().removeAll(style2);
				w2r6b3.setStyle(style);}
			if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
				w2r6b3.getStyleClass().removeAll(style2);
				w2r6b3.setStyle(style3);
			}
			if(b.getResident().getGender().isEmpty()) {
				w2r6b3.setStyle(style2);	 	        		 	 	
			b = BedList.get(36);
			b.setIsBedAvail(false);
				}}
				if (b.getIdBed().equals("38")) {
					w2r6b4.setStyle(style2);
				b.getResident().getGender();
			if(b.getResident().getGender().equalsIgnoreCase("MALE")) {
				w2r6b4.getStyleClass().removeAll(style2);
				w2r6b4.setStyle(style);}
			if(b.getResident().getGender().equalsIgnoreCase("FEMALE")) {
				w2r6b4.getStyleClass().removeAll(style2);
				w2r6b4.setStyle(style3);
			}
			if(b.getResident().getGender().isEmpty()) {
				w2r6b4.setStyle(style2);	 	        		 	 	
			b = BedList.get(37);
			b.setIsBedAvail(false);
				}}}}

			}


			//Add Tab   --->   Evening shift
			@FXML
			void onClickW1R1B1(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();


				Bed b = BedList.get(0);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
						try {
							insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
						} catch (SQLException e) {
							e.printStackTrace();
						}
					w1r1b1.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w1r1b1.getStyleClass().removeAll(style2);
						w1r1b1.setStyle(style);}
					else {
						w1r1b1.getStyleClass().removeAll(style2);
						w1r1b1.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}





			//Add Tab   --->   Evening shift
			@FXML
			void onClickW1R2B1(MouseEvent event) {

				Resident r = tableView.getSelectionModel().getSelectedItem();

				Bed b = BedList.get(1);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

					b.setResident(r);
					b.setIsBedAvail(false);
						try {
							insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
						} catch (SQLException e) {
							e.printStackTrace();
						}
					w1r2b1.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w1r2b1.getStyleClass().removeAll(style2);
						w1r2b1.setStyle(style);}
					else {
						w1r2b1.getStyleClass().removeAll(style2);
						w1r2b1.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}}

			//Add Tab   --->   Evening shift
			@FXML
			void onClickW1R2B2(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();


				Bed b = BedList.get(2);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
						try {
							insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
						} catch (SQLException e) {
							e.printStackTrace();
						}
					w1r2b2.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w1r2b2.getStyleClass().removeAll(style2);
						w1r2b2.setStyle(style);}
					else {
						w1r2b2.getStyleClass().removeAll(style2);
						w1r2b2.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}




			//Add Tab   --->   Evening shift
			@FXML
			void onClickW1R3B1(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();


				Bed b = BedList.get(3);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w1r3b1.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w1r3b1.getStyleClass().removeAll(style2);
						w1r3b1.setStyle(style);}
					else {
						w1r3b1.getStyleClass().removeAll(style2);
						w1r3b1.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}




			//Edit Tab   --->   Morning shift
			@FXML
			void onClickW1R3B2(MouseEvent event) { 
				Resident r = tableView.getSelectionModel().getSelectedItem();


				Bed b = BedList.get(4);
					if(b.isBedAvailable() == false) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Bed currently occupied");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();

					}
					else {
						for(Bed c : ResInBed) {
							if(c.getResident().getFname().equals(r.getFname()))	{ 
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error Dialog");
							alert.setHeaderText("Resident currently in another bed");
							alert.setContentText("Ooops, there was an error!");
							alert.showAndWait();
							return;
						}}}

						b.setResident(r);
						b.setIsBedAvail(false);
						try {
							insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
						} catch (SQLException e) {
							e.printStackTrace();
						}
					w1r3b2.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w1r3b2.getStyleClass().removeAll(style2);
						w1r3b2.setStyle(style);}
					else {
						w1r3b2.getStyleClass().removeAll(style2);
						w1r3b2.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			//Add Tab   --->   Morning shift
			@FXML
			void onClickW1R3B3(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();


				Bed b = BedList.get(5);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
						try {
							insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
						} catch (SQLException e) {
							e.printStackTrace();
						}
					w1r3b3.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w1r3b3.getStyleClass().removeAll(style2);
						w1r3b3.setStyle(style);}
					else {
						w1r3b3.getStyleClass().removeAll(style2);
						w1r3b3.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
			
			//Edit Tab   --->   Evening shift
			@FXML
			void onClickW1R3B4(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();


				Bed b = BedList.get(6);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
						try {
							insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
						} catch (SQLException e) {
							e.printStackTrace();
						}
					w1r3b4.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w1r3b4.getStyleClass().removeAll(style2);
						w1r3b4.setStyle(style);}
					else {
						w1r3b4.getStyleClass().removeAll(style2);
						w1r3b4.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//Add Tab   --->   Evening shift
			@FXML
			void onClickW1R4B1(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();


				Bed b = BedList.get(7);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
						try {
							insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
						} catch (SQLException e) {
							e.printStackTrace();
						}
					w1r4b1.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w1r4b1.getStyleClass().removeAll(style2);
						w1r4b1.setStyle(style);}
					else {
						w1r4b1.getStyleClass().removeAll(style2);
						w1r4b1.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//Edit Tab   --->   Morning shift
			@FXML
			void onClickW1R4B2(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();


				Bed b = BedList.get(8);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
						try {
							insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
						} catch (SQLException e) {
							e.printStackTrace();
						}
					w1r4b2.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w1r4b2.getStyleClass().removeAll(style2);
						w1r4b2.setStyle(style);}
					else {
						w1r4b2.getStyleClass().removeAll(style2);
						w1r4b2.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//Add Tab   --->   Morning shift
			@FXML
			void onClickW1R4B3(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();


				Bed b = BedList.get(9);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w1r4b3.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w1r4b3.getStyleClass().removeAll(style2);
						w1r4b3.setStyle(style);}
					else {
						w1r4b3.getStyleClass().removeAll(style2);
						w1r4b3.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			//Edit Tab   --->   Evening shift
			@FXML
			void onClickW1R4B4(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem(); 


				Bed b = BedList.get(10);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w1r4b4.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w1r4b4.getStyleClass().removeAll(style2);
						w1r4b4.setStyle(style);}
					else {
						w1r4b4.getStyleClass().removeAll(style2);
						w1r4b4.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			//Add Tab   --->   Evening shift
			@FXML
			void onClickW1R5B1(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();


				Bed b = BedList.get(11);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w1r5b1.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w1r5b1.getStyleClass().removeAll(style2);
						w1r5b1.setStyle(style);}
					else {
						w1r5b1.getStyleClass().removeAll(style2);
						w1r5b1.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			//Edit Tab   --->   Morning shift
			@FXML
			void onClickW1R5B2(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();


				Bed b = BedList.get(12);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w1r5b2.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w1r5b2.getStyleClass().removeAll(style2);
						w1r5b2.setStyle(style);}
					else {
						w1r5b2.getStyleClass().removeAll(style2);
						w1r5b2.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//Add Tab   --->   Morning shift
			@FXML
			void onClickW1R5B3(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();


				Bed b = BedList.get(13);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w1r5b3.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w1r5b3.getStyleClass().removeAll(style2);
						w1r5b3.setStyle(style);}
					else {
						w1r5b3.getStyleClass().removeAll(style2);
						w1r5b3.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//Edit Tab   --->   Evening shift
			@FXML
			void onClickW1R5B4(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();


				Bed b = BedList.get(14);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w1r5b4.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w1r5b4.getStyleClass().removeAll(style2);
						w1r5b4.setStyle(style);}
					else {
						w1r5b4.getStyleClass().removeAll(style2);
						w1r5b4.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			//Add Tab   --->   Evening shift
			@FXML
			void onClickW1R6B1(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();
				Bed b = BedList.get(15);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w1r6b1.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w1r6b1.getStyleClass().removeAll(style2);
						w1r6b1.setStyle(style);}
					else {
						w1r6b1.getStyleClass().removeAll(style2);
						w1r6b1.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			//Edit Tab   --->   Morning shift
			@FXML
			void onClickW1R6B2(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();
				Bed b = BedList.get(16);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w1r6b2.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w1r6b2.getStyleClass().removeAll(style2);
						w1r6b2.setStyle(style);}
					else {
						w1r6b2.getStyleClass().removeAll(style2);
						w1r6b2.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//Add Tab   --->   Morning shift
			@FXML
			void onClickW1R6B3(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();
				Bed b = BedList.get(17);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w1r6b3.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w1r6b3.getStyleClass().removeAll(style2);
						w1r6b3.setStyle(style);}
					else {
						w1r6b3.getStyleClass().removeAll(style2);
						w1r6b3.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			//Edit Tab   --->   Evening shift
			@FXML
			void onClickW1R6B4(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();
				Bed b = BedList.get(18);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w1r6b4.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w1r6b4.getStyleClass().removeAll(style2);
						w1r6b4.setStyle(style);}
					else {
						w1r6b4.getStyleClass().removeAll(style2);
						w1r6b4.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}


			@FXML
			void onClickW2R1B1(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();
				Bed b = BedList.get(19);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w2r1b1.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w2r1b1.getStyleClass().removeAll(style2);
						w2r1b1.setStyle(style);}
					else {
						w2r1b1.getStyleClass().removeAll(style2);
						w2r1b1.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}



			//Edit Tab   --->   Morning shift
			@FXML
			void onClickW2R2B1(MouseEvent event) { 
				Resident r = tableView.getSelectionModel().getSelectedItem();
				Bed b = BedList.get(20);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w2r2b1.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w2r2b1.getStyleClass().removeAll(style2);
						w2r2b1.setStyle(style);}
					else {
						w2r2b1.getStyleClass().removeAll(style2);
						w2r2b1.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			//Add Tab   --->   Morning shift
			@FXML
			void onClickW2R2B2(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();
				Bed b = BedList.get(21);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w2r2b2.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w2r2b2.getStyleClass().removeAll(style2);
						w2r2b2.setStyle(style);}
					else {
						w2r2b2.getStyleClass().removeAll(style2);
						w2r2b2.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			@FXML
			void onClickW2R3B1(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();
				Bed b = BedList.get(22);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w2r3b1.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w2r3b1.getStyleClass().removeAll(style2);
						w2r3b1.setStyle(style);}
					else {
						w2r3b1.getStyleClass().removeAll(style2);
						w2r3b1.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}



			//Edit Tab   --->   Morning shift
			@FXML
			void onClickW2R3B2(MouseEvent event) { 
				Resident r = tableView.getSelectionModel().getSelectedItem();
				Bed b = BedList.get(23);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w2r3b2.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w2r3b2.getStyleClass().removeAll(style2);
						w2r3b2.setStyle(style);}
					else {
						w2r3b2.getStyleClass().removeAll(style2);
						w2r3b2.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			//Add Tab   --->   Morning shift
			@FXML
			void onClickW2R3B3(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();
				Bed b = BedList.get(24);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w2r3b3.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w2r3b3.getStyleClass().removeAll(style2);
						w2r3b3.setStyle(style);}
					else {
						w2r3b3.getStyleClass().removeAll(style2);
						w2r3b3.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//Edit Tab   --->   Evening shift
			@FXML
			void onClickW2R3B4(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();
				Bed b = BedList.get(25);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w2r3b4.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w2r3b4.getStyleClass().removeAll(style2);
						w2r3b4.setStyle(style);}
					else {
						w2r3b4.getStyleClass().removeAll(style2);
						w2r3b4.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//Add Tab   --->   Evening shift
			@FXML
			void onClickW2R4B1(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();
				Bed b = BedList.get(26);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w2r4b1.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w2r4b1.getStyleClass().removeAll(style2);
						w2r4b1.setStyle(style);}
					else {
						w2r4b1.getStyleClass().removeAll(style2);
						w2r4b1.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//Edit Tab   --->   Morning shift
			@FXML
			void onClickW2R4B2(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();
				Bed b = BedList.get(27);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w2r4b2.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w2r4b2.getStyleClass().removeAll(style2);
						w2r4b2.setStyle(style);}
					else {
						w2r4b2.getStyleClass().removeAll(style2);
						w2r4b2.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//Add Tab   --->   Morning shift
			@FXML
			void onClickW2R4B3(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();
				Bed b = BedList.get(28);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w2r4b3.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w2r4b3.getStyleClass().removeAll(style2);
						w2r4b3.setStyle(style);}
					else {
						w2r4b3.getStyleClass().removeAll(style2);
						w2r4b3.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			//Edit Tab   --->   Evening shift
			@FXML
			void onClickW2R4B4(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();
				Bed b = BedList.get(29);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w2r4b4.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w2r4b4.getStyleClass().removeAll(style2);
						w2r4b4.setStyle(style);}
					else {
						w2r4b4.getStyleClass().removeAll(style2);
						w2r4b4.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//Add Tab   --->   Evening shift
			@FXML
			void onClickW2R5B1(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();
				Bed b = BedList.get(30);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w2r5b1.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w2r5b1.getStyleClass().removeAll(style2);
						w2r5b1.setStyle(style);}
					else {
						w2r5b1.getStyleClass().removeAll(style2);
						w2r5b1.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			//Edit Tab   --->   Morning shift
			@FXML
			void onClickW2R5B2(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();
				Bed b = BedList.get(31);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w2r5b2.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w2r5b2.getStyleClass().removeAll(style2);
						w2r5b2.setStyle(style);}
					else {
						w2r5b2.getStyleClass().removeAll(style2);
						w2r5b2.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//Add Tab   --->   Morning shift
			@FXML
			void onClickW2R5B3(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();
				Bed b = BedList.get(32);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w2r5b3.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w2r5b3.getStyleClass().removeAll(style2);
						w2r5b3.setStyle(style);}
					else {
						w2r5b3.getStyleClass().removeAll(style2);
						w2r5b3.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//Edit Tab   --->   Evening shift
			@FXML
			void onClickW2R5B4(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();
				Bed b = BedList.get(33);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}

						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w2r5b4.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w2r5b4.getStyleClass().removeAll(style2);
						w2r5b4.setStyle(style);}
					else {
						w2r5b4.getStyleClass().removeAll(style2);
						w2r5b4.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			//Add Tab   --->   Evening shift
			@FXML
			void onClickW2R6B1(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();
				Bed b = BedList.get(34);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}
						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w2r6b1.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w2r6b1.getStyleClass().removeAll(style2);
						w2r6b1.setStyle(style);}
					else {
						w2r6b1.getStyleClass().removeAll(style2);
						w2r6b1.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			//Edit Tab   --->   Morning shift
			@FXML
			void onClickW2R6B2(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();
				Bed b = BedList.get(35);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}
						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w2r6b2.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w2r6b2.getStyleClass().removeAll(style2);
						w2r6b2.setStyle(style);}
					else {
						w2r6b2.getStyleClass().removeAll(style2);
						w2r6b2.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//Add Tab   --->   Morning shift
			@FXML
			void onClickW2R6B3(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();
				Bed b = BedList.get(36);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}
						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w2r6b3.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w2r6b3.getStyleClass().removeAll(style2);
						w2r6b3.setStyle(style);}
					else {
						w2r6b3.getStyleClass().removeAll(style2);
						w2r6b3.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			//Edit Tab   --->   Evening shift
			@FXML
			void onClickW2R6B4(MouseEvent event) {
				Resident r = tableView.getSelectionModel().getSelectedItem();
				Bed b = BedList.get(37);
				if(b.isBedAvailable() == false) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Bed currently occupied");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();

				}
				else {
					for(Bed c : ResInBed) {
						if(c.getResident().getFname().equals(r.getFname()))	{ 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Resident currently in another bed");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						return;
					}}}
						b.setResident(r);
						b.setIsBedAvail(false);
					try {
						insertSpecificBed(b.getResident().getFname(), b.getResident().getLname(), b, b.getResident().getGender());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					w2r6b4.setStyle(style2);
					if (r.getGender().equals("MALE")) {
						w2r6b4.getStyleClass().removeAll(style2);
						w2r6b4.setStyle(style);}
					else {
						w2r6b4.getStyleClass().removeAll(style2);
						w2r6b4.setStyle(style3); 	 	        		 	 	
					}
	 	    		tableView.getItems().clear();
	 	    		try {
						JavafxChoiceFill();
						loadData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}



			@FXML
			void logout(MouseEvent event) throws IOException {
				Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Landing.fxml"));
				Node node = (Node) event.getSource();
				Stage stage = (Stage) node.getScene().getWindow();
				stage.setScene(new Scene(root));

			}
			 @FXML
			    void roster(MouseEvent event) throws IOException {
			        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/NRoster.fxml"));
			        Node node = (Node) event.getSource();
			        Stage stage = (Stage) node.getScene().getWindow();
			        stage.setScene(new Scene(root));
			        stage.setTitle("Nurse Roster");

			    }

			    @FXML
			    void resident(MouseEvent event) throws IOException {
			        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Resident.fxml"));
			        Node node = (Node) event.getSource();
			        Stage stage = (Stage) node.getScene().getWindow();
			        stage.setScene(new Scene(root));
			        stage.setTitle("Resident View");

			    }

			public void bedmenu2(Event event) throws IOException {
				Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Beds.fxml"));
				Node node = (Node) event.getSource();
				Stage stage = (Stage) node.getScene().getWindow();
				stage.setScene(new Scene(root));

			}
	
	
	
	
	@FXML
    private Button nurse;


	 @FXML
	    void nurse(MouseEvent event) throws IOException {
	        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Nurse.fxml"));
	        Node node = (Node) event.getSource();
	        Stage stage = (Stage) node.getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.setTitle("Nurse Homepage");

	    }

}
