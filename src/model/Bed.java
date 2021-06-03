package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Bed {



	private StringProperty idbed = new SimpleStringProperty(); 
	private Resident resident;
	private boolean isBedAvail = true;

	public Bed(Resident r, String idbed) {
		setResident(r);
		this.idbed.set(idbed);
	}


	public Bed() {
		idbed.set("");
	}

	public Bed(String idbed) { 
		this.idbed.set(idbed);
	}

	@Override
	public String toString() {  //this is needed for ComboBox
		return 	idbed.get();	
	}

	public String getIdBed() {
		return this.idbed.get();
	}

	public void setIdBed(String idbed) {
		this.idbed.set(idbed);
	}

	public StringProperty idBedProperty() {
		return this.idbed;
	}

	public Resident getResident() {
		return resident;
	}

	public void setResident(Resident resident) {
		this.resident = resident;
		this.setIsBedAvail(false);
	}

	public boolean isBedAvailable() {
		return isBedAvail;
	}

	public void setIsBedAvail(boolean isBedAvail) {
		this.isBedAvail = isBedAvail;
	}




	public void setStyle(String style2) {
		style2 = "-fx-background-color:  WHITE";

	}

}
