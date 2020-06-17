package com.MovieTicketing;				//by Daniel Lam
import java.util.*;						//import all utilities 

public class Audience {
	Scanner input = new Scanner(System.in);			
	public String name = "";				//initialized all values to either 0 or ""
	public long ID = 0; 					//except for specialoffer which starts at 1
	public String section = "";
	public long seatNo = 0;
	public String phoneNo = "";
	public String address = "";
	public double pricePaid = 0;
	public String specialOfferType = "";
	public double specialoffer = 1;
	public Audience() {
		// default constructor
	}
	public Audience(String name, long ID, String section, long seatNo, String phoneNo, String address, double pricePaid,
			String specialOfferType, double specialoffer) {
		this.name=name;
		this.ID=ID;
		this.section= section;
		this.seatNo = seatNo;
		this.phoneNo = phoneNo;
		this.address = address;
		this.pricePaid = pricePaid;
		this.specialOfferType = specialOfferType;
		this.specialoffer = specialoffer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getID() {
		return ID;
	}
	public void setID(long ID) {
		this.ID = ID;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		
		this.section = section;
	}
	public long getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(long seatNo) {												//checks if isValidSection is true to return seatNo
		if (isValidSection(section) == true)
			this.seatNo = seatNo;
		else
			System.out.print("Section is not valid");									//user input wrong Section
			return;

	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getPricePaid() {
		return pricePaid;
	}
	public void setPricePaid(double pricePaid) {
		System.out.println("Price before Special offer: " + pricePaid);							//displays price before special offer application
		pricePaid = pricePaid * applyOffer();													//applies the special offer to price
		this.pricePaid = pricePaid;
		Math.round(pricePaid);																	//rounds the price to nearest float
		System.out.println("With Special offer(if applicable): $" + this.pricePaid);			//displays price after special offer applied
	}
	
	
	public double getSpecialoffer() {
		return specialoffer;
	}
	public void setSpecialoffer(double specialoffer) {											//displays rate if specialOfferType is valid
		this.specialoffer = specialoffer;
		System.out.println(this.specialoffer);
	}
	public String getSpecialOfferType() {
		return specialOfferType;
	}
	public void setSpecialOfferType(String specialOfferType) {									//sets specialOfferType (Balcony,Orchestra or Wheelchair)
		this.specialOfferType = specialOfferType;
	}
	
	
	public boolean isValidSection(String section) {															//validates section value
		if (section.equals("Wheelchair") || section.equals("Orchestra") || section.equals("Balcony"))
			return true;
		else
			return false;
	}
	public void inputDetail(long ID, String name, String phoneNo, String specialOfferType, String address) {		//asks user for input
		System.out.println("Please enter your ID number, name, phone, special offers, and address");				//and sets all variables
		ID = input.nextLong();
		setID(ID);
		name = input.next();
		setName(name);
		phoneNo= input.next();
		setPhoneNo(phoneNo);
		specialOfferType = input.next();
		setSpecialOfferType(specialOfferType);
		address = input.next();
		setAddress(address);
	}
	public void printDetails() {						//displays information on guest to screen
		System.out.println("ID: " + this.ID + "\nname: " + this.name + " \nsection: " + this.section + "\nseat Number: " + this.seatNo + "\nphone number " + 
		this.phoneNo + "\nprice paid: " + this.pricePaid + "\nspecial Offer Type: " + this.specialOfferType + "\naddress: " + this.address);
		System.out.println("\nPlease confirm your information and purchase");
		System.out.println("1. Yes | 2. No");
	}
	
	public double applyOffer() {						//compares specialOfferType and sees if a discount can be applicable
		double value = 1;								//else value stays at 1
		if (specialOfferType.equals("Student")) {
			value = 0.90;
		}
		else if (specialOfferType.equals("Senior")){
			value = 0.70;
		}
		else if (specialOfferType.equals("Child")) {
			value = 0.60;
		}
		return value;
	}
	public String printInfo() {								//used for guest information list
		return (name + " " + ID + " " + section + " " + phoneNo + " " + address + " " + pricePaid + " " + specialOfferType + " " + applyOffer());
	}
}
