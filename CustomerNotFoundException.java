/*
 * Program Name:   MGMT
 * School:         Patrick Henry High School
 * Computer used:  home desktop
 *                 school computer
 * IDE used:       Notepad++ text editor (Windows)
 *                 medit text editor (GNU/Linux)
 */

/**
 * Exception thrown when an expected or searched for customer is not found in
 * the <code>ArrayList customers</code>. Designed for the MGMT class.
 *
 * @author   Nathan Typanski
 * @version  1.0 20 March 2010
 */
public class CustomerNotFoundException extends java.lang.Exception
{
	/**
	 * Throws the CustomerNotFoundException.
	 *
	 */
	CustomerNotFoundException()
	{
		super("The specified customer was not found in the database.");
	}
}