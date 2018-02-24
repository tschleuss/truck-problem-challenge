package br.com.tschleuss.truckproblem.geo;

/**
 * Helper to work with coordinates.
 * 
 * @author tschleuss
 */
public class CoordinateHelper {

	private final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;

	/**
	 * Helper to calcula the distance between two points in kilometers.
	 * 
	 * @param latFrom
	 *            First latitude.
	 * @param lngFrom
	 *            First longitude.
	 * @param latTo
	 *            Second latitude.
	 * @param lngTo
	 *            Second longitude.
	 * @return Distance in kilometers.
	 */
	public static double calculateDistanceInKilometer(double latFrom, double lngFrom, double latTo, double lngTo) {

		final double latDistance = Math.toRadians(latFrom - latTo);
		final double lngDistance = Math.toRadians(lngFrom - lngTo);

		final double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(latFrom))
				* Math.cos(Math.toRadians(latTo)) * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

		final double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c);
	}
}
