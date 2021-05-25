package model;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Room {

	private StringProperty idroom = new SimpleStringProperty(); 
	Bed bed;
	
	private ArrayList<Bed> bedList;
	
	public Room() {
	idroom.set("");
	}
	
	public Room(Bed b, String id) {
		setBed(b);
		this.idroom.set(id);
	}
	
	public Room(String id) {
		this.idroom.set(id);
		bedList = new ArrayList<Bed>();
	}

	public Bed getBed() {
		return bed;
	}
	
	public void setBed(Bed bed) {
		this.bed = bed;
	}
	
	
	@Override
	public String toString() {  //this is needed for ComboBox
		return 	idroom.get();	
	}
	
	public String getIdRoom() {
		return this.idroom.get();
	}

	public void setIdRoom(String idroom) {
		this.idroom.set(idroom);
	}

	public StringProperty idRoomProperty() {
		return this.idroom;
	}
	
	public ArrayList<Bed> getBeds() {
		return bedList;
	}

	public void setBed(ArrayList<Bed> bed) {
		this.bedList = bed;
	}
}
