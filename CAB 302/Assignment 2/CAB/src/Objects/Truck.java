package Objects;
import java.util.ArrayList;

/**
 * Creates a Truck which is used to store Items
 * along with returning the cost of the Truck
 * 
 * @author Jake Ralph
 *
 */
public class Truck {
	private ArrayList<Item> cargo;
	
	/**
	 * Creates a Truck
	 */
	Truck(){
		cargo = new ArrayList<Item>();
	}
	
	/**
	 * Adds An Item to the Trucks cargo
	 * 
	 * @param item Item to be added
	 */
	public void addCargo(Item item) {
		this.cargo.add(item);
	}
	
	/**
	 * Removes Cargo from the Truck
	 * 
	 * @param item Item to be removed
	 */
	public void removeCargo(Item item) {
		int index = this.cargo.indexOf(item);
		this.cargo.remove(index);
	}
	
	/**
	 * Returns a list of the current Cargo
	 * 
	 * @return Cargo List
	 */
	public ArrayList<Item> checkLoad(){
		return this.cargo;
	}
}
