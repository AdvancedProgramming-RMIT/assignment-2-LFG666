package jtest;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Bed;
import model.Resident;

public class BedTest {
	Bed one = new Bed("1");
	Bed two = new Bed("2");
	Bed three = new Bed("3");
	Bed four = new Bed("4");
	String successMsg = "Resident placed into bed successfully";
	String rejectMsg = "Bed already occupied";
	Resident r = new Resident();
	Resident b = new Resident();
	Resident h = new Resident();	
	

	@Test
	public void testIsBedAvailable() {
 

		one.setResident(r);
		two.setResident(b);
		
		
		//see if these beds are available
		//these should suceed
		assertEquals(three.isBedAvailable(), successMsg);
		assertEquals(four.isBedAvailable(), successMsg);
		two.setResident(h);
		//this should fail
		assertEquals(two.isBedAvailable(), rejectMsg);
		assertEquals(one.isBedAvailable(), rejectMsg);

	}

}
