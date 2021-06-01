package model;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Doctor extends Staff implements Person{
	public ArrayList<Medicines> medicines;
	public ArrayList<Condition> conditions;

	
	@Override
	public String toString() {  //this is needed for ComboBox
		return this.getFname();
	}
	public Doctor() {
		medicines=new ArrayList<Medicines>();
		conditions=new ArrayList<Condition>();
		
	}
	
	public Doctor(Integer id, String fname, String lname, String userName, String type,
			String gender) {
		super(id, fname, lname, userName, type, gender, getRoster());

	}

public void displayMedicines() {
		
		Iterator<Medicines> it = medicines.iterator(); 
		while (it.hasNext()) {
			System.out.println(it.next().getMName());
		}
	}
	public void displayCondition() {
		
		Iterator<Condition> it = conditions.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().getCname());
		}
	}

	public void setMedicines(ArrayList<Medicines> medicines) {
		this.medicines = medicines;
	}

	

   
		
		@Override
		public String getName() {

			return null;
		}

	}
