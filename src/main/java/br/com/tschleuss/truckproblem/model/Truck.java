package br.com.tschleuss.truckproblem.model;

/**
 * Represent the Truck model with all their data.
 * 
 * @author tschleuss
 */
public class Truck implements CSVModel {

	private String truck;
	private String city;
	private String state;
	private double lat;
	private double lng;

	public String getTruck() {
		return truck;
	}

	public void setTruck(String truck) {
		this.truck = truck;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	@Override
	public void initFromCSVLine(String[] p) {
		truck = p[0];
		city = p[1];
		state = p[2];
		lat = Double.valueOf(p[3]);
		lng = Double.valueOf(p[4]);
	}
}
