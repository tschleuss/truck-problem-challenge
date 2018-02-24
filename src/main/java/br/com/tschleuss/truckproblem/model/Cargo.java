package br.com.tschleuss.truckproblem.model;

/**
 * Represent the Cargo model with all their data.
 * 
 * @author tschleuss
 */
public class Cargo implements CSVModel {

	private String product;
	private String originCity;
	private String originState;
	private double originLat;
	private double originLng;
	private String destinationCity;
	private String destinationState;
	private double destinationLat;
	private double destinationLng;

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getOriginCity() {
		return originCity;
	}

	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	public String getOriginState() {
		return originState;
	}

	public void setOriginState(String originState) {
		this.originState = originState;
	}

	public double getOriginLat() {
		return originLat;
	}

	public void setOriginLat(double originLat) {
		this.originLat = originLat;
	}

	public double getOriginLng() {
		return originLng;
	}

	public void setOriginLng(double originLng) {
		this.originLng = originLng;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public String getDestinationState() {
		return destinationState;
	}

	public void setDestinationState(String destinationState) {
		this.destinationState = destinationState;
	}

	public double getDestinationLat() {
		return destinationLat;
	}

	public void setDestinationLat(double destinationLat) {
		this.destinationLat = destinationLat;
	}

	public double getDestinationLng() {
		return destinationLng;
	}

	public void setDestinationLng(double destinationLng) {
		this.destinationLng = destinationLng;
	}

	@Override
	public void initFromCSVLine(String[] p) {
		product = p[0];
		originCity = p[1];
		originState = p[2];
		originLat = Double.valueOf(p[3]);
		originLng = Double.valueOf(p[4]);
		destinationCity = p[5];
		destinationState = p[6];
		destinationLat = Double.valueOf(p[7]);
		destinationLng = Double.valueOf(p[8]);
	}
}
