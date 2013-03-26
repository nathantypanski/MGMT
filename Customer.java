/*
 * Program Name:   MGMT
 * School:         Patrick Henry High School
 * Computer used:  home desktop
 *                 school computer
 * IDE used:       Notepad++ text editor (Windows)
 *                 medit text editor (GNU/Linux)
 */

import java.lang.String;

/**
 * The Customer data type for the <code>MGMT</code> class. Stores and provides
 * methods for reading/modifying all the information on a single customer.
 *
 * @author   Nathan Typanski
 * @version  1.0 20 March 2010
 */
public class Customer
{
	/*******************************************
	   Global variables
	 *******************************************/
	
	// customer's name
	String name;
	// unique ID held only by this customer
	int customerID;
	// single unique ID for the next customer to be added. Should always be greater than the highest customerID
	static int customerIDtotal;
	// customer's email
	String email;
	// customer's phone number
	String phoneNumber;
	// customer's shipping address
	String shippingAddress;
	// customer's shipping state
	String shippingState;
	// customer's shipping city
	String shippingCity;
	// customer's shipping zip code
	String shippingZip;
	// customer's billing address
	String billingAddress;
	// customer's billing State
	String billingState;
	// customer's billing city
	String billingCity;
	// customer's billing zip code
	String billingZip;
	// customer's comment
	String comment;
	
	/*******************************************
	   Constructors
	 *******************************************/
	
	/**
	 * Creates a new customer with the default name "New Customer". Sets
	 * up all other variables with default values.
	 */
	public Customer()
	{
		customerID = customerIDtotal;
		customerIDtotal++;
		name = "New Customer";
		email = "";
		phoneNumber = "";
		shippingAddress = "";
		shippingState = "";
		shippingCity = "";
		shippingZip ="";
		billingAddress = "";
		billingState = "";
		billingCity = "";
		billingZip = "";
		comment = "";
	}
	
	/**
	 * Creates a new customer with the custom name <code>name</code>. Sets
	 * up all other variables with default values.
	 */
	public Customer(String name)
	{
		customerID = customerIDtotal;
		customerIDtotal++;
		setName(name);
		email = "";
		phoneNumber = "";
		shippingAddress = "";
		shippingState = "";
		shippingCity = "";
		shippingZip = "";
		billingAddress = "";
		billingState = "";
		billingCity = "";
		billingZip = "";
		comment = "";
	}
	
	/*******************************************
	   Accessors
	 *******************************************/
	
	/**
	 * Returns the name of this customer when a customer is printed as a
	 * String.
	 *
	 * @return  name of this customer
	 */
	public String toString()
	{
		return name;
	}
	
	/**
	 * Returns this name of this customer.
	 *
	 * @return  name of this customer
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Returns the unique customerID for this customer.
	 *
	 * @return  unique customerID for this customer
	 */
	public int getCustomerID()
	{
		return customerID;
	}
	
	/**
	 * Returns the static customerID for the next customer to be created.
	 *
	 * @return  static customerID for the next customer to be created
	 */
	public int getCustomerIDtotal()
	{
		return customerIDtotal;
	}
	
	/**
	 * Returns the email of this customer.
	 *
	 * @return  email of this customer
	 */
	public String getEmail()
	{
		return email;
	}
	
	/**
	 * Returns the phone number of this customer.
	 *
	 * @return  phone number of this customer
	 */
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
	
	/**
	 * Returns the shipping address of this customer.
	 *
	 * @return  shipping address of this customer
	 */
	public String getShippingAddress()
	{
		return shippingAddress;
	}
	
	/**
	 * Returns the shipping state of this customer.
	 *
	 * @return  shipping state of this customer
	 */
	public String getShippingState()
	{
		return shippingState;
	}
	
	/**
	 * Returns the shipping city of this customer.
	 *
	 * @return  shipping city of this customer
	 */
	public String getShippingCity()
	{
		return shippingCity;
	}
	
	/**
	 * Returns the shipping zip code of this customer.
	 *
	 * @return  shipping zip code of this customer
	 */
	public String getShippingZip()
	{
		return shippingZip;
	}
	
	/**
	 * Returns the billing address of this customer
	 *
	 * @return  billing address of this customer
	 */
	public String getBillingAddress()
	{
		return billingAddress;
	}
	
	/**
	 * Returns the billing state of this customer.
	 *
	 * @return  billing state of this customer
	 */
	public String getBillingState()
	{
		return billingState;
	}
	
	/**
	 * Returns the billing city of this customer.
	 *
	 * @return  billing city of this customer
	 */
	public String getBillingCity()
	{
		return billingCity;
	}
	
	/**
	 * Returns the billing zip code of this customer.
	 *
	 * @return  billing zip code of this customer
	 */
	public String getBillingZip()
	{
		return billingZip;
	}
	
	/**
	 * Gets the comment for this customer.
	 *
	 * @return  comment for this customer
	 */
	public String getComment()
	{
		return comment;
	}
	
	/*******************************************
	   Mutators
	 *******************************************/
	
	/**
	 * Sets a new name for this customer
	 *
	 * @param newName  new name for this customer
	 */
	public void setName(String newName)
	{
		name = newName;
	}
	
	/**
	 * Sets a new <code>customerID</code> for this customer and ensures that
	 * <code>customerIDtotal</code> is greater than the current
	 * <code>customerID</code>.
	 *
	 * @param newCustomerID  new name for this customer
	 */
	public void setCustomerID(int newCustomerID)
	{
		customerID = newCustomerID;
		if(customerID > customerIDtotal)
			customerIDtotal = customerID + 1;
	}
	
	/**
	 * Sets a new email address for this customer.
	 *
	 * @param newEmail  new email address for this customer
	 */
	public void setEmail(String newEmail)
	{
		email = newEmail;
	}
	
	/**
	 * Sets a new phone number for this customer.
	 *
	 * @param newPhoneNumber  new phone number for this customer
	 */
	public void setPhoneNumber(String newPhoneNumber)
	{
		phoneNumber = newPhoneNumber;
	}
	
	/**
	 * Sets a new shipping address for this customer.
	 *
	 * @param newShippingAddress  new shipping address for this customer
	 */
	public void setShippingAddress(String newShippingAddress)
	{
		shippingAddress = newShippingAddress;
	}
	
	/**
	 * Sets a new shipping state for this customer.
	 *
	 * @param newShippingState  new shipping state for this customer
	 */
	public void setShippingState(String newShippingState)
	{
		shippingState = newShippingState;
	}
	
	/**
	 * Sets a new shipping city for this customer.
	 *
	 * @param newShippingCity  new shipping city for this customer
	 */
	public void setShippingCity(String newShippingCity)
	{
		shippingCity = newShippingCity;
	}
	
	/**
	 * Sets a new shipping zip code for this customer.
	 *
	 * @param newShippingZip  new shipping zip code for this customer
	 */
	public void setShippingZip(String newShippingZip)
	{
		shippingZip = newShippingZip;
	}
	
	/**
	 * Sets a new billing address for this customer
	 *
	 * @param newBillingAddress  new billing address for this customer
	 */
	public void setBillingAddress(String newBillingAddress)
	{
		billingAddress = newBillingAddress;
	}
	
	/**
	 * Sets a new billing state for this customer
	 *
	 * @param newBillingState  new billing state for this customer
	 */
	public void setBillingState(String newBillingState)
	{
		billingState = newBillingState;
	}
	
	/**
	 * Sets a new billing city for this customer.
	 *
	 * @param newBillingCity  new billing city for this customer
	 */
	public void setBillingCity(String newBillingCity)
	{
		billingCity = newBillingCity;
	}
	
	/**
	 * Sets a new billling zip code for this customer.
	 *
	 * @param newBillingZip  new billing zip code for this customer
	 */
	public void setBillingZip(String newBillingZip)
	{
		billingZip = newBillingZip;
	}
	
	/**
	 * Sets a new comment for this customer.
	 *
	 * @param newComment  new comment for this customer
	 */
	public void setComment(String newComment)
	{
		comment = newComment;
	}
}