package br.com.tschleuss.truckproblem.structure;

import java.util.Comparator;

import br.com.tschleuss.truckproblem.geo.CoordinateHelper;
import br.com.tschleuss.truckproblem.model.Cargo;
import br.com.tschleuss.truckproblem.model.Truck;

public class WeighedRoute implements Comparable<WeighedRoute>, Comparator<WeighedRoute> {

	private Truck truck;
	private Cargo cargo;

	private double distanceFromTruckToCargo;
	private double distanceFromCargoToDestination;

	public WeighedRoute(final Truck from, final Cargo to) {
		this.truck = from;
		this.cargo = to;
		calculateWeight();
	}

	private void calculateWeight() {
		distanceFromTruckToCargo = CoordinateHelper.calculateDistanceInKilometer(truck.getLat(), truck.getLng(),
				cargo.getOriginLat(), cargo.getOriginLng());

		distanceFromCargoToDestination = CoordinateHelper.calculateDistanceInKilometer(cargo.getOriginLat(),
				cargo.getOriginLng(), cargo.getDestinationLat(), cargo.getDestinationLng());
	}

	public double getDistance() {
		return distanceFromTruckToCargo + distanceFromCargoToDestination;
	}

	public Truck getTruck() {
		return truck;
	}

	public void setTruck(Truck truck) {
		this.truck = truck;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public double getDistanceFromTruckToCargo() {
		return distanceFromTruckToCargo;
	}

	public void setDistanceFromTruckToCargo(double distanceFromTruckToCargo) {
		this.distanceFromTruckToCargo = distanceFromTruckToCargo;
	}

	public double getDistanceFromCargoToDestination() {
		return distanceFromCargoToDestination;
	}

	public void setDistanceFromCargoToDestination(double distanceFromCargoToDestination) {
		this.distanceFromCargoToDestination = distanceFromCargoToDestination;
	}

	@Override
	public int compareTo(WeighedRoute route) {
		return compare(this, route);
	}

	@Override
	public int compare(WeighedRoute r1, WeighedRoute r2) {
		if (r1.getDistance() < r2.getDistance()) {
			return -1;
		} else if (r1.getDistance() > r2.getDistance()) {
			return +1;
		}
		return 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		sb.append(getDistance());
		sb.append(" km> - ");
		sb.append("[");
		sb.append(truck.getTruck());
		sb.append("] should travel ");
		sb.append(distanceFromTruckToCargo);
		sb.append(" km to pick [");
		sb.append(cargo.getProduct());
		sb.append("] in [");
		sb.append(cargo.getOriginCity());
		sb.append("] and then travel ");
		sb.append(distanceFromCargoToDestination);
		sb.append(" km to delivery it at [");
		sb.append(cargo.getDestinationCity());
		sb.append("]");
		return sb.toString();
	}
}
