package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Resident implements Person{
	public ArrayList<Medicines> medicines;
	public ArrayList<Condition> conditions;
	public static String id, Fname,Lname,username,type,gender;
	
	public Resident() {
		medicines=new ArrayList<Medicines>();
		conditions=new ArrayList<Condition>();
	}
	Resident(String ids,String firstN, String lastN, String userName, String types, String genders) {
		Resident.id=ids;
		Resident.Fname=firstN;
		Resident.Lname=lastN;
		Resident.username=userName;
		Resident.type=types;
		Resident.gender=genders;
		
	}
	public void displayMedicines() {
		
		Iterator<Medicines> it = medicines.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().MName);
		}
	}
	public void displayCondition() {
		
		Iterator<Condition> it = conditions.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().CName);
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
	@Override
	public String getName() {
		return Fname;
	}

	@Override
	public String getType() {
		return type;
	}
	
}
