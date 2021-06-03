package model;



public class Admin extends Staff implements Person{ 


	@Override
	public String toString() {  //this is needed for ComboBox
		return 	super.toString();	
	}
	public Admin() {


	}

	public Admin(Integer id, String fname, String lname, String userName, String type,
			String gender) {
		super(id, fname, lname, userName, type, gender, getRoster());

	}


	@Override
	public String getName() {

		return null;
	}

}


