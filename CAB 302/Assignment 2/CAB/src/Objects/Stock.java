package Objects;
import java.util.ArrayList;
import java.util.List;

/**
 * the Stock class is a list of items used
 * to store multiple items in the same location.
 * 
 * @author Matthew Pini
 *
 */
public class Stock {

	// List of all items in stock
	List<Item> list;
	
	/**
	 * Creates a Stock
	 */
	public Stock() {
		list = new ArrayList<Item>();
	}
	
	/**
	 * Adds an Item to the Stock
	 * 
	 * @param item Item to be added
	 */
	public void addItem(Item item) { //throws StockException
		if (this.list.contains(item) != true) {
			this.list.add(item);
		} else {
			// throw new StockException("Item already in list, Please update instead");
		}
	}
	
	/**
	 * Removes an Item from the stock
	 * 
	 * @param item Item to be removed
	 */
	public void removeItem(Item item) { // throws StockException
		if (this.list.contains(item) == true) {
			this.list.remove(item);
		} else {
			// throw new StockException("Item not in list");
		}
	}

	/**
	 * Removes the the current version on an item
	 * within the stock and replaces with the new
	 * version
	 * 
	 * @param item New Item to be replaced
	 */
	public void updateItem(Item item) {
		if (this.list.contains(item) == true) {
			removeItem(item);
			addItem(item);
		}
	}

	/**
	 * returns how many Items are in stock
	 * 
	 * @return Amount of Items
	 */
	public int lengthOfStock() {
		return this.list.size();
	}
	
	/**
	 * gets the position of an Item within the stock
	 * 
	 * @param item Item to be searched for
	 * @return Item position
	 */
	public int positionOfItem(Item item) {
		return this.list.indexOf(item);
	}
	
	/**
	 * Checks to see if a specific item is within
	 * the stock
	 * 
	 * @param itemName Name of item to be searched for
	 * @return If the item was found or not
	 */
	public boolean contains(String itemName) {
		boolean found = false;

		for (Item item : list) {
			if (item.getName().equals(itemName)) {
				found = true;
			}
		}

		return found;
	}

	/**
	 * Checks to see if the stock list is empty or not
	 * 
	 * @return if there is any stock or not.
	 */
	public boolean hasNoStock() {
		return this.list.isEmpty();
	}

	/**
	 * Gets the Item within the stock
	 * 
	 * @param itemName Name of the Item to get
	 * @return The Item
	 */
	public Item getItem(String itemName) { // throws StockException
		Item itemFound = null;

		for (Item item : this.list) {

			if (item.getName().equals(itemName)) {
				itemFound = item;
			}
		}

		if (itemFound == null) {
			// throw new StockException("Item not found");
		}

		return itemFound;
	}

	/**
	 * Gets the Perishable Item within the stock
	 * 
	 * @param itemName Name of the Item to get
	 * @return The Perishable Item
	 */
	public Perishable getPerishable(String itemName) {
		Perishable itemFound = null;

		for (Item item : this.list) {
			if (item instanceof Perishable) {
				if (item.getName().equals(itemName)) {
					itemFound = (Perishable) item;
				}
			}
		}

		return itemFound;

	}

	/**
	 * Returns the name and
	 * re-order amount for all Items within the stock
	 * 
	 * @return the stock
	 */
	public List<String[]> manifest() {
		List<String[]> output = new ArrayList<String[]>();

		for (Item item : this.list) {
			String[] middle = new String[2];
			if (item.getReorderPoint() > item.getQuantity()) {
				middle[0] = item.getName();
				middle[1] = Integer.toString(item.getReorderAmount());
			}

			output.add(middle);
		}

		return output;
	}
	
	/**
	 * The full list of Items within the Stock
	 * 
	 * @return List of Items
	 */
	public List<Item> getItemList(){
		return list;
	}
}
