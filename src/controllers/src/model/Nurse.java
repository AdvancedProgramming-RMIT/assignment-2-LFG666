package model;

public class Nurse extends Staff implements Person{

	
	public Nurse(Integer id, String fname, String lname, String userName, String type,
			String gender) {
		super(id, fname, lname, userName, type, gender, getRoster());

	}
	@Override
	public String toString() {  //this is needed for ComboBox
		return 	super.toString();	
	}

	public String getName() {

		return null;
	}

}
