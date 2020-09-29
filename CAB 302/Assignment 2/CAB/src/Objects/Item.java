package Objects;


/**
 * The Item class creates a new item.
 * 
 * @param name The name of the item.
 * @param cost How much the item costs to order
 * @param sellPrice How much the item will be sold for
 * @param reorderPoint The point at which new stock needs to be ordered
 * @param reorderAmount The amount which needs to be ordered when the item hits the reorder point
 * 
 * @author Jake Ralph
 *
 */
public class Item {
	
	private String name;
	private int quantity;
	private double cost;  // in dollars
	private double sellPrice; // in dollars
	private int reorderPoint;
	private int reorderAmount;
	
	/**
	 * Creates an item
	 * 
	 * @param name Name of the Item
	 * @param cost Cost of the Item
	 * @param sellPrice Re-sale price of the Item
	 * @param reorderPoint Point at which new stock needs to be ordered at
	 * @param reorderAmount Amount which needs to be ordered at the reorderPoint
	 */
	public Item(String name, double cost, double sellPrice,  
			int reorderPoint, int reorderAmount) {
		this.setName(name);
		this.quantity = 0;
		this.setCost(cost);
		this.setSellPrice(sellPrice);
		this.setReorderPoint(reorderPoint);
		this.setReorderAmount(reorderAmount);
	}
	
	/**
	 * Used for getting the name of the item.
	 * 
	 * @return the Item name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Used for renaming an Item.
	 * 
	 * @param name The new name to be set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Used to get the Items current quantity of stock.
	 * 
	 * @return the number of items in stock
	 */
	public int getQuantity() {
		return this.quantity;
	}
	
	
	/**
	 * Used for when an Item is sold.
	 * Takes the amount sold away from the current quantity.
	 * 
	 * @param quantitySold The amount of an Item sold
	 */
	public void sellItem(int quantitySold) { // throws StockException()
		if (quantitySold > this.quantity) {
			// throw new StockException("Incorrect quantity");
		} else {
			this.quantity = this.quantity - quantitySold;
		}
	}
	
	
	/**
	 * Used for when an order of items come in.
	 * Adds the amount of an Item, received and
	 * adds it to the current quantity.
	 * 
	 * @param quantityReceived The amount to be added.
	 */
	public void receiveItem(int quantityReceived) {
		this.quantity = this.quantity + quantityReceived;
	}
	
	
	/**
	 * Gets the cost of the Item.
	 * 
	 * @return The Items cost.
	 */
	public double getCost() {
		return this.cost;
	}
	
	/**
	 * Sets the cost of an Item
	 * 
	 * @param cost The new cost for the Item.
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	
	/**
	 * Gets the price the item will be re-sold for.
	 * 
	 * @return The Items sell price.
	 */
	public double getSellPrice() {
		return this.sellPrice;
	}
	
	/**
	 * Sets the items re-sell price.
	 * 
	 * @param sellPrice The Items new re-sell price.
	 */
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	/**
	 * Gets the point at which new stock needs
	 * to be ordered at.
	 * 
	 * @return The Items reorder point
	 */
	public int getReorderPoint() {
		return this.reorderPoint;
	}
	
	/**
	 * Sets the point at which new stock needs
	 * to be ordered at.
	 * 
	 * @param reorderPoint The new re-order point
	 */
	public void setReorderPoint(int reorderPoint) {
		this.reorderPoint = reorderPoint;
	}
	
	/**
	 * Gets the amount of stock to be re-ordered.
	 * 
	 * @return The amount to be re-ordered
	 */
	public int getReorderAmount() {
		return this.reorderAmount;
	}
	
	/**
	 * Sets the amount of stock to be re-ordered.
	 * 
	 * @param reorderAmount The new amount to be re-ordered
	 */
	public void setReorderAmount(int reorderAmount) {
		this.reorderAmount = reorderAmount;
	}
}
