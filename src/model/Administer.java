package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Administer {

	private StringProperty dname = new SimpleStringProperty(); 
	private StringProperty rname = new SimpleStringProperty(); 
	private StringProperty mname = new SimpleStringProperty(); 
	private IntegerProperty dosage = new SimpleIntegerProperty();
	private IntegerProperty times = new SimpleIntegerProperty();



	
	public Administer() {
		mname.set("");
		dname.set("");
		rname.set("");
		dosage.set(0);
		times.set(0);}
	
	
	
	public Administer(String mname, String dname, String rname, int dosage, int times) {
		this.mname.set(mname);
        this.dname.set(dname);
        this.dosage.set(dosage);
    	this.rname.set(rname);
    	this.times.set(times);
		
	}
	
	
	@Override
	public String toString() {  //this is needed for ComboBox
return  mname.get() + "," + dname.get() + "," + rname.get() + "," + dosage.get() + "," + times.get();
	}
	
	public StringProperty mnameProperty() {
		return this.mname;
	}

	
	public String getMname() {
		return this.mname.get();
	}

	public void setMname(String mname) {
		this.mname.set(mname);
	}

	
	public StringProperty dnameProperty() {
		return this.dname;
	}

	
	public String getDname() {
		return this.dname.get();
	}

	public void setDname(String dname) {
		this.dname.set(dname);
	}

	public StringProperty rnameProperty() {
		return this.rname;
	}

	
	public String getRname() {
		return this.rname.get();
	}

	public void setRname(String rname) {
		this.rname.set(rname);
	}

	public IntegerProperty dosageProperty() {
		return this.dosage;
	}
	

	public int getDosage() {
		return this.dosageProperty().get();
	}
	

	public void setDosage(int dosage) {
		this.dosageProperty().set(dosage);
	}
	public IntegerProperty timesProperty() {
		return this.times;
	}
	

	public final int getTimes() {
		return this.timesProperty().get();
	}
	

	public final void setTimes(int times) {
		this.timesProperty().set(times);
	}
	

	
	
}
