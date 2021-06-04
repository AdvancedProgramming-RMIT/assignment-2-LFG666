package jtest;

import static org.junit.Assert.*;

import org.junit.Test;

import model.*;

public class MedicinesTest {

	@Test
	public final void testSupply() {
	Medicines d = new Medicines(14,"Cocaine",20);
	String successMsg = "Resident placed into bed successfully";
	String rejectMsg = "Bed already occupied";
	//expect this to pass
	assertEquals(d.supply(5), successMsg);
	assertEquals(d.supply(3), successMsg);
	//expect this to fail
	assertEquals(d.supply(30), rejectMsg);
	}


}
