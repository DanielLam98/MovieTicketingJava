package com.MovieTicketing;
import java.util.*;												//imports all utilities

public class MovieTicketingSystem {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Audience user = new Audience();
		int printRow = 3;
		int printCol = 21;
		int row = 3;
		int col = 7;
		int num = 1;
		int choice;
		long seatNo;
		int count = 63;			//9 Audience already initialized 
		long ID = 0;
		double pricePaid;
		int [][] arrSection = new int[row][col];
		int [] seats1 = new int [21];			//Seats within Balcony
		int [] seats2 = new int [21];			//Seats within Orchestra
		int [] seats3 = new int [21];			//Seats within Wheel chair
		ArrayList<Long> guestInfo = new ArrayList<Long>();				//list for seats
		add9guest(guestInfo);											//initializing 9 guest seats
		ArrayList<String> guestInfo2 = new ArrayList<String>();			//list for guest info
		add9guest2(guestInfo2);											//initializing 9 guests info
		String name = "", phoneNo ="", specialOfferType ="";
		String address = "";
		printMenu(printRow, printCol);
		int i = 0, j =0, k=0;

			for (i = 0; i < arrSection.length; i++) {							//Balcony array initializing
				for (j = 0; j < arrSection[i].length; j++) {
						seats1[k] = num;
						num++;
						k++;
				}
				num+=14;
			}
			k=0;
			num = 8;
			for (i = 0; i < arrSection.length; i++) {								//Orchestra array initializing
				for (j = 0; j < arrSection[i].length; j++) {
					seats2[k] = num;
					num++;
					k++;
				}
				num+=14;
			}
			k=0;
			num = 15;
			for (i = 0; i < arrSection.length; i++) {								//Wheel chair array initializing
				for (j = 0; j < arrSection[i].length; j++) {
					seats3[k] = num;
					num++;
					k++;
				}
				num+=14;
			}
			

		while (true) {
			menu();													//displays menu
			int confirmChoice;
			String selection = input.next();
			switch (selection) {																//switch case using String
			case "A":
			System.out.println("Have you booked a seat with us?");
			System.out.println("1. Yes | 2. No");
			choice = input.nextInt();
			if (choice == 1) {																//if user bought seat, sends them back to menu
				break;
			}
			else if (choice ==2) {
				System.out.println("Which section would you like to sit in?(Balcony, Orchestra, or Wheelchair)");	
				String section = input.next();												//asks user for input
				user.setSection(section);													
				pricePaid = comparePrice(section, seats1, seats2, seats3);					//grabs section input to see which pricePaid is set at
				if (pricePaid == 999)														//user enters wrong input
					break;
				seatNo = input.nextLong();
				if (checkSeat(guestInfo, seatNo) == false)									//checks seat to see if taken
					break;
				else
				user.setSeatNo(seatNo);															//sets seat number in Audience class
				System.out.println("Please enter your information");
				user.inputDetail(ID, name, phoneNo, specialOfferType, address);					//asks user to input person information
				user.setPricePaid(pricePaid);
				user.printDetails();
				confirmChoice = input.nextInt();
					if (confirmChoice == 1) {
						confirmTicket(seatNo, guestInfo);										//asks user to confirm buying the ticket
						String userInfo = user.printInfo();										//creating a string to pass onto the array list below
						guestInfo2.add(userInfo);												//user information is added to the array
					}
					else if (confirmChoice == 2) {
						System.out.println("Heading back to main menu");						//user declines on purchasing the ticket
						break;
					}
				break;
			}
			else System.out.println("Please enter one of the options above. Going back to main menu");		//error handling, if user enters another number other
				break;																						//than 1 or 2
			case "B":
				System.out.println("Have you already booked a seat?");
				System.out.println("1. Yes | 2. No");
				choice = input.nextInt();
				if (choice == 1)
					{
					System.out.println("What seat were you in?");
					seatNo = input.nextLong();											//asks user for input
					checkSeatB(seatNo, guestInfo, guestInfo2);							//compares the seat to the seat the user wants
					}
				break;																	//break restarts the while loop
			case "C":
				System.out.println("Printing out all seats currently taken");
				printSeats(guestInfo);													//calls printSeats method to display all seats taken
				printGuests(guestInfo2);												//calls printGuets method to display all Information on guests
				break;
			case "D":																	
				System.out.println("Exiting system now");
				System.exit(0);															//system exits
				break;																	//break restarts the while loop
			default:
				System.out.println("ERROR! Please enter one of the options(in CAPS)");				//error if user enters an incorrect selection
			}
		}
	}
	public static void menu(){															//method to display Menu
		System.out.println("Select one of the options below");
		System.out.println("A. New Booking");
		System.out.println("B. Changing Seat");
		System.out.println("C. Print all booked seats of each section");
		System.out.println("D. Exit Program");
}
	public static void printMenu(int row, int col) {									//outputs the audience menu for the user to show all seat numbers
		int num =1;																		//this is shown as a 2d array
		int [][] Display = new int[row][col];
		for (int i = 0; i < Display.length; i++) {
		     for (int j = 0; j < Display[i].length; j++) {
		    	 Display[i][j] = num++;
		         System.out.print(Display[i][j] + " , ");
		         
		}
		System.out.println();
		}
	}
	public static double comparePrice(String section, int[] seats1, int [] seats2, int [] seats3) {
		double price;
		if (section.equals("Wheelchair"))
		{
			price = 59.0;																//price changes accordingly
			System.out.println(Arrays.toString(seats3));								//Displays all seats Wheelchair section
			System.out.println("Please pick one of the numbers above");
		}
		else if (section.equals("Orchestra"))
		{
			price = 72.0;																//price changes accordingly
			System.out.println(Arrays.toString(seats2));								//Displays all seats in Orchestra section
			System.out.println("Please pick one of the numbers above");
		}
		else if (section.equals("Balcony"))												//if the section equals balcony then price is changed accordingly
		{
			price = 89.0;
			System.out.println(Arrays.toString(seats1));								//Displays all seats in Balcony section
			System.out.println("Please pick one of the numbers above");
		}
		else { 																			//if user inputs incorrect section will display error
			price= 999;
			System.out.println("Error! Please enter a correct section");
		}
		return price;
	}
	public static void confirmTicket(long seatNo, ArrayList<Long> guestInfo) {			//confirms the ticket is bought and adds the seat number
		guestInfo.add(seatNo);
	}

	public static void add9guest(ArrayList<Long> guestInfo) {
		guestInfo.add(1L);																//initializing information about the seats taken
		guestInfo.add(2L);
		guestInfo.add(3L);
		guestInfo.add(8L);
		guestInfo.add(9L);
		guestInfo.add(10L);
		guestInfo.add(15L);
		guestInfo.add(16L);
		guestInfo.add(17L);
	}
	public static void add9guest2(ArrayList<String> guestInfo2) {
		guestInfo2.add("Daniel 1 Balcony 7788981933 Ashwood 80.10000000000001 Student 0.90");			//initializing information for 3 Audience in 3 sections
		guestInfo2.add("John 2 Balcony 7781231223 Broadmore 80.10000000000001 Student 0.90");			//corresponds with above add9guest2 seat #
		guestInfo2.add("Tom 3 Balcony 7788981933 Ashwood 80.10000000000001 Student 0.90");
		guestInfo2.add("Jerry 4 Orchestra 7784241324 Mapleroad 43.2 Child 0.60");
		guestInfo2.add("Hillary 5 Orchestra 7788981933 Mapleroad 50.4 Senior 0.70");
		guestInfo2.add("Tommy 6 Orchestra 7784241114 Junior chicken 64.8 Student 0.90");
		guestInfo2.add("Connie 7 WheelChair 7789999911 Mapleroad 59 N/A 1");
		guestInfo2.add("Drogo 8 WheelChair 7784232444 ashsmith 53.1 Student 0.90");
		guestInfo2.add("Tom 9 WheelChair 7784241324 Woodwork 59 N/A 1");
	}
	public static boolean checkSeat(ArrayList<Long> guestInfo, long seatNo) {
		for (int i = 0; i < guestInfo.size(); i++) {					//for loop for size of array
			if (seatNo == guestInfo.get(i)){
				System.out.println("The seat is already taken");		//returns false if seat matches a value in the array
				return false;
			}
		}
		System.out.println("The seat is available");					//returns true if array does not contain the seat value
		return true;
	}
	public static void checkSeatB(long seatNo, ArrayList<Long> guestInfo, ArrayList<String> guestInfo2) {
		Scanner input = new Scanner(System.in);
		for (int i = 0; i < guestInfo.size(); i++) {
			if (seatNo == guestInfo.get(i)) {							//compares the seatNo parameter with values within the array
				System.out.println(guestInfo2.get(i));					//outputs to user letting them know the seat is within the system and displays information
				System.out.println("We have your information, where would you like to move your seat to?");
				seatNo = input.nextLong();
					for(int j = 0; j <guestInfo.size(); j++) {
						if (seatNo == guestInfo.get(j)) {				//compares seatNo to values within guestInfo
							System.out.println("Sorry the seat is taken, please try again");
							break;
						}
						else
							guestInfo.remove(j);						//removes the seat from the array
							guestInfo2.remove(j);						//removes the guests information 
							System.out.println("We have removed your booking and gave you a refund, please go back to the menu and book again");
							break;										//ends the for loop 
					}
					break;												//ends the for loop and heads back to menu
			}
			else
				System.out.print("We do not have your seat number");
			break;														//back to menu
		}
	}
	public static void printSeats(ArrayList<Long> guestInfo) {			//prints out all seats currently taken
		for (int i= 0; i < guestInfo.size(); i++) {
			System.out.print(guestInfo.get(i) + " ");
		}
		System.out.println();
	}
	public static void printGuests(ArrayList<String> guestInfo2) {		//prints out information of all guests currently with a seat
		for (int i= 0; i < guestInfo2.size(); i++) {
			System.out.println(guestInfo2.get(i));
		}
		System.out.println();
	}

}
