package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Data {

	    String user;
		String pass;
		private StringProperty fname = new SimpleStringProperty();
		private StringProperty type = new SimpleStringProperty();
		private StringProperty gender = new SimpleStringProperty();
		private StringProperty lname = new SimpleStringProperty();
		private IntegerProperty id = new SimpleIntegerProperty();

		@Override
		public String toString() {  //this is needed for ComboBox
			return id.get() + ","
				+	fname.get() +","
				+	type.get() + ","
				+	lname.get() + ","
				+	gender.get();		
		}
	    public Data(){
	    	id.set(0);
	    	fname.set("");
	    	lname.set("");
	    	type.set("");
	    	gender.set("");
	        user=" ";
	        pass=" ";



	    }
	    public Data(Integer id,String Fname,String Lname,String type,String gender){
	        this.id.set(id);
	        this.fname.set(Fname);
	        this.lname.set(Lname);
	        this.gender.set(gender);
	        this.type.set(type);
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

	    public String getUser() {
	        return user;
	    }

	    public void setUser(String user) {
	        this.user = user;
	    }

	    public String getPass() {
	        return pass;
	    }

	    public void setPass(String pass) {
	        this.pass = pass;
	    }

	    public StringProperty getFnameProperty() {
	        return this.fname;
	    }
	    
	    public String getFname() {
	    return this.fname.get();	
	    }

	    public void setFname(String fname) {
	        this.fname.set(fname);
	    }

	    public StringProperty getLnameProperty() {
	        return this.lname;
	    }
	    
	    public String getLname() {
		    return this.lname.get();	
		    }

	    public void setLname(String lname) {
	        this.lname.set(lname);
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

	    public StringProperty getGenderProperty() {
	        return this.gender;
	    }
	    
	    public String getGender() {
		    return this.gender.get();	
		    }

	    public void setGender(String gender) {
	        this.gender.set(gender);
	    }

	   
}
