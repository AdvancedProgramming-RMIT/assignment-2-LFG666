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
public boolean containsText(String key) {
    // your choice whether to use equals or equalsIgnoreCase
    return key.equals(day) 
            || key.equals(Shift.asString()); 

}
// The next 2 methods were needed in order to override Java's standard hashcode to enable doctor shift to work.
@Override
public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    Roster other = (Roster) obj;
    if (Shift != other.Shift)
        return false;
    return true;
}

@Override
public int hashCode() {
	 final int prime = 31;
     int result = 1;
     result = prime * result + Shift.get();
     return result;
}

@Override
public String toString() {
    return "Roster{" +
//            "staff=" + staff.getFname() +
            ", Day='" + day + '\'' +
            ", Shift=" + Shift +
            '}';
}












}