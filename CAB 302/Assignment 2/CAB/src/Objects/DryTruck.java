package Objects;


/**
 * The DryTruck class extends from the Truck class
 * and creates a truck used for non-perishable goods.
 * 
 * @author Jake Ralph
 */
public class DryTruck extends Truck {
	private int maxCapacity;
	private int load;
	private double cost; // in dollars
	
	/**
	 * Creates a DryTruck used for no perishable items only.
	 */
	public DryTruck(){
		super();
		this.maxCapacity = 1000;
		this.load = 0;
		this.setCost();
	}
	
	/**
	 * returns the cost of the truck
	 */
	public void setCost() {
		this.cost = 750 + (0.25 * this.load);
	}
	
	/**
	 * adds an item to the trucks cargo also long as its
	 * not an instance of the Perishable Item class
	 */
	@Override
	public void addCargo(Item item) { // throws DeliveryException()
		if (item instanceof Perishable) {
			//throw new DeliveryException("Cannot load perishables in non-refridgerated truck");
		}
		
		if (this.load + item.getQuantity() > this.maxCapacity ) {
			//throw new DeliveryException("Not enough room");
		} else {
			this.load += item.getQuantity();
			this.setCost();
		}
		
	}
	
	/**
	 * Gets the capacity of the Dry Truck
	 * 
	 * @return Capacity
	 */
	public int getCapacity() {
		return this.maxCapacity;
	}
	
	/**
	 * gets the cost of the dry Truck
	 * 
	 * @return cost
	 */
	public double getCost() {
		return this.cost;
	}
}
