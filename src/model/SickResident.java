package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SickResident  {
	private StringProperty CNamec = new SimpleStringProperty(); 
	private StringProperty Typec = new SimpleStringProperty();
	private IntegerProperty id2 = new SimpleIntegerProperty();
	private StringProperty Fname2 = new SimpleStringProperty();
	private StringProperty Lname2 = new SimpleStringProperty();
	private StringProperty gender2 = new SimpleStringProperty();



	@Override
	public String toString() {
		return  id2.get() + "," + Fname2.get() + "," + Lname2.get() + "," + gender2.get() + "," + Typec.get() + "," + CNamec.get();
	}

	public SickResident(){
		id2.set(0);
		Fname2.set("");
		Lname2.set("");
		gender2.set("");
		Typec.set("");
		CNamec.set("");


	}
	public SickResident(Integer ids, String firstN, String lastN, String genders, String Type, String CName) {


		this.Typec.set(Type);
		this.CNamec.set(CName);
		this.id2.set(ids);
		this.Fname2.set(firstN);
		this.Lname2.set(lastN);
		this.gender2.set(genders);

	}

	public StringProperty CNamecProperty() {
		return this.CNamec;
	}


	public final String getCNamec() {
		return this.CNamecProperty().get();
	}


	public final void setCNamec(final String CNamec) {
		this.CNamecProperty().set(CNamec);
	}


	public StringProperty TypecProperty() {
		return this.Typec;
	}


	public final String getTypec() {
		return this.TypecProperty().get();
	}


	public final void setTypec(final String Typec) {
		this.TypecProperty().set(Typec);
	}

	public IntegerProperty id2Property() {
		return this.id2;
	}


	public final int getId2() {
		return this.id2Property().get();
	}


	public final void setId2(final int id2) {
		this.id2Property().set(id2);
	}


	public StringProperty Fname2Property() {
		return this.Fname2;
	}


	public final String getFname2() {
		return this.Fname2Property().get();
	}


	public final void setFname2(final String Fname2) {
		this.Fname2Property().set(Fname2);
	}


	public StringProperty Lname2Property() {
		return this.Lname2;
	}


	public final String getLname2() {
		return this.Lname2Property().get();
	}


	public final void setLname2(final String Lname2) {
		this.Lname2Property().set(Lname2);
	}


	public StringProperty gender2Property() {
		return this.gender2;
	}


	public final String getGender2() {
		return this.gender2Property().get();
	}


	public final void setGender2(final String gender2) {
		this.gender2Property().set(gender2);
	}







}
