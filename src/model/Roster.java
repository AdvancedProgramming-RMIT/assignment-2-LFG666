package model;

import java.sql.Date;
import java.util.Objects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class Roster {
Staff staff;
private StringProperty day = new SimpleStringProperty();
private IntegerProperty Shift = new SimpleIntegerProperty();

public Roster() {

	day.set("");
	Shift.set(0);
	
}


public Roster(Staff s, Integer Shift, String day) {
    setStaff(s);
    this.day.set(day);
	this.Shift.set(Shift);
}

public Roster(Integer Shift, String day) {
    this.day.set(day);
	this.Shift.set(Shift);
}

public Staff getStaff() {
    return staff;
}

public void setStaff(Staff staff) {
    this.staff = staff;
}

public StringProperty getDayProperty() {
    return this.day;
}

public String getDay() {
    return this.day.get();	
    }

public void setDay(String day) {
    this.day.set(day);
}

public IntegerProperty getShiftProperty() {
    return this.Shift;
}
public Integer getShift(){
    return this.Shift.get();
}

public void setShift(Integer Shift) {
    this.Shift.set(Shift);
}

@Override
public String toString() {
    return "Roster{" +
            "staff=" + staff.getFname() +
            ", Day='" + day + '\'' +
            ", Shift=" + Shift +
            '}';
}














}