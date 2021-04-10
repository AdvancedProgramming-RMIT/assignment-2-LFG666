package model;
public class Data {
	 Integer id;
	    String user;
		String pass;
		String fname;
		String lname;
		String type;
		String gender;


	    public Data(){
	        user=" ";
	        pass=" ";
	        fname=" ";
	        lname=" ";
	        type=" ";


	    }
	    public Data(Integer id,String Fname,String Lname,String type,String gender){
	        this.id=id;
	        this.fname=Fname;
	        this.lname=Lname;
	        this.gender=gender;
	        this.type=type;
	    }

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
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

	    public String getFname() {
	        return fname;
	    }

	    public void setFname(String fname) {
	        this.fname = fname;
	    }

	    public String getLname() {
	        return lname;
	    }

	    public void setLname(String lname) {
	        this.lname = lname;
	    }


	    public String getType() {
	        return type;
	    }

	    public void setType(String type) {
	        this.type = type;
	    }

	    public String getGender() {
	        return gender;
	    }

	    public void setGender(String gender) {
	        this.gender = gender;
	    }

	   
}
