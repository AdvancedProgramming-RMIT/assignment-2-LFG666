package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.Objects;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public abstract class Staff {






		private StringProperty Fname = new SimpleStringProperty();
		private StringProperty type = new SimpleStringProperty();
		private StringProperty gender = new SimpleStringProperty();
		private StringProperty Lname = new SimpleStringProperty();
		private IntegerProperty id = new SimpleIntegerProperty();
		private StringProperty username = new SimpleStringProperty();
		protected static ArrayList<Roster> roster;

		
		
		public Staff() {
			id.set(0);
	    	Fname.set("");
	    	Lname.set("");
	    	type.set("");
	    	gender.set("");
	    	username.set("");	
			
		}

		public Staff (Integer id, String fname, String lname, String userName, String type, String gender, ArrayList<Roster> roster)
		{
			this.id.set(id);
	        this.Fname.set(fname);
	        this.Lname.set(lname);
	        this.gender.set(gender);
	        this.type.set(type);
	        this.username.set(userName);
	        Staff.roster = roster;
	        
		}
		
		public String toString() {  //this is needed for ComboBox
			return id.get() + ","
				+	Fname.get() + ","
				+	roster;
		}
		 public static ArrayList<Roster> getRoster() {
		        return roster;
		    }

		    public void setRoster(ArrayList<Roster> roster) {
		        Staff.roster = roster;
		    }
		
		public StringProperty getFnameProperty() {
	        return this.Fname;
	    }
	    
	    public String getFname() {
	    return this.Fname.get();	
	    }

	    public void setFname(String Fname) {
	        this.Fname.set(Fname);
	    }
		

		 public StringProperty getlnameProperty() {
		        return this.Lname;
		    }
		    
		    public String getLname() {
			    return this.Lname.get();	
			    }

		    public void setLname(String Lname) {
		        this.Lname.set(Lname);
		    }

		    public IntegerProperty getidProperty() {
		        return this.id;
		    }
		    public Integer getId(){
		        return this.id.get();
		    }

		    public void setId(Integer id) {
		        this.id.set(id);
		    }

		 public StringProperty getusernameProperty() {
		        return this.username;
		    }
		    
		    public String getUsername() {
			    return this.username.get();	
			    }


		    public void setUsername(String username) {
		        this.username.set(username);
		    }
		  public StringProperty gettypeProperty() {
		        return this.type;
		    }
		    
		    public String getType() {
			    return this.type.get();	
			    }


		    public void setType(String type) {
		        this.type.set(type);
		    }

		    public StringProperty getgenderProperty() {
		        return this.gender;
		    }
		    
		    public String getGender() {
			    return this.gender.get();	
			    }

		    public void setGender(String gender) {
		        this.gender.set(gender);
		    }
		   
}
