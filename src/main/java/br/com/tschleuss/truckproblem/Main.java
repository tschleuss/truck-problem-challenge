package br.com.tschleuss.truckproblem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import br.com.tschleuss.truckproblem.model.Cargo;
import br.com.tschleuss.truckproblem.model.Truck;
import br.com.tschleuss.truckproblem.structure.WeighedRoute;

public class Main {

	private static final String FILE_CARGO = "cargo.csv";
	private static final String FILE_TRUCKS = "trucks.csv";
	private static final String CSV_SEPARATOR = ",";

	/**
	 * Run the program. To facilitate each approach reads the csv file again.
	 * 
	 * That kind of problem, similar to the traveling salesman problem, doesn't have
	 * a killer approach, the solution may very from condition to condition.
	 * 
	 * In this case we are even just considering the straight route between truck
	 * and cargo, doesn't have conditions like road, traffic, vehicle condition,
	 * return to the garage or whatever is the origin point. We we have roads, we
	 * could use dijkstra's algorithm to help decide, building a graph with points
	 * of iterest and their weights (connections), to help us decide the fastest /
	 * shortests route.
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, URISyntaxException {

		System.out.println("===== Pick First Approach =====");
		pickFirstApproach();

		System.out.println("\n===== Pick Ordered Closest Approach =====");
		pickOrderedClosestApproach();
	}

	/**
	 * Try to determine the best route for each cargo simple picking the first
	 * closest truck. Doesn't consider that the selected Truck may be nearest to
	 * other Cargo that is not being analyzed in the loop.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private static void pickFirstApproach() throws FileNotFoundException, IOException, URISyntaxException {

		final URL cargoFilePath = Main.class.getResource(FILE_CARGO);
		final URL trucksFilePath = Main.class.getResource(FILE_TRUCKS);

		final List<Cargo> cargos = readCargos(cargoFilePath);
		final List<Truck> trucks = readTrucks(trucksFilePath);
		final List<WeighedRoute> bestRoutes = new ArrayList<>();

		cargos.stream().forEach(cargo -> {
			bestRoutes.add(bestTravel(trucks, cargo));
		});

		bestRoutes.stream().sorted().forEach(e -> System.out.println(e));
	}

	/**
	 * Try to pick the first best truck for that cargo. Note that the truck is
	 * removed from the list when picked.
	 * 
	 * @param trucks
	 *            List of {@link Truck}
	 * @param cargo
	 *            List of {@link Cargo}
	 * @return the first best route.
	 */
	public static WeighedRoute bestTravel(final List<Truck> trucks, final Cargo cargo) {
		final List<WeighedRoute> routes = new ArrayList<>();
		trucks.stream().forEach(truck -> {
			routes.add(new WeighedRoute(truck, cargo));
		});
		final WeighedRoute best = Collections.min(routes);
		trucks.remove(best.getTruck());
		return best;
	}

	/**
	 * Try to determine the best route for each cargo analyzing every one from every
	 * Truck. Order all the routes to get the shortest one. If the shortest one to a
	 * cargo is from a Truck that is already designated for other delivery, then get
	 * the next one.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private static void pickOrderedClosestApproach() throws FileNotFoundException, IOException, URISyntaxException {

		final URL cargoFilePath = Main.class.getResource(FILE_CARGO);
		final URL trucksFilePath = Main.class.getResource(FILE_TRUCKS);
		final List<Cargo> cargos = readCargos(cargoFilePath);
		final List<Truck> trucks = readTrucks(trucksFilePath);

		final List<WeighedRoute> allRoutes = new ArrayList<>();
		cargos.stream().forEach(cargo -> {
			trucks.stream().forEach(truck -> {
				allRoutes.add(new WeighedRoute(truck, cargo));
			});
		});

		final List<Truck> truckMarked = new ArrayList<>();
		final List<Cargo> cargoMarked = new ArrayList<>();
		final List<WeighedRoute> bestRoutes = new ArrayList<>();

		allRoutes.stream().sorted().forEach(route -> {
			if (!truckMarked.contains(route.getTruck()) && !cargoMarked.contains(route.getCargo())) {
				truckMarked.add(route.getTruck());
				cargoMarked.add(route.getCargo());
				bestRoutes.add(route);
			}
		});

		bestRoutes.stream().sorted().forEach(e -> System.out.println(e));
	}

	/**
	 * Read the entire CSV converting it to a list of {@link Cargo}.
	 * 
	 * @param path
	 *            File path
	 * @return List of {@link Cargo}.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private static List<Cargo> readCargos(final URL path)
			throws FileNotFoundException, IOException, URISyntaxException {
		final File file = new File(path.toURI());
		try (final BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
			return br.lines().skip(1).map(mapToCargo).collect(Collectors.toList());
		}
	}

	/**
	 * Read the entire CSV converting it to a list of {@link Truck}.
	 * 
	 * @param path
	 *            File path
	 * @return List of {@link Truck}.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private static List<Truck> readTrucks(final URL path)
			throws FileNotFoundException, IOException, URISyntaxException {
		final File file = new File(path.toURI());
		try (final BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
			return br.lines().skip(1).map(mapToTruck).collect(Collectors.toList());
		}
	}

	/**
	 * Map a String line from CSV to {@link Cargo} model.
	 */
	private static Function<String, Cargo> mapToCargo = (line) -> {
		final String[] p = line.split(CSV_SEPARATOR);
		final Cargo cargo = new Cargo();
		cargo.initFromCSVLine(p);
		return cargo;
	};

	/**
	 * Map a String line from CSV to {@link Truck} model.
	 */
	private static Function<String, Truck> mapToTruck = (line) -> {
		final String[] p = line.split(CSV_SEPARATOR);
		final Truck truck = new Truck();
		truck.initFromCSVLine(p);
		return truck;
	};
}
