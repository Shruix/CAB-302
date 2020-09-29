package Objects;


/**
 * The FridgeTruck class extends from the Truck class
 * and creates a truck for goods that do not require
 * a temperature.
 * 
 * @author Jake Ralph
 */
public class FridgeTruck extends Truck {
	private int maxCapacity;
	private int maxTemp; // in degrees Celsius
	private double cost; // in dollars
	
	/**
	 * Creates a Fridge Truck for all types of items.
	 */
	public FridgeTruck(){
		super();
		this.maxTemp = 10; // degrees Celsius
		setCost(maxTemp);
		this.maxCapacity = 800;
	}
	
	/**
	 * Sets the cost of the truck
	 * 
	 * @param maxTemp The maximum temperature the truck needs to be kept at.
	 */
	public void setCost(int maxTemp) {
		this.cost = 900 + (200 * (Math.pow( 0.7, ((double)maxTemp/5.0) )));
	}
	
	/**
	 * Gets the cost of the truck
	 * 
	 * @return Cost
	 */
	public double getCost(int maxTemp) {
		double tempcost;
		tempcost = 900 + (200 * (Math.pow( 0.7, ((double)maxTemp/5.0) )));
		System.out.println(Double.toString(tempcost));
		return tempcost;
		
	}
	
	
	/**
	 * adds an item to the trucks cargo.
	 * any type of item can be added to the truck.
	 * The maxTemp of the truck is also added to the truck
	 * depending on what items are in.
	 * 
	 * @param item The item to be added
	 */
	public void addCargo(Item item) {
		if (item instanceof Perishable) {
			if (((Perishable) item).getMaxTemp() < maxTemp) {
				this.maxTemp = ((Perishable) item).getMaxTemp();
				setCost(maxTemp);
			}
		}
	}
	
	/**
	 * returns the temperature of the truck.
	 * 
	 * @return Temperature
	 */
	public int getMaxTemp() {
		return this.maxTemp;
	}
	
	/**
	 * Gets the cost of the truck
	 * 
	 * @return Cost
	 */
	public double getCost() {
		return this.cost;
	}
	
	/**
	 * Gets the capacity of the Fridge Truck
	 * 
	 * @return
	 */
	public int getCapacity() {
		return this.maxCapacity;
	}
	
}
