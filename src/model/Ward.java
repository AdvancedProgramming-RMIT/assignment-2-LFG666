package model;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ward {

	private StringProperty idward = new SimpleStringProperty(); 
	Room room;
	Bed bed;
	
	ArrayList<Room> roomList;
	
	public Ward() {
		idward.set("");
		}
	
	public Ward(String id) {
		this.idward.set(id);
		roomList = new ArrayList<Room>();
	}
	
	public Ward(Bed b, Room r, String id) {
		setRoom(r);
		setBed(b);
		this.idward.set(id);
	}
	
	public Room getRoom() {
		return room;
	}
	
	public Bed getBed() {
		return bed;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public void setBed(Bed bed) {
		this.bed = bed;
	}
	
	@Override
	public String toString() {  //this is needed for ComboBox
		return 	idward.get();	
	}
	
	public StringProperty idWardProperty() {
		return this.idward;
	}

	public ArrayList<Room> getRoomList() {
		return roomList;
	}

	public void setRoomList(ArrayList<Room> roomList) {
		this.roomList = roomList;
	}
	
	public String getIdWard() {
		return this.idward.get();
	}

	public void setIdWard(String idward) {
		this.idward.set(idward);
	}

	
	
}
