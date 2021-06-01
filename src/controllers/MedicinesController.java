package controllers;



import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import Application.Constants;

import databaseSQL.SQLite;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Resident;
import model.Medicines;
import model.Perscription;
import model.Data;
import model.Doctor;
import model.MedicineCabinet;

public class MedicinesController {
	public  ObservableList<Resident> residentArray = FXCollections.observableArrayList();
	public  ObservableList<Resident> getResidentData() {
        return residentArray;
	}
	public  ObservableList<Medicines> medicineList = FXCollections.observableArrayList();  
	public  ObservableList<Medicines> getMedicineData() {
        return medicineList;
	}
	public Connection con;
	@FXML
	ComboBox<Medicines> medicineComboBox = new ComboBox<Medicines>();   
	@FXML
	ComboBox<Doctor> doctorComboBox = new ComboBox<Doctor>();
	@FXML
	ComboBox<Integer> timesComboBox = new ComboBox<Integer>();
	@FXML
	ComboBox<Integer> dosageComboBox = new ComboBox<Integer>();

	 @FXML
	 private Label unitValueLabel;
	 @FXML
	 private Label lblUser;
	 @FXML
	ComboBox<Resident> residentComboBox = new ComboBox<Resident>();
	 @FXML
	private Slider quantitySlider;
	 @FXML
	private Label dispencedUnitsValueLabel;
	public ObservableList<MedicineCabinet> cartObservableList = FXCollections.observableArrayList();
	 @FXML
	private Button addButton; 
	 @FXML
	private Button removeButton;
	@FXML
	private Button checkout;
	@FXML
	private Button logout;
	@FXML
	private Button medicines;
	@FXML
	private Button reorder;
	@FXML
	private Label totalValueLabel;
	@FXML
	Label medicineLabel;
	@FXML
	Label pricePerPartLabel;
	
	@FXML
	TextField OrdertfId = new TextField();
	
	@FXML
	Label totalValLabel2;
	
	@FXML
	Label qtySliderLabel;
	@FXML
	Label purchasedUnitsLabel;
	@FXML
	GridPane imageStackPane;
	@FXML
	TableView<MedicineCabinet> medicinesTableView;
	@FXML
	TableColumn<MedicineCabinet, String> residentColumn = new TableColumn<>("Fname");
	@FXML
	private TableColumn<MedicineCabinet,String> nameColumn;
	@FXML
	private TableColumn<MedicineCabinet,Integer> qtyColumn;
	@FXML
	private TableColumn<MedicineCabinet,String> medicineColumn;
	@FXML
	TableView<Medicines> medicinesTabView;	
	@FXML
	private TableColumn<Medicines, Integer> idColumn;
	@FXML
	private TableColumn<Medicines,String> nameColumn2;
	@FXML
	private TableColumn<Medicines,Integer> stockColumn;
	int available = 0;
	public Resident selectedResident;
	public Medicines selectedMedicine;
	
	ObservableList<Doctor> doctorlist= FXCollections.observableArrayList();
	public  ObservableList<Doctor> getDoctorData() {
        return doctorlist;
	}
    ObservableList<Integer> timeslist= FXCollections.observableArrayList(1,2,3,4); 
    ObservableList<Integer> dosagelist= FXCollections.observableArrayList(1,2,3,4,5,10,15,20,25,30,35,40,45,50);

	ObjectBinding<Medicines> medicineBinding = new ObjectBinding<>() {
		{
			bind(medicineComboBox.valueProperty());
		}
		@Override
		protected Medicines computeValue() {
			Medicines medicineList = null;
			if (medicineComboBox.getSelectionModel().getSelectedIndex() >= 0)
				medicineList = medicineComboBox.getSelectionModel().selectedItemProperty().get();
			else medicineList = new Medicines(0,"",0);
			quantitySlider.setValue(0);
			return medicineList;
		}};
		
		protected Resident computeValue2() {
		Resident residentArray = null;
		if (residentComboBox.getSelectionModel().getSelectedIndex() >= 0)
			residentArray = residentComboBox.getSelectionModel().selectedItemProperty().get();
		else residentArray = new Resident(0,"","","","");
		return residentArray;

		}
	

	@FXML
    public void initialize() throws ClassNotFoundException, SQLException {

	    loadData2();		
		loadData();
        medicineList.addAll(getMedicine());
        residentArray.addAll(getUsers());
        residentComboBox.setItems(getResidentData());
        medicineComboBox.setItems(getMedicineData());
        doctorComboBox.setItems(getDoctorData());
        doctorlist.addAll(getDoc());
        timesComboBox.setItems(timeslist);
        dosageComboBox.setItems(dosagelist);

        


        
        
        
        
     
        
        dispencedUnitsValueLabel.textProperty().bind(Bindings.format("%.0f", quantitySlider.valueProperty()));
        
        unitValueLabel.textProperty().bind(Bindings.format("%d %s", Bindings.select(medicineBinding, "stock"), Bindings.select(medicineBinding, "mName")));

	
	        
	        Callback<CellDataFeatures<MedicineCabinet, String>, ObservableValue<String>> callback = 
					new Callback<CellDataFeatures<MedicineCabinet, String>, ObservableValue<String>> () {

				@Override
				public ObservableValue<String> call(CellDataFeatures<MedicineCabinet, String> param) {
					Medicines selectedItem = medicineComboBox.getSelectionModel().getSelectedItem();
					Resident selectedItem2 = residentComboBox.getValue();
					
						if (selectedItem.getMName().equals(param.getValue().getName())) {
							
							return selectedItem2.getFnameProperty();
						
						
					}
					return null;
				}
			};
			//set resident columnn's call-back for factory method
			residentColumn.setCellValueFactory(callback);
				
				
			addButton.setOnAction(event -> {
				 Medicines selectedItem = medicineComboBox.getSelectionModel().getSelectedItem();
					MedicineCabinet shoppingCartItem = new MedicineCabinet(selectedItem.getMName(), (int) quantitySlider.getValue(), 0);
					cartObservableList.add(shoppingCartItem);
//					medicinesTableView.getSelectionModel().selectLast();
//					medicinesTabView.getSelectionModel().selectFirst();
				});
			
			medicines.setOnAction(event -> { 
				Parent root = null;
				try {
					root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Doctor.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        Node node = (Node) event.getSource();
		        Stage stage = (Stage) node.getScene().getWindow();
		        stage.setScene(new Scene(root));
		        stage.setTitle("Medicine Control");
				});
		        
		        checkout.setOnAction(event -> {
		        	Medicines selectedItem = medicineComboBox.getSelectionModel().getSelectedItem();
		        	Resident selectedResident = residentComboBox.getSelectionModel().getSelectedItem();
					selectedItem.stockProperty();
					
					if (selectedItem.getStock() >= (int) quantitySlider.getValue()) {
						int available1 = selectedItem.getStock();
						selectedItem.setStock(selectedItem.getStock() - (int) quantitySlider.getValue());
						int available2 = selectedItem.getStock();
						int totalavailable = available1 - available2;
						 Connection connection= SQLite.dbConnector(); 

					       
					        String insertString = "UPDATE medicine SET stock = ? where MName = ?";  
					 
					       try (PreparedStatement pst = connection.prepareStatement(insertString)) 
					    		   
					    		   {
					    	   connection.setAutoCommit(false); 
					        for (Medicines m : medicineList)  {
					        	String mName = m.getMName();
					        	Integer stock = m.getStock();
					        	
					        	pst.setInt(1, stock);
					        	pst.setString(2, mName);

					        	pst.executeUpdate(); 
					        	connection.commit();
					        }}
				 catch (SQLException e) {
			        System.err.println("Cannot Connect to Database");
			    }
					       Resident FName;
					       Medicines mName;
					       Integer times;
					       Integer dosage;
					       FName = residentComboBox.getValue();
					       mName = medicineComboBox.getValue();
					       times = timesComboBox.getValue();
					       dosage = dosageComboBox.getValue();
					       String insertString2 = "UPDATE overall SET mName = ?, dosage = ?, times = ? where FName = '"+FName.getFname()+"'"; 
							 
					       try (PreparedStatement pst = connection.prepareStatement(insertString2))
					    		   
					    		   {
					    	   connection.setAutoCommit(false); 
					  
					        	
					        	pst.setInt(2, dosage); 
					        	pst.setString(1, mName.getMName());
					        	pst.setInt(3, times);

					        	pst.executeUpdate(); 
					        	connection.commit();
					        }
				 catch (SQLException e) {
			        System.err.println("Cannot Connect to Database");
			    }
					       Doctor DName;
					       DName = doctorComboBox.getValue();
					       java.util.Date date = new Date();
					       Object time = new java.sql.Timestamp(date.getTime());    
					       String insertString3 = "INSERT INTO doctorper (DName, RName, MName, dosage, times, time)" + " VALUES (?, ?, ?, ?, ?, ?)"; 
							 
					       try (PreparedStatement pst = connection.prepareStatement(insertString3))
					    		   
					    		   {
					    	   connection.setAutoCommit(false); 
					  
					        	
					        	pst.setString(1, DName.getFname());
					        	pst.setString(2, FName.getFname());
					        	pst.setString(3, mName.getMName());
					        	pst.setInt(4,  dosage);
					        	pst.setInt(5,  times);
					        	pst.setObject(6, time);
					        	

					        	pst.executeUpdate(); 
					        	connection.commit();
					        }
				 catch (SQLException e) {
			        System.err.println("Cannot Connect to Database");
			    }
					       
						int index = medicinesTableView.getSelectionModel().getFocusedIndex();
						if (cartObservableList.size() > index) cartObservableList.remove(index);
						if (cartObservableList.size() == 0) medicineComboBox.getSelectionModel().clearSelection();
						if (cartObservableList.size() == 0) residentComboBox.getSelectionModel().clearSelection();
						medicinesTabView.getColumns().get(0).setVisible(false);
						medicinesTabView.getColumns().get(0).setVisible(true);
						
					}
					
					else if (selectedItem.getStock() < (int) quantitySlider.getValue()) {
						for (Medicines medicinelist : medicineList) {	
							int totalavailable = selectedItem.getStock();
					 if((int) quantitySlider.getValue() >= totalavailable)		 {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Not enough stock available");
					alert.setContentText("Ooops, there was an error!");
					alert.showAndWait();
					medicinesTabView.getColumns().get(0).setVisible(false);
					medicinesTabView.getColumns().get(0).setVisible(true);

					break;
				}

					else ;
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Not enough stock available");
						alert.setContentText("Ooops, there was an error!");
						alert.showAndWait();
						medicinesTabView.getColumns().get(0).setVisible(false);
						medicinesTabView.getColumns().get(0).setVisible(true);}}});
				
		        
		       
		        doctorComboBox.setOnAction((event) -> {
		            int selectedIndex = doctorComboBox.getSelectionModel().getSelectedIndex();
		            Doctor selectedItem = doctorComboBox.getSelectionModel().getSelectedItem();						
		        });    

					

           				
			
			medicinesTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue != null) {
					int index = 0;
					for (Medicines medicineList : medicineList) {
						if (medicineList.getMName().equals(newValue.getName())) {
							medicineComboBox.getSelectionModel().select(index);
							doctorComboBox.getSelectionModel().select(index);
							residentComboBox.getSelectionModel().select(index);
							quantitySlider.setValue(newValue.getQuantity());
							break;
						}
						index++;
					}
				}
			});
			
			medicinesTabView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue != null) {
					int index = 0;
					for (Medicines medicineList : medicineList) {
						if (medicineList.getMName().equals(newValue.getMName())) {
							medicineComboBox.getSelectionModel().select(index);
							quantitySlider.setValue(newValue.getStock());
							break;
						}
						index++;
					}
				}
			
					  
			});

			
			removeButton.setOnAction(event -> {
				int index = medicinesTableView.getSelectionModel().getFocusedIndex();
				if (cartObservableList.size() > index) cartObservableList.remove(index);
				quantitySlider.setValue(0);
				if (cartObservableList.size() > index) cartObservableList.remove(index);
				if (cartObservableList.size() == 0) residentComboBox.getSelectionModel().clearSelection();
				if (cartObservableList.size() == 0) medicineComboBox.getSelectionModel().clearSelection();
			});

			reorder.setOnAction(event -> {
				 Medicines selectedItem = medicineComboBox.getSelectionModel().getSelectedItem();
				 Resident selectedResident = residentComboBox.getSelectionModel().getSelectedItem();
					selectedItem.stockProperty();
					selectedItem.setStock(selectedItem.getStock() + (int) quantitySlider.getValue());
					int index = medicinesTableView.getSelectionModel().getFocusedIndex();
					if (cartObservableList.size() > index) cartObservableList.remove(index);
					if (cartObservableList.size() == 0) medicineComboBox.getSelectionModel().clearSelection();
					if (cartObservableList.size() == 0) residentComboBox.getSelectionModel().clearSelection();
					medicinesTabView.getColumns().get(0).setVisible(false);
					medicinesTabView.getColumns().get(0).setVisible(true);
				});
			
			logout.setOnAction(event -> {
				Parent root = null;
				try {
					root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Landing.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        Node node = (Node) event.getSource();
		        Stage stage = (Stage) node.getScene().getWindow();
		        stage.setScene(new Scene(root));
		        stage.setTitle("Medicine Control");
				;});};
				
				protected void notifyList(Medicines changedItem) {
				    int index = medicineList.indexOf(changedItem);
				    if (index >= 0) {
				        medicineList.set(index, null);
				        medicineList.set(index, changedItem);
				    }
				}
				
				
	 @FXML
	    public void loadData2() throws ClassNotFoundException, SQLException {

		
			//creates the 2nd table on the right which displays current real time stock
	        idColumn.setCellValueFactory(new PropertyValueFactory<Medicines,Integer>("id"));
	        nameColumn2.setCellValueFactory(new PropertyValueFactory<Medicines,String>("mName"));
	        stockColumn.setCellValueFactory(new PropertyValueFactory<Medicines,Integer>("stock"));
	    		
	        medicinesTabView.setItems(medicineList);
	        
	        
			}
	 @FXML
	    public void loadData() {
		//create table view
	        nameColumn.setCellValueFactory(new PropertyValueFactory<MedicineCabinet,String>("name"));
	        qtyColumn.setCellValueFactory(new PropertyValueFactory<MedicineCabinet,Integer>("quantity"));

	        

	        
	        medicinesTableView.setItems(cartObservableList);
	 }
	 

	 public ArrayList<Medicines> getMedicine() throws ClassNotFoundException, SQLException{
	            Connection connection = SQLite.dbConnector();
	            Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery("select * from medicine");
	            ArrayList<Medicines> Medi= new ArrayList<>();
	            while (resultSet.next()) {

	            	 Medicines medicine = (new Medicines(resultSet.getInt("id"), resultSet.getString("mName"), resultSet.getInt("stock")));
	            	 Medi.add(medicine);
//	            	   Bindings.bindContent(Medi, medicineList);
	            }

	        return Medi;
	    }
	 public ObservableList<Resident> getUsers() throws ClassNotFoundException, SQLException{
	        
	        
	            Connection connection = SQLite.dbConnector();
	            Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery("select * from users where type = 'RESIDENT'");
	            ObservableList<Resident> Data= FXCollections.observableArrayList();
	            while (resultSet.next()) {

	            	 Resident resident = (new Resident(resultSet.getInt("id"), resultSet.getString("FName"), resultSet.getString("LName"), resultSet.getString("type"), resultSet.getString("gender")));
	            	 Data.add(resident);
   
	        }

	        return Data;
	    }
	 
	 public ObservableList<Doctor> getDoc() throws ClassNotFoundException, SQLException{
	        
	        
         Connection connection = SQLite.dbConnector();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("select * from users where type = 'DOCTOR'");
         ObservableList<Doctor> Doc= FXCollections.observableArrayList();
         while (resultSet.next()) {

         	 Doctor doctor = (new Doctor(resultSet.getInt("id"), resultSet.getString("FName"), resultSet.getString("LName"), resultSet.getString("username"), resultSet.getString("type"), resultSet.getString("gender")));
         	 Doc.add(doctor);

     }

     return Doc;
 }
	 
	 @FXML
	    void logout(MouseEvent event) throws IOException {
	        Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath +"/Landing.fxml"));
	        Node node = (Node) event.getSource();
	        Stage stage = (Stage) node.getScene().getWindow();
	        stage.setScene(new Scene(root));

	    }
	 @FXML
	    void reorder() throws IOException {
		 
	    }
	
	 @FXML
	    void checkout() throws IOException {
		 
	    }
	 @FXML
	    void remove() throws IOException {
		 
	    }
	
	 
	 @FXML
	    void medicineCabinet(MouseEvent event) throws IOException {
	        
	 }
	 
	
	 public void addRes(ArrayList<Resident> Data) {
			residentArray.addAll(Data);
		}

		
		
		}