package model;

import java.util.ArrayList;
import java.util.Iterator;


public class Medicines {
	ArrayList<Resident> residents;
	ArrayList<Nurse> nurses;
	ArrayList<Doctor> doctors;

	public Integer id,dosage,times;
	public String MName,ADoctor,ANurse,type;

	public Medicines(){
		residents=new ArrayList<Resident>();
		nurses=new ArrayList<Nurse>();
		doctors=new ArrayList<Doctor>();
	}
	public Medicines( Integer id, String MName, String ADoctor, String ANurse, Integer dosage, String type, Integer times) {

		this.id = id;
		this.MName = MName;
		this.ADoctor = ADoctor;
		this.ANurse = ANurse;
		this.type = type;
		this.dosage = dosage;
		this.times=times;
	}


	public void setMName(String MName) {
		this.MName = MName;
	}


	public void addResidents(Resident resident){

		residents.add(resident);
	}
	public void assignDoctor(Doctor doctor){

		doctors.add(doctor);
	}
	public void assignNurse(Nurse nurse){

		nurses.add(nurse);
	}
	
	public void getDoctor(){

		System.out.println("DOCTORS:");
		Iterator<Doctor> it = doctors.iterator();
		while (it.hasNext()){
			System.out.println(it.next().getName());
		}
	}
	public void getTeacher(){

		System.out.println("NURSES:");
		Iterator<Nurse> it = nurses.iterator();
		while (it.hasNext()){
			System.out.println(it.next().getName());
		}
	}
	
	public void displayResidents(){

		System.out.println("RESIDENTS:");
		Iterator<Resident> it = residents.iterator();
		while (it.hasNext()){
			System.out.println(it.next().getName());
		}
	}

	public ArrayList<Resident> getResidents() {
		return residents;
	}

	public void setResidents(ArrayList<Resident> residents) {
		this.residents = residents;
	}

	public ArrayList<Doctor> getDoctors() {
		return doctors;
	}
	public ArrayList<Nurse> getNurses() {
		return nurses;
	}

	public void setDoctors(ArrayList<Doctor> doctors) {
		this.doctors = doctors;
	}
	public void setNurses(ArrayList<Nurse> nurses) {
		this.nurses = nurses;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDosage() {
		return dosage;
	}

	public void setDosage(Integer dosage) {
		this.dosage = dosage;
	}


	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public String getMName() {
		return MName;
	}

	public String getADoctor() {
		return ADoctor;
	}

	public void setADoctor(String aDoctor) {
		this.ADoctor = aDoctor;
	}
	public String getANurse() {
		return ANurse;
	}

	public void setANurse(String aNurse) {
		this.ANurse = aNurse;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
