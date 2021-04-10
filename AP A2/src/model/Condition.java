package model;

import java.util.ArrayList;
import java.util.Iterator;
public class Condition {
	

	
		ArrayList<Resident> residents;
		ArrayList<Condition> conditions;

		public Integer id;
		public String CName,type;

		public Condition(){
			residents=new ArrayList<Resident>();
			conditions=new ArrayList<Condition>();
		}
		public Condition( Integer id, String CName,String type) {

			this.id = id;
			this.CName = CName;
			this.type = type;
		}

		public ArrayList<Condition> myConditions() {
			return conditions;
		}

		public void setConditions(ArrayList<Condition> conditions) {
			this.conditions = conditions;
		}
		public void setCName(String CName) {
			this.CName = CName;
		}


		public void addResident(Resident resident){

			residents.add(resident);
		}
		

		public void displayResidents(){

			System.out.println("RESIDENT:");
			Iterator<Resident> it = residents.iterator();
			while (it.hasNext()){
				System.out.println(it.next().getname());
			}
		}

		public ArrayList<Resident> getResidents() {
			return residents;
		}

		public void setStudents(ArrayList<Resident> residents) {
			this.residents = residents;
		}

		
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		
		public String getCName() {
			return CName;
		}

		
		public void dispayConditions(){
			System.out.println(conditions);

		}

		public void addConditions(Condition condition){

			conditions.add(condition);
		}


		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
	}


