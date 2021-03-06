
//import java.io.IOException;
import java.nio.charset.StandardCharsets; 
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;


public class Server {
	private int ID;
 	private double latitude, longitude;

	public Server() {
		

		    class lost_item        {
		 	
		 		public double generateLongCoords() {
		 		//will generate random longitude coordinates in which it will tell the location of   //the lost item
		 		
		 		return longitude;
		 	}
		 	
		 	public double generateLatCoords() {
		 		//will generate random latitude coordinates for location of lost item
		 		
		 		return latitude;
		 	}
		 	
		 	public int generateID(int ID) {
		 		//Generates lost item ID
		 		
		 		return ID;
		 	}
	 }

		 /**
		  * TODO: 
		  * Create static ID for each instance
		  * so each Finder is gauranteed a unique ID
		  */

		 /**
		  * 
		  * @author Phil Fernandez
		  *
		  */

		 class Finder {
		 	private double latitude, longitude;
		 	private int ID;

		 	/**
		 	 * Constructor randomly generates lotitude and longitude coordinates
		 	 * @param ID
		 	 */
		 	public Finder(int ID) {
		 		// generate latitude and longitude coordinates
		 		double lowerBoundLatitude = -90;
		 		double upperBoundLatitude = 90;
		 		double lowerBoundLongitude = -180;
		 		double upperBoundLongitude = 180;

		 		this.latitude = range(lowerBoundLatitude, upperBoundLatitude);
		 		this.longitude = range(lowerBoundLongitude, upperBoundLongitude);
		 		this.ID = ID;
		 	}

		 	/**
		 	 * Constructor takes specific latitude and longitude
		 	 * coordinates.
		 	 * @param ID
		 	 * @param latitude
		 	 * @param longitude
		 	 */
		 	public Finder(int ID, double latitude, double longitude)    {
		 		// If latitude or longitude are outside of range
		 		// round up/down to nearest acceptable value.
		 		// -90 <= latitude <= 90
		 		if (latitude > 90)
		 			latitude = 90;
		 		if (latitude < -90)
		 			latitude = -90;

		 		// -180 <= longitude <= 180
		 		if (longitude > 180)
		 			longitude = 180;
		 		if (longitude < -180)
		 			longitude = -180;

		 		this.ID = ID;
		 		this.latitude = latitude;
		 		this.longitude = longitude;
		 	}

		 	/**
		 	 * 
		 	 * @return string representation of latitude and longitude
		 	 */
		 	public String getCoordinates() {
		 		// format decimal output of longitude and latitude
		 		DecimalFormat formatLatitude = new DecimalFormat("##.######");
		 		DecimalFormat formatLongitude = new DecimalFormat("###.######");

		 		return "(" + formatLatitude.format(latitude) + 
		 			", " + formatLongitude.format(longitude) + ")";
		 	}

		 	/**
		 	 * provide toString method so instances can be printed out to console
		 	 * @return string literal
		 	 */
		 	public String toString() {
		 		// return string for printing Finder object to console
		 		return "Device ID: " + ID + "\nCoordinates: " +
		 			getCoordinates();
		 	}

		 	/**
		 	 * sends latitude and lonitude coordinates to server, along with date
		 	 * and timestamp
		 	 * @return void
		 	 */
		 	public void sendLocationToServer()  {
		 		try {
		 			// create Path object to file acting as server
		 			final Path pathToServer = Paths.get("./server.dat");
		 			// Create Files object for writing to file (aka server) <singleton?>
		 			Files.write(pathToServer, Arrays.asList(this.getCoordinates() + " "
		 			+ timeStamp()), StandardCharsets.UTF_8, 
		 			// If file exists append coordinates and timestamp
		 			Files.exists(pathToServer) ? StandardOpenOption.APPEND 
		 			// Else create file to be written to and write corridinates 
		 			// and timestamp
		 			: StandardOpenOption.CREATE);
		 		} catch (final IOException ioe) {
		 			ioe.printStackTrace();
		 		}
		 	}

		 	/**
		 	 * private helper method
		 	 * @return current time and date aka Timestamp
		 	 */
		 	private Timestamp timeStamp() {
		 		Date date = new Date();
		 		Timestamp timestamp = new Timestamp(date.getTime());
		 		return timestamp;
		 	}

		 	/**
		 	 * This is a helper method for generating random coordinates
		 	 * @param lower
		 	 * @param upper
		 	 * @return random double between lower and upper
		 	 */
		 	private double range(double lower, double upper) 
		   {
		 		return Math.random() * (upper - lower) + lower;
		   }
		 }
	}
}