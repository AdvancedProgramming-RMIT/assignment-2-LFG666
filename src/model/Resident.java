package model;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Resident implements Person{
	public ArrayList<Medicines> medicines;
	public ArrayList<Condition> conditions;
	private StringProperty Fname = new SimpleStringProperty();
	private StringProperty type = new SimpleStringProperty();
	private StringProperty gender = new SimpleStringProperty();
	private StringProperty Lname = new SimpleStringProperty();
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty username = new SimpleStringProperty();
	
	@Override
	public String toString() {  //this is needed for ComboBox
		return 	Fname.get() +","
			+	Lname.get();	
	}
	
	public Resident() {
		medicines=new ArrayList<Medicines>();
		conditions=new ArrayList<Condition>();
		id.set(0);
    	Fname.set("");
    	Lname.set("");
    	type.set("");
    	gender.set("");
    	username.set("");
	}
	public Resident(Integer id,String fname, String lname, String userName, String type, String gender) {
		this.id.set(id);
        this.Fname.set(fname);
        this.Lname.set(lname);
        this.gender.set(gender);
        this.type.set(type);
        this.username.set(userName);
		
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

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	

	public ArrayList<Medicines> getMedicines() {
		return medicines;
	}

	public void setMedicines(ArrayList<Medicines> medicines) {
		this.medicines = medicines;
	}

	public StringProperty getFnameProperty() {
        return this.Fname;
    }
    
    public final String getFname() {
    return this.Fname.get();	
    }

    public final void setFname(String Fname) {
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
	