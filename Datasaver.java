/*
 * Program Name:   MGMT
 * School:         Patrick Henry High School
 * Computer used:  home desktop
 *                 school computer
 * IDE used:       Notepad++ text editor (Windows)
 *                 medit text editor (GNU/Linux)
 */

import java.io.*;
import java.util.ArrayList;

/**
 * A class that saves and restores the customers and their information from
 * the <code>customers</code> ArrayList in the <code>MGMT</code> class, but
 * should work for any <code>ArrayList</code> of the <code>Customer</code>
 * datatype.
 *
 * @author   Nathan Typanski
 * @version  1.0 20 March 2010
 */
public class Datasaver
{
	/**
	 * Saves the customers in <code>list</code> to the text file
	 * <code>CustomerDatabase.txt</code>. Each new customer is preceeded by
	 * the tag <code>&&NEWCUSTOMER</code> and ends with
	 * <code>&&ENDCUSTOMER</code>. The document ends with the line
	 * <code>&&ENDFILE</code>. Customer data is stored in the following
	 * order:
	 *   name
	 *   customerID
	 *   email
	 *   phonenumber
	 *   shippingAddress
	 *   shippingState
	 *   shippingCity
	 *   shippingZip
	 *   billingAddress
	 *   billingState
	 *   billingCity
	 *   billingZip
	 * The method will print the text "unable to write to file" in the
	 * event of an IOException.
	 *
	 * @param list  An arraylist of <code>Customer</code> data types.
	 */
	public static void saveCustomers(ArrayList<Customer> list)
	{
		// Define a file writing stream
		FileOutputStream out;
		try
		{
		    // Open the file writing stream
		    out = new FileOutputStream ("CustomerDatabase.txt");
		    // Print the customer data in the ArrayList
		    for(Customer c:list)
		    {
				// Save the "begin customer" tag
				new PrintStream(out).println ("&&NEWCUSTOMER");
				new PrintStream(out).println (c.getName());
				new PrintStream(out).println (c.getCustomerID());
				new PrintStream(out).println (c.getEmail());
				new PrintStream(out).println (c.getPhoneNumber());
				new PrintStream(out).println (c.getShippingAddress());
				new PrintStream(out).println (c.getShippingState());
				new PrintStream(out).println (c.getShippingCity());
				new PrintStream(out).println (c.getShippingZip());
				new PrintStream(out).println (c.getBillingAddress());
				new PrintStream(out).println (c.getBillingState());
				new PrintStream(out).println (c.getBillingCity());
				new PrintStream(out).println (c.getBillingZip());
				new PrintStream(out).println (c.getComment());
				// Save the "end customer" tag
				new PrintStream(out).println ("&&ENDCUSTOMER");
		    }
			// Save the "end file" tag
		    new PrintStream(out).println ("&&ENDFILE");
		    // Close the file output
		    out.close();
		}
		catch (IOException e)
		{
			System.err.println ("[ERROR: Unable to save customers]");
		}
	}
	
	/**
	 * Returns an <code>ArrayList</code> of <code>Customer</code> datatypes
	 * restored from the <code>CustomerDatabase.txt</code> file.
	 * The method will print the text "unable to write to file" in the
	 * event of an IOException. Note that the text file must be organized
	 * exactly as the <code>saveCustomers()</code> method styles it for
	 * this to work properly.
	 *
	 * @return  An <code>ArrayList</code> of <code>Customer</code> datatype
	 *          restored from CustomerDatabase.txt
	 */
	public static ArrayList<Customer> loadCustomers()
	{
		// The ArrayList to return
		ArrayList<Customer> customers = new ArrayList<Customer>();
		
		try
		{
			// File reader
			BufferedReader in = new BufferedReader(new FileReader("CustomerDatabase.txt"));
			// Stores the current line
			String line;
			// While the file still has information to be read, add it to the ArrayList
			while ((line = in.readLine()) != null)
			{
				if(line.equals("&&NEWCUSTOMER"))
				{
					Customer c = new Customer(in.readLine());
					c.setCustomerID(Integer.parseInt(in.readLine()));
					c.setEmail(in.readLine());
					c.setPhoneNumber(in.readLine());
					c.setShippingAddress(in.readLine());
					c.setShippingState(in.readLine());
					c.setShippingCity(in.readLine());
					c.setShippingZip(in.readLine());
					c.setBillingAddress(in.readLine());
					c.setBillingState(in.readLine());
					c.setBillingCity(in.readLine());
					c.setBillingZip(in.readLine());
					c.setComment(in.readLine());
					
					// Add the customer to the ArrayList
					customers.add(c);
				}
			}
			// Close the file input.
			in.close();
		} 
		catch (IOException e)
		{
			System.err.println ("[ERROR: Unable to restore customers]");
		}
		
		return customers;
	}	
}