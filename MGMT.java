/*
 * Program Name:   MGMT
 * School:         Patrick Henry High School
 * Computer used:  home desktop
 *                 school computer
 * IDE used:       Notepad++ text editor (Windows)
 *                 medit text editor (GNU/Linux)
 */

import java.util.ArrayList;
import java.io.*;
import java.lang.String;
import java.util.Collections;

/**
 * A class that allows the user to create, organize, and modify a list of
 * customers and their associated information using a command-line interface.
 * The user's command-line window should be at least 25 characters tall and 75
 * characters wide for maximum compatiblity.
 *
 * @author   Nathan Typanski
 * @version  1.0 20 March 2010
 */
public class MGMT
{
	/**
	 * Stores an <code>ArrayList</code> of the <code>Customer</code>
	 * datatype to be handled by the program.
	 */
	public static ArrayList<Customer> customers = new ArrayList<Customer>();
	
	/*******************************************
	   Basic user input/output methods
	 *******************************************/
	
	/**
	 * Clears the screen by displaying many newline characters.
	 */
	public static void clearScreen()
	{
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	
	/** 
	 * Gets user input for an integer until presented with a valid number.
	 *
	 * @param minValid  The minimum valued integer accepted for input
	 * @param maxValid  The maximum valued integer accepted for input
	 * @return          An integer between <code>minValid</code> and
	 *                  <code>maxValid</code> (inclusive)
	 */
	public static int inputInt(int minValid, int maxValid) throws IOException
	{
		// open a new input reader
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		// stores the final integer to return
		int returnInt;
		
		// Keep getting inputs until a valid integer between or equal to minValid and maxValid is entered
		do{
			try
			{
				System.out.print("> ");
				returnInt = Integer.parseInt(input.readLine());
			}
			catch (NumberFormatException e)
			{
				returnInt = inputInt(minValid, maxValid);
			}
		}
		while(returnInt < minValid || returnInt > maxValid);
		
		return returnInt;
	}
	
	/**
	 * Returns a valid <code>String</code> that can be constrained to 
	 * either allow or deny blank inputs.
	 *
	 * @param allowBlank  <code>true</code> to allow empty inputs, 
	 *                    <code>false</code> to deny
	 * @return            a <code>String</code> that satisfies the
	 *                    <code>allowBlank</code> condition and does not 
	 *                    produce an <code>IOException</code>
	 *
	 */
	public static String inputString(boolean allowBlank) throws IOException
	{
		// open a new input reader
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		// stores the final string to return
		String returnString = new String();
		
		// Continue requesting inputs until a valid string is entered
		if(allowBlank)
		{
			try
			{
				System.out.print("> ");
				returnString = input.readLine();
			}
			catch (IOException e)
			{
				System.out.println("IOException");
				returnString = inputString(allowBlank);
			}
		
			return returnString;
		}
		else
		{
			do
			{
				try
				{
					System.out.print("> ");
					returnString = input.readLine();
				}
				catch (IOException e)
				{
					System.out.println("IOException");
					returnString = inputString(false);
				}
			}while(returnString.length() == 0);
		
			return returnString;
		}
	}
	
	/*******************************************
	   Customer displaying methods 
	 *******************************************/
	
	/**
	 * Prints a list of <code>Customer</code> names to the user that can be
	 * scrolled through using the keyboard. Requires that <code>ArrayList 
	 * customers</code> exist.
	 *
	 * @param customerList  an <code>ArrayList</code> of
	 *                      <code>Customer</code> datatypes to print
	 * @see Customer
	 */
	public static void printCustomers(ArrayList<Customer> customerList) throws IOException
	{
		clearScreen();
		
		//Stores the user input
		String input = new String();
		// Stores the first customer on the upcoming screen to print
		int customerCount = 0;
		// Stores the number of customers printed on the last screen
		int screenCount = 0;
		// Whether or not it is the last screen
		boolean lastScreen;
		// Whether or not it is the first screen
		boolean firstScreen;
		
		// quits when user enters "q"
		while(!(input.equalsIgnoreCase("q")))
		{
			System.out.println("[CUSTOMER PRINTER]\n");
			
			// Stores the index of the last customer to print to the screen, assuming it doesn't exceed the array size
			int maxPrint = customerCount + 19;
			// Resets the number of lines printed to this screen
			screenCount = 0;
			
			// Print customers until 18 are displayed or the customers ArrayList end is reached.
			for(int i = customerCount; i < maxPrint; i++)
			{
				if(!(i == customerList.size()))
				{
					// Prints customer info
					System.out.println(customerList.get(customerCount).getCustomerID() + ". " + customerList.get(customerCount).getName());
					screenCount ++;
					// Ensures customerCount always stores the first customer to print on the upcoming screen
					customerCount = i+1;
				}
				else
				{
					// Ends the loop if the array size is exceeded
					i = maxPrint;
				}
			}
			
			// Sets lastscreen & firstscreen to the correct values depending on which screen was displayed
			if(customerCount == customerList.size())
				lastScreen = true;
			else
				lastScreen = false;
			if(customerCount <= 19)
				firstScreen = true;
			else
				firstScreen = false;
				
			// Notifies the user if the customers ArrayList is empty
			if(customerList.size() == 0)
				System.err.println("[ERROR: no customers found. Create one or restore from data file.]");
			
			// Prints the controls based on which screen was displayed
			System.out.print("\n[CONTROLS] ");
			if(!(lastScreen))
				System.out.print("(n: next screen) ");
			if(!(firstScreen))
				System.out.print("(p: previous screen) ");
			System.out.println("(q: quit)");
			
			// Gets a valid user input based on which screen was displayed
			input = "";
			while(!((input.equalsIgnoreCase("n") &&!(lastScreen)) || (input.equalsIgnoreCase("p") && !(firstScreen)) || input.equalsIgnoreCase("q")))
			{
				input = inputString(false);
			}

			// Displays the previous screen if requested (otherwise, show the next screen or quit);
			if(input.equalsIgnoreCase("p"))
			{
				customerCount -= screenCount + 19;
				if(customerCount < 0)
					customerCount = 0;
			}
			
			clearScreen();
		}
	}
	
	/*******************************************
	   Customer searching/sorting methods
	 *******************************************/
	
	/**
	 * Returns a <code>Customer</code> if the <code>customerID</code> is 
	 * found in the <code>customers ArrayList</code>. If there are multiple
	 * <code>Customer</code> objects with the same <code>customerID</code>
	 * (there shouldn't be), the first one in the <code>ArrayList</code>
	 * will be returned.
	 *
	 * @param i  a potential <code>customerID</code>
	 * @return   a <code>Customer</code> with the <code>customerID i</code>
	 * @exception CustomerNotFoundException
	 * @see      Customer
	 */
	public static Customer findCustomer(int i) throws CustomerNotFoundException
	{
		// simple integer search algorithm
		for(Customer c:customers)
		{
			if(c.getCustomerID() == i)
				return c;
		}
		throw new CustomerNotFoundException();
	}
	
	/**
	 * Returns <code>true</code> if <code>i</code> is a valid
	 * <code>customerID</code> in the <code>customers ArrayList</code>,
	 * otherwise <code>false</code>.
	 *
	 * @param i  a possible <code>customerID</code>
	 * @return   <code>true</code> if <code>i</code> is a valid 
	 *           <code>customerID</code> in the <code>customers
	 *           ArrayList</code>, otherwise <code>false</code>.
	 * @see      Customer
	 */
	public static boolean isCustomer(int i)
	{
		// attempt to find the customerID in the customers ArrayList
		try
		{
			findCustomer(i);
		}
		catch(CustomerNotFoundException e)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Alphabetizes the <code>customers ArrayList</code> by
	 * <code>name</code> field. Uses a bubble sort to make this super
	 * fast.
	 *
	 * @see  Customer
	 */
	public static void alphabetizeCustomers() throws IOException
	{
		// only alphabetize if the array is not empty
		if(customers.size() > 0)
		{
			// keeps track of whether or not the elements been swapped into the correct order
			boolean swapped = false;
		
			clearScreen();
		
			// run bubble sort
			do
			{
				// nothing has been swapped (yet!)
				swapped=false;
				// cycles through customers ArrayList
				for(int i = 0; i < customers.size()-1; i++)
				{
					// swap two customers if they are out of order
					if(customers.get(i).getName().compareToIgnoreCase(customers.get(i+1).getName()) > 0)
					{
						Customer c = customers.get(i);
						customers.set(i, customers.get(i+1));
						customers.set(i+1, c);
						// flag this iteration of the loop as a swap
						swapped = true;
					}
				}
			}while(swapped);
		
			System.out.println("[CUSTOMERS ALPHABETIZED]\n");
			System.out.println("[PRESS ENTER TO CONTINUE]");
			// Get a garbage user input before showing the next screen
			String trash = new String(inputString(true));
		}
		// notify the user if the array was empty
		else
		{
			clearScreen();
			System.out.println("[CUSTOMER ALPHABETIZER]\n");
			System.err.println("[ERROR: no customers found. Create one or restore from data file.]\n");
			System.out.println("[PRESS ENTER TO CONTINUE]");
			// Get a garbage user input before showing the next screen
			String trash = new String(inputString(true));
		}
	}
	
	/**
	 * Displays an interface for searching for and editing a <code>Customer
	 * </code> based on a name.
	 *
	 * @param customerList  a list of <code>Customer</code> objects to
	 * search for.
	 * @see                 Customer
	 */
	public static void searchCustomers(ArrayList<Customer> customerList) throws IOException
	{
		clearScreen();
		
		// Contains the list of customers to display to the user
		ArrayList<Customer> printList = new ArrayList<Customer>();
		
		System.out.println("[CUSTOMER SEARCH]\n");
		System.out.println("Enter a name to search for.");
		
		// Gets user search input
		String searchString = inputString(false);
		
		// If a customer's name contains searchString, add it to the list of customers to be printed
		for(Customer c : customerList)
			if(c.getName().toLowerCase().contains(searchString.toLowerCase().subSequence(0, searchString.length())))
				printList.add(c);
		
		editCustomers(printList);
	}
	
	/*******************************************
	   Saving and loading methods
	 *******************************************/
	
	/**
	 * Saves the <code>customers ArrayList</code> to
	 * <code>CustomerDatabase.txt</code>.
	 *
	 * @see  Customer
	 * @see  Datasaver
	 */
	public static void saveCustomers() throws IOException
	{
		clearScreen();
		// Save the customers to a text file
		Datasaver.saveCustomers(customers);
		System.out.println("[CUSTOMERS SAVED TO CustomerDatabase.txt]\n");
		System.out.println("[PRESS ENTER TO CONTINUE]");
		// Get a garbage user input before showing the next screen
		String trash = new String(inputString(true));
	}
	
	/**
	 * Restores the <code>customers ArrayList</code> from 
	 * <code>CustomerDatabase.txt</code>
	 *
	 * @see  Customer
	 * @see  Datasaver
	 */
	public static void loadCustomers() throws IOException
	{
		clearScreen();
		// clear the current customers ArrayList
		customers.clear();
		// restore customers from CustomerDatabase.txt
		customers = Datasaver.loadCustomers();
		System.out.println("[CUSTOMERS RESOTRED FROM CustomerDatabase.txt]\n");
		System.out.println("[PRESS ENTER TO CONTINUE]");
		// Get a garbage user input before showing the next screen
		String trash = new String(inputString(true));
	}
	
	/*******************************************
	   Customer creation/editing methods
	 *******************************************/
	
	/**
	 * Creates a new <code>Customer</code>, requests a name from the user,
	 * adds it to <code>customers ArrayList</code>, then opens an editor
	 * for the new <code>Customer.
	 *
	 * @see  Customer
	 */
	public static void createCustomer() throws IOException
	{
		clearScreen();
		
		System.out.println("[CUSTOMER CREATOR]\n");
		System.out.println("Enter the name of the customer you would like to create:");
		
		// create a new customer with a user-defined name
		Customer c = new Customer(inputString(false));
		// add the customer to the ArrayList
		customers.add(c);
		// display the edit screen for the customer
		editCustomer(c);
	}
	
	/**
	 * Prints a list of customers to the user and provides for editing an
	 * individual in this list. The user is asked for the
	 * <code>customerID</code> of a customer to edit.
	 *
	 * @param customerList  an <code>ArrayList</code> of
	 *                      <code>Customer</code> objects to edit
	 * @see                 Customer
	 */
	public static void editCustomers(ArrayList<Customer> customerList) throws IOException
	{
		clearScreen();
		
		//Stores the user input
		String input = new String();
		// Stores the first customer on the upcoming screen to print
		int customerCount = 0;
		// Stores the number of customers printed on the last screen
		int screenCount = 0;
		// Stores the integer value of input, if it exists. Otherwise, stores -1
		int inputNum;
		// Whether or not it is the last screen
		boolean lastScreen;
		// Whether or not it is the first screen
		boolean firstScreen;
		
		// quits when user enters "q"
		while(!(input.equalsIgnoreCase("q")))
		{
			System.out.println("[CUSTOMER EDITOR SELECTION]\n");
			
			// Stores the index of the last customer to print to the screen, assuming it doesn't exceed the array size
			int maxPrint = customerCount + 19;
			// Resets the number of lines printed to this screen
			screenCount = 0;
			
			// Print customers until 18 are displayed or the customers ArrayList end is reached.
			for(int i = customerCount; i < maxPrint; i++)
			{
				if(!(i == customerList.size()))
				{
					// Prints customer info
					System.out.println(customerList.get(customerCount).getCustomerID() + ". " + customerList.get(customerCount).getName());
					screenCount ++;
					// Ensures customerCount always stores the first customer to print on the upcoming screen
					customerCount = i+1;
				}
				else
				{
					// Ends the loop if the array size is exceeded
					i = maxPrint;
				}
			}
			
			// Sets lastscreen & firstscreen to the correct values based on which screen was displayed
			if(customerCount == customerList.size())
				lastScreen = true;
			else
				lastScreen = false;
			if(customerCount <= 19)
				firstScreen = true;
			else
				firstScreen = false;
				
			// Notifies the user if the customers ArrayList is empty
			if(customerList.size() == 0)
				System.out.println("[No customers found.]");
			
			// Prints the controls based on which screen was displayed
			System.out.print("\n[CONTROLS] (#: edit customer) ");
			if(!(lastScreen))
				System.out.print("(n: next screen) ");
			if(!(firstScreen))
				System.out.print("(p: previous screen) ");
			System.out.println("(q: quit)");
			
			// Gets a valid user input based on which screen was displayed
			input = "";
			// Resets customerID input
			inputNum = -1;
			while(!((input.equalsIgnoreCase("n") &&!(lastScreen)) || (input.equalsIgnoreCase("p") && !(firstScreen)) || input.equalsIgnoreCase("q") || isCustomer(inputNum)))
			{
				input = inputString(false);
				// Attempt to convert the user input to an integer
				try
				{
					inputNum = Integer.parseInt(input);
				}
				catch(NumberFormatException e)
				{
					inputNum = -1;
				}
			}
			
			// Edits the customer if the user entered a valid CustomerID
			if(isCustomer(inputNum))
			{
				try
				{
					// Edit details for the selected customer
					editCustomer(findCustomer(inputNum));
					// Quit customer editor
					input = "q";
				}
				catch(CustomerNotFoundException e)
				{
					// This should never happen. If it does, this is indicative of a bug somewhere in editCustomers()
					System.out.println("Not a real customer");
				}
			}
			
			// Displays the previous screen if requested (otherwise show the next screen or quit);
			else if(input.equalsIgnoreCase("p"))
			{
				customerCount -= screenCount + 19;
				if(customerCount < 0)
					customerCount = 0;
			}
			
			clearScreen();
		}
	}
	
	/**
	 * Provides a user interface for editing a customer. Displays a menu
	 * with all the editable fields and their current values. Allows the
	 * user to select a field and enter a new value, or to empty the field
	 * by entering a blank input. Note that the <code>customerID</code> is
	 * unique and will never change, and that all fields are strings that
	 * are not checked for validity by the program (a phrase can be used as
	 * a zip code, for example).
	 *
	 * @param c  the <code>customer</code> you would like to edit
	 * @see      Customer
	 * @see      Datasaver
	 */
	public static void editCustomer(Customer c) throws IOException
	{
		// Stores the menu selection for the customer editor
		String editMenuSelection = new String();
		
		// display the customer editor screen until the user enters "q"
		while(!editMenuSelection.equalsIgnoreCase("q"))
		{
			clearScreen();
			
			// display the menu
			System.out.println("[CUSTOMER EDITOR]\n");
			System.out.println("a. Name: " + c.getName());
			System.out.println("   CustomerID: " + c.getCustomerID());
			System.out.println("b. Email: " + c.getEmail());
			System.out.println("c. Phone Number: " + c.getPhoneNumber());
			System.out.println("d. Ship Address: " + c.getShippingAddress());
			System.out.println("e. Ship State: " + c.getShippingState());
			System.out.println("f. Ship City: " + c.getShippingCity());
			System.out.println("g. Ship Zip: " + c.getShippingZip());
			System.out.println("h. Bill Address: " + c.getBillingAddress());
			System.out.println("i. Bill State: " + c.getBillingState());
			System.out.println("j. Bill City: " + c.getBillingCity());
			System.out.println("k. Bill Zip: " + c.getBillingZip());
			System.out.println("l. Comment: " + c.getComment());
			System.out.println("r. remove customer");
			System.out.println("\nEnter the letter for the field you would like to edit\nType q to quit the editor");
			
			// gets user menu input
			editMenuSelection = inputString(true);
			
			// Edit name
			if(editMenuSelection.equalsIgnoreCase("a"))
			{
				clearScreen();
				System.out.println("[NAME EDITOR]\n");
				System.out.println("[CUSTOMER: " + c.getName() + "] [ID: " + c.getCustomerID() + "]");
				System.out.println("Current name: " + c.getName());
				System.out.println("Enter a new name.");
				String newName = new String(inputString(true));
				clearScreen();
				System.out.println("Old name " + c.getName() + " replaced with " + newName + ".");
				c.setName(newName);
				System.out.println("[PRESS ENTER TO CONTINUE]");
				// Get a garbage user input before showing the next screen
				newName = inputString(true);
			}
			// Edit Email
			else if(editMenuSelection.equalsIgnoreCase("b"))
			{
				clearScreen();
				System.out.println("[EMAIL EDITOR]\n");
				System.out.println("[CUSTOMER: " + c.getName() + "] [ID: " + c.getCustomerID() + "]");
				System.out.println("Current email: " + c.getEmail());
				System.out.println("Enter a new email.");
				String newEmail = new String(inputString(true));
				clearScreen();
				System.out.println("Old email " + c.getEmail() + " replaced with " + newEmail + ".");
				c.setEmail(newEmail);
				System.out.println("[PRESS ENTER TO CONTINUE]");
				// Get a garbage user input before showing the next screen
				newEmail = inputString(true);
			}
			// Edit Phone Number
			else if(editMenuSelection.equalsIgnoreCase("c"))
			{
				clearScreen();
				System.out.println("[PHONE NUMBER EDITOR]\n");
				System.out.println("[CUSTOMER: " + c.getName() + "] [ID: " + c.getCustomerID() + "]");
				System.out.println("Current phone number: " + c.getPhoneNumber());
				System.out.println("Enter a new phone number.");
				String newPhone = new String(inputString(true));
				clearScreen();
				System.out.println("Old phone number " + c.getPhoneNumber() + " replaced with " + newPhone + ".");
				c.setPhoneNumber(newPhone);
				System.out.println("[PRESS ENTER TO CONTINUE]");
				// Get a garbage user input before showing the next screen
				newPhone = inputString(true);
			}
			// Edit shipping address
			else if(editMenuSelection.equalsIgnoreCase("d"))
			{
				clearScreen();
				System.out.println("[SHIPPING ADDRESS EDITOR]\n");
				System.out.println("[CUSTOMER: " + c.getName() + "] [ID: " + c.getCustomerID() + "]");
				System.out.println("Current shipping address: " + c.getShippingAddress());
				System.out.println("Enter a new shipping address.");
				String newAddress = new String(inputString(true));
				clearScreen();
				System.out.println("Old shipping address " + c.getShippingAddress() + " replaced with " + newAddress + ".");
				c.setShippingAddress(newAddress);
				System.out.println("[PRESS ENTER TO CONTINUE]");
				// Get a garbage user input before showing the next screen
				newAddress = inputString(true);
			}
			// Edit shipping State
			else if(editMenuSelection.equalsIgnoreCase("e"))
			{
				clearScreen();
				System.out.println("[SHIPPING STATE EDITOR]\n");
				System.out.println("[CUSTOMER: " + c.getName() + "] [ID: " + c.getCustomerID() + "]");
				System.out.println("Current shipping state: " + c.getShippingState());
				System.out.println("Enter a new shipping state.");
				String newState = new String(inputString(true));
				clearScreen();
				System.out.println("Old shipping state " + c.getShippingState() + " replaced with " + newState + ".");
				c.setShippingState(newState);
				System.out.println("[PRESS ENTER TO CONTINUE]");
				// Get a garbage user input before showing the next screen
				newState = inputString(true);
			}
			// Edit shipping city
			else if(editMenuSelection.equalsIgnoreCase("f"))
			{
				clearScreen();
				System.out.println("[SHIPPING CITY EDITOR]\n");
				System.out.println("[CUSTOMER: " + c.getName() + "] [ID: " + c.getCustomerID() + "]");
				System.out.println("Current Shipping City: " + c.getShippingCity());
				System.out.println("Enter a new shipping city.");
				String newCity = new String(inputString(true));
				clearScreen();
				System.out.println("Old shipping city " + c.getShippingCity() + " replaced with " + newCity + ".");
				c.setShippingCity(newCity);
				System.out.println("[PRESS ENTER TO CONTINUE]");
				// Get a garbage user input before showing the next screen
				newCity = inputString(true);
			}
			// Edit shipping zip
			else if(editMenuSelection.equalsIgnoreCase("g"))
			{
				clearScreen();
				System.out.println("[SHIPPING ZIP CODE EDITOR]\n");
				System.out.println("[CUSTOMER: " + c.getName() + "] [ID: " + c.getCustomerID() + "]");
				System.out.println("Current shipping zip: " + c.getShippingZip());
				System.out.println("Enter a new shipping zip code.");
				String newZip = new String(inputString(false));
				clearScreen();
				System.out.println("Old shipping zip " + c.getShippingZip() + " replaced with " + newZip + ".");
				c.setShippingZip(newZip);
				System.out.println("[PRESS ENTER TO CONTINUE]");
				// Get a garbage user input before showing the next screen
				newZip = inputString(true);
			}
			// Edit billing address
			else if(editMenuSelection.equalsIgnoreCase("h"))
			{
				clearScreen();
				System.out.println("[BILLING ADDRESS EDITOR]\n");
				System.out.println("[CUSTOMER: " + c.getName() + "] [ID: " + c.getCustomerID() + "]");
				System.out.println("Current billing address: " + c.getBillingAddress());
				System.out.println("Enter a new billing address");
				String newAddress = new String(inputString(true));
				clearScreen();
				System.out.println("Old billing address " + c.getBillingAddress() + " replaced with " + newAddress + ".");
				c.setBillingAddress(newAddress);
				System.out.println("[PRESS ENTER TO CONTINUE]");
				// Get a garbage user input before showing the next screen
				newAddress = inputString(true);
			}
			// Edit billing state if requested
			else if(editMenuSelection.equalsIgnoreCase("i"))
			{
				clearScreen();
				System.out.println("[BILLING STATE EDITOR]\n");
				System.out.println("[CUSTOMER: " + c.getName() + "] [ID: " + c.getCustomerID() + "]");
				System.out.println("Current billing state: " + c.getBillingState());
				System.out.println("Enter a new billing state");
				String newState = new String(inputString(true));
				clearScreen();
				System.out.println("Old billing state " + c.getBillingState() + " replaced with " + newState + ".");
				c.setBillingState(newState);
				System.out.println("[PRESS ENTER TO CONTINUE]");
				// Get a garbage user input before showing the next screen
				newState = inputString(true);
			}
			// Edit billing city if requested
			else if(editMenuSelection.equalsIgnoreCase("j"))
			{
				clearScreen();
				System.out.println("[BILLING CITY EDITOR]\n");
				System.out.println("[CUSTOMER: " + c.getName() + "] [ID: " + c.getCustomerID() + "]");
				System.out.println("Current billing city: " + c.getBillingCity());
				System.out.println("Enter a new billing city.");
				String newCity = new String(inputString(true));
				clearScreen();
				System.out.println("Old billing city " + c.getBillingCity() + " replaced with " + newCity + ".");
				c.setBillingCity(newCity);
				System.out.println("[PRESS ENTER TO CONTINUE]");
				// Get a garbage user input before showing the next screen
				newCity = inputString(true);
			}
			// Edit billing zip if requested
			else if(editMenuSelection.equalsIgnoreCase("k"))
			{
				clearScreen();
				System.out.println("[BILLING ZIP CODE EDITOR]\n");
				System.out.println("[CUSTOMER: " + c.getName() + "] [ID: " + c.getCustomerID() + "]");
				System.out.println("Current billing zip: " + c.getBillingZip());
				System.out.println("Enter a new billing zip code.");
				String newZip = new String(inputString(true));
				clearScreen();
				System.out.println("Old billing zip " + c.getBillingZip() + " replaced with " + newZip + ".");
				c.setBillingZip(newZip);
				System.out.println("[PRESS ENTER TO CONTINUE]");
				// Get a garbage user input before showing the next screen
				newZip = inputString(true);
			}
			// Edit comment if requested
			else if(editMenuSelection.equalsIgnoreCase("l"))
			{
				clearScreen();
				System.out.println("[COMMENT EDITOR]\n");
				System.out.println("[CUSTOMER: " + c.getName() + "] [ID: " + c.getCustomerID() + "]");
				System.out.println("Current comment: " + c.getComment());
				System.out.println("Enter a new comment.");
				String newComment = new String(inputString(true));
				clearScreen();
				System.out.println("Old comment " + c.getComment() + " replaced with " + newComment + ".");
				c.setComment(newComment);
				System.out.println("[PRESS ENTER TO CONTINUE]");
				// Get a garbage user input before showing the next screen
				newComment = inputString(true);
			}
			// Remove this customer
			else if(editMenuSelection.equalsIgnoreCase("r"))
			{
				clearScreen();
				System.out.println("[CUSTOMER REMOVER]\n");
				System.out.println("[CUSTOMER: " + c.getName() + "] [ID: " + c.getCustomerID() + "]");
				System.out.println("Are you sure you would like to remove this customer?");
				System.out.println("(yes: remove)(anything else: keep customer)");
				if(inputString(true).equalsIgnoreCase("yes"))
				{
					clearScreen();
					customers.remove(customers.indexOf(c));
					System.out.println("[CUSTOMER REMOVER]\n");
					System.out.println("[Customer " + c.getName() + " ID: " + c.getCustomerID() + " removed.]");
					System.out.println("[PRESS ENTER TO CONTINUE]");
					// Get a garbage user input before showing the next screen
					String trash = new String(inputString(true));
					editMenuSelection = "q";
				}
				else
				{
					clearScreen();
					System.out.println("[CUSTOMER REMOVER]\n");
					System.out.println("[Customer " + c.getName() + " ID: " + c.getCustomerID() + " kept.]");
					System.out.println("[PRESS ENTER TO CONTINUE]");
					// Get a garbage user input before showing the next screen
					String trash = new String(inputString(true));
				}
			}
		}
	}
	
	/*******************************************
	   Main method
	 *******************************************/
	
	/**
	 * Provides a user interface for the MGMT customer management software.
	 *
	 * @param args  program arguments passed at the command line. Unused.
	 */
	public static void main(String args[]) throws IOException
	{
		// Stores the user's menu selection. Only valid between 1 and 7.
		int menuSelection = 0;
		
		// Opening screen with program name & information
		clearScreen();
		System.out.println("[MGMT CUSTOMER MANAGMENT SOFTWARE]");
		System.out.println("  author: Nathan Typanski");
		System.out.println("  version: 1.0");
		System.out.println();
		System.out.println("[PRESS ENTER TO CONTINUE]");
		// Get a garbage user input before showing the Main menu
		String trash = new String(inputString(true));
		
		// Displays the main menu. Exits when 8 is entered.
		while(!(menuSelection == 8))
		{
			clearScreen();
			System.out.println("[MAIN MENU]");
			System.out.println();
			System.out.println("1) Create customers");
			System.out.println("2) Print customers");
			System.out.println("3) Edit customers");
			System.out.println("4) Alphabetize customers");
			System.out.println("5) Search customers");
			System.out.println("6) Save customer database");
			System.out.println("7) Restore customer database");
			System.out.println("8) Quit");
			System.out.println();
			
			// Get's the user's input between 1 and 8.
			menuSelection = inputInt(1, 8);
			
			// Adds a new customer to customer ArrayList
			if(menuSelection == 1)
				createCustomer();
			
			// Prints all customers in a nice scrollable interface
			else if(menuSelection == 2)
				printCustomers(customers);
			
			// Allows the user to select and edit a customer based on the customerID
			else if(menuSelection ==3)
				editCustomers(customers);
			
			// Alphabetizes customers using a bubble sort
			else if(menuSelection == 4)
				alphabetizeCustomers();
			
			// Searches customers based on a name
			else if(menuSelection == 5)
				searchCustomers(customers);
			
			// Saves customers to CustomerDatabase.txt
			else if(menuSelection == 6)
				saveCustomers();
			
			// Restores customers from CustomerDatabase.txt
			else if(menuSelection == 7)
				loadCustomers();
		}
	}
}