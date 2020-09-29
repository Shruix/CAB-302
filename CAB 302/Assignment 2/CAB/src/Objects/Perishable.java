package Objects;

/**
 * the Perishable class extends from the Item class
 * is used to differentiate goods that need a temperature.
 * 
 * @author Jake Ralph
 *
 */
public class Perishable extends Item {
	
	private int maxTemp; // Maximum storage temperature in degrees Celcius
	
	/**
	 * Creates a Perishable Item
	 * 
	 * @param name The name of the Item
	 * @param cost The cost of the Item
	 * @param sellPrice The re-sale price of the Item
	 * @param reorderPoint The point at which more stock needs to be ordered at
	 * @param reorderAmount The amount to be reordered
	 * @param maxTemp The maximum temperature the Item needs to be kept at
	 */
	public Perishable(String name, double cost, double sellPrice,
			int reorderPoint, int reorderAmount, int maxTemp)
	{
		super(name, cost, sellPrice, reorderPoint, reorderAmount);
		this.maxTemp = maxTemp;
	}
	
	
	/**
	 * Gets the Items maximum Temperature
	 * 
	 * @return The items maximum temperature
	 */
	public int getMaxTemp() {
		return maxTemp;
	}
	
	/**
	 * Sets the Items maximum Temperature
	 * 
	 * @param maxTemp The new Temperature to be reset
	 */
	public void setMaxTemp(int maxTemp) {
		this.maxTemp = maxTemp;
	}
}
