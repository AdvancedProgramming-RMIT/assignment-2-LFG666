package model;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Medicines {
	ArrayList<Resident> residents;


	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty mName = new SimpleStringProperty();
	private IntegerProperty stock = new SimpleIntegerProperty();

	@Override
	public String toString() {
		return  id.get() + ","
			+	mName.get() + ","
			+	stock.get();
	}

	public Medicines(){
		residents=new ArrayList<Resident>();
		id.set(0);
		mName.set("");
		stock.set(0);

	}
	public Medicines(int ids, String MName, int stocK) {


        this.mName.set(MName);
        this.id.set(ids);
    	this.stock.set(stocK);

	}


	public void addResidents(Resident resident){

		residents.add(resident);
	}
	
	
	public void displayResidents(){

		System.out.println("RESIDENTS:");
		Iterator<Resident> it = residents.iterator();
		while (it.hasNext()){
			System.out.println(it.next().getName());
		}
	}

	public ArrayList<Resident> getResidents() {
		return residents;
	}

	public void setResidents(ArrayList<Resident> residents) {
		this.residents = residents;
	}

	
	public StringProperty MNameProperty() {
		return this.mName;
	}
	

	public String getMName() {
		return this.mName.get();
	}
	

	public void setMName(String MName) {
		this.mName.set(MName);
	}
	
	public IntegerProperty idProperty() {
		return this.id;
	}
	

	public int getId() {
		return this.id.get();
	}
	

	public void setId( int ids) {
		this.id.set(ids);
	}
	
	
	
	public IntegerProperty stockProperty() {
		return this.stock;
	}
	

	public int getStock() {
		return this.stock.get();
	}
	

	public void setStock(int stocK) {
		this.stock.set(stocK);
	}
	
	 public double supply(int available) 
		{
			//Checking whether the qty received as argument is less than or equal to stock level
			if(available <= this.stock.get())
			{
				//Decreasing the qty from stock level
				this.setStock(this.getStock() - (available));
				
				//Return the supply amount
				return available;
			}
			else {
				return 0;
			}
		
		}
	 public void add(int ids, String MName, int dosagE, int timeS, int stocK) {
			// TODO Auto-generated method stub
			
		}
	
	
	
}