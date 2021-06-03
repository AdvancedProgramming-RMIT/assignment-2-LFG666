package model;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class Condition {



	ArrayList<Resident> residents;
	ArrayList<Condition> conditions;

	private StringProperty type = new SimpleStringProperty();
	private StringProperty CName = new SimpleStringProperty();
	private IntegerProperty id = new SimpleIntegerProperty();

	@Override
	public String toString() {  //this is needed for ComboBox
		return 	CName.get() +","
		+	type.get();		
	}

	public Condition(){
		residents=new ArrayList<Resident>();
		conditions=new ArrayList<Condition>();
		id.set(0);
		CName.set("");
		type.set("");
	}
	public Condition( Integer id, String CName,String type) {

		this.id.set(id);
		this.CName.set(CName);
		this.type.set(type);
	}

	public ArrayList<Condition> myConditions() {
		return conditions;
	}

	public void setConditions(ArrayList<Condition> conditions) {
		this.conditions = conditions;
	}



	public void addResident(Resident resident){

		residents.add(resident);
	}



	public ArrayList<Resident> getResidents() {
		return residents;
	}

	public void setResident(ArrayList<Resident> residents) {
		this.residents = residents;
	}


	public IntegerProperty getIdProperty() {
		return this.id;
	}
	public Integer getId(){
		return this.id.get();
	}

	public void setId(Integer id) {
		this.id.set(id);
	}



	public StringProperty getCnameProperty() {
		return this.CName;
	}

	public String getCname() {
		return this.CName.get();	
	}

	public void setCname(String CName) {
		this.CName.set(CName);
	}


	public void dispayConditions(){
		System.out.println(conditions);

	}

	public void addConditions(Condition condition){

		conditions.add(condition);
	}


	public StringProperty getTypeProperty() {
		return this.type;
	}

	public String getType() {
		return this.type.get();	
	}


	public void setType(String type) {
		this.type.set(type);
	}
}


