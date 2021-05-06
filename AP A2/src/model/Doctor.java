package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Doctor implements Person{
	public ArrayList<Medicines> medicines;
	public ArrayList<Condition> conditions;
	public static String id, Fname,Lname,username,type,gender;
	
	public Doctor() {
		medicines=new ArrayList<Medicines>();
		conditions=new ArrayList<Condition>();
	}

	Doctor(String ids,String firstN, String lastN, String userName, String types, String genders) {
		Doctor.id=ids;
		Doctor.Fname=firstN;
		Doctor.Lname=lastN;
		Doctor.username=userName;
		Doctor.type=types;
		Doctor.gender=genders;
		
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
	public static String getname(){

		return Fname;
	}
	public void addMedicines(Medicines medicine){

		medicines.add(medicine);
	}
	public void addCondition(Condition condition){

		conditions.add(condition);
	}
	public String getFName() {
		return Fname;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
