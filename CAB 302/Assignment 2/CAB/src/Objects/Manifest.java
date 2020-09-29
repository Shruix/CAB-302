package Objects;
import java.util.ArrayList;
import java.util.List;

/**
 * the Manifest class creates a list for
 * the input of multiple trucks.
 * 
 * @author Matthew Pini
 *
 */
public class Manifest {
	
	private List<Truck> manifest;
	
	/**
	 * Creates a manifest
	 */
	public Manifest() {
		this.manifest = new ArrayList<Truck>();
	}
	
	/**
	 * Gets the List of Trucks and returns it
	 * 
	 * @return The List of Trucks.
 	 */
	public List<Truck> getManifest(){
		return this.manifest;
	}
	
	/**
	 * Adds a Truck to the manifest.
	 * 
	 * @param truck The truck to be added
	 */
	public void addTruck(Truck truck) {
		this.manifest.add(truck);
	}
	
	/**
	 * Clears the Manifest of all Trucks
	 */
	public void clearManifest() {
		this.manifest.clear();
	}
	
	/**
	 * Gets the position of a Truck within the manifest
	 * 
	 * @param truck The truck searched for
	 * @return The trucks position
	 */
	public int positionOfTruck(Truck truck) {
		return this.manifest.indexOf(truck);
	}
	
	/**
	 * Gets the Truck at a given position within the manifest
	 * 
	 * @param position The Position of the Truck wanted
	 * @return The Truck searched for
	 */
	public Truck truckAtPosition(int position) {
		return this.manifest.get(position);
	}
	
	/**
	 * Gets the truck at the last position within the manifest
	 * 
	 * @return The last Truck
	 */
	public Truck truckAtLastPosition() {
		return this.manifest.get(manifest.size());
	}
	
	/**
	 * Removes a Truck from the manifest
	 * 
	 * @param truck The truck to be removed
	 */
	public void removeTruck(Truck truck) {
		if (this.manifest.contains(truck)) {
			this.manifest.remove(truck);
		}
	}
	
	/**
	 * Checks to see if a truck exists in the manifest
	 * 
	 * @param truck The truck to search for
	 * @return If the truck exists or not
	 */
	public boolean truckExists(Truck truck) {
		return this.manifest.contains(truck);
	}
	
}
