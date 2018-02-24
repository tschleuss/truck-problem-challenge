package br.com.tschleuss.truckproblem.model;

/**
 * Interface helper to make models being initialized with array os data.
 * 
 * @author tschleuss
 */
public interface CSVModel {

	/**
	 * Receive an array of String to populate the model.
	 * 
	 * @param p
	 */
	void initFromCSVLine(final String[] p);
}
