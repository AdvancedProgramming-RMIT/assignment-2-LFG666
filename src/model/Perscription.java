package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Perscription extends Medicines {
	
	private IntegerProperty dosage = new SimpleIntegerProperty();
	private IntegerProperty times = new SimpleIntegerProperty();
	private StringProperty fName = new SimpleStringProperty();
	private StringProperty lName = new SimpleStringProperty();
	private StringProperty dName = new SimpleStringProperty();
	


	public Perscription() {
		fName.set("");
		lName.set("");
		dName.set("");
		dosage.set(0);
		times.set(0);
	}

	public Perscription(int ids, String MName, int stocK, String fName, String lName, String dName, int dosage, int times) {
		super(ids, MName, stocK);
		this.fName.set(fName);
		this.lName.set(lName);
		this.dName.set(dName);
		this.dosage.set(dosage);
    	this.times.set(times);
	}

	@Override
	public String toString() {
		return  super.toString() + "," + fName.get() + "," + lName.get() + "," + dName.get() + "," + times.get() + "," + dosage.get();
	}
	
	public StringProperty fNameProperty() {
		return this.fName;
	}
	

	public String getFName() {
		return this.fName.get();
	}
	

	public void setFName(String fName) {
		this.fName.set(fName);
	}
	
	public StringProperty lNameProperty() {
		return this.lName;
	}
	

	public String getLName() {
		return this.lName.get();
	}
	

	public void setLName(String lName) {
		this.lName.set(lName);
	}
	
	public StringProperty dNameProperty() {
		return this.dName;
	}
	

	public String getDName() {
		return this.dName.get();
	}
	

	public void setDName(String dName) {
		this.dName.set(dName);
	}
	
	public IntegerProperty dosageProperty() {
		return this.dosage;
	}


	public double getDosage() {
		return this.dosage.get();
	}
	

	public void setDosage( int dosage) {
		this.dosage.set(dosage);
	}
	
	
	
	public IntegerProperty timesProperty() {
		return this.times;
	}
	

	public int getTimes() {
		return this.times.get();
	}
	

	public void setTimes(int times) {
		this.times.set(times);
	}
	
	
	
	
}
