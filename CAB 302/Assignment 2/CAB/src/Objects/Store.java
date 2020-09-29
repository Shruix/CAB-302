package Objects;

/**
 * The Store class creates a store which holds
 * the stores capital, inventory and its name.
 * 
 * @author Matthew Pini
 *
 */
public class Store {

	private static Store store;

	private double capital = 100000.00;
	private Stock inventory;
	private String name;
	
	/**
	 * Creates a Store
	 * 
	 * @param inventory The Inventory for the store
	 * @param storeName The Name of the store
	 */
	public Store(Stock inventory ,String storeName) {
		this.inventory = inventory;
		this.name = storeName;
	}
	
	/**
	 * Makes sure there is only ever one instance of
	 * this store class
	 * 
	 * @param inventory The Inventory for the store
	 * @param storeName The Name of the store
	 * @return The created Store
	 */
	public static Store getInstance(Stock inventory ,String storeName) {
		if (store == null) {
			store = new Store(inventory, storeName);
		}
		return store;
	}
	
	/**
	 * Gets the name of the Store
	 * 
	 * @return Store name
	 */
	public String getStoreName() {
		return this.name;
	}
	
	/**
	 * Gets the capital of the Store
	 * 
	 * @return Store capital
	 */
	public double getStoreCapital() {
		return this.capital;
	}
	
	/**
	 * Gets the inventory of the Store
	 * 
	 * @return The Stock
	 */
	public Stock getStock() {
		return this.inventory;
	}

	/**
	 * Used to give the store capital
	 * 
	 * @param numberOfItems Number of Items being sold
	 * @param costOfItem Cost of Item being sold
	 */
	public void giveCapital(int numberOfItems, double costOfItem) {
		this.capital += (numberOfItems * costOfItem);
	}
	
	/**
	 * Used to take Capital from the store
	 * 
	 * @param numberOfItems The number of Items bought by the store
	 * @param costOfItem Cost of the Items
	 */
	public void takeCapital(int numberOfItems, double costOfItem) {
		this.capital -= (numberOfItems * costOfItem);
	}
}
