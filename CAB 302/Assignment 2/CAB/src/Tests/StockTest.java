package Tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import Objects.Stock;
import Objects.Item;
import Objects.Perishable;

/**
 * Test for the Stock Object Class
 * 
 * @author Jake Ralph
 *
 */
class StockTest {
	
	Stock inventory = new Stock();
	
	Item item1 = new Item("rice", 2.0, 3.0, 225, 300);
	Item item2 = new Item("beans", 4, 6, 450, 525);
	Perishable item3 = new Perishable("mushroom", 2, 4, 200, 325, 10);
	
	/**
	 * Resets the inventory before every test
	 */
	@Before //Resets stock
	public void before() {
		inventory = new Stock();
	}
	
	/**
	 * Checks to see if the inventory contains an Item
	 */
	@Test //Checks to see if the inventory contains the added item
	void inventoryContains() {
		inventory.addItem(item1);
		
		assertTrue(inventory.contains("rice"));
	}
	
	/**
	 * Checks to see if an Item can be added to the inventory
	 */
	@Test //Checks to see if the inventory adds the item correctly
	void addingToStock() {
		inventory.addItem(item1);
		
		Item item = inventory.getItem("rice");
		
		boolean correct = true;
		
		if (item1.getName().contentEquals(item.getName())) {
			
		} else {
			correct = false;
		}
		
		if (item1.getCost() != item.getCost()) {
			correct = false;
		}
		
		if (item1.getQuantity() != item.getQuantity()) {
			correct = false;
		}
		
		if (item1.getReorderAmount() != item.getReorderAmount()) {
			correct = false;
		}
		
		if (item1.getReorderPoint() != item.getReorderPoint()) {
			correct = false;
		}
		
		if (item1.getSellPrice() != item.getSellPrice()) {
			correct = false;
		}
		
		assertTrue(correct);
	}
	
	/**
	 * Checks to see if Perishable Items to the inventory
	 */
	@Test //Checks to see if perishables are added correctly
	void addingPerishable() {
		inventory.addItem(item3);
		
		Perishable item = (Perishable) inventory.getItem("mushroom");
		
		assertEquals(item.getMaxTemp(), item3.getMaxTemp());
	}
	
	/**
	 * checks the length of stock with only one item
	 */
	@Test //Checks too see if the length of stock is correct
	void lengthOfStock() {
		inventory.addItem(item2);
		
		assertEquals(inventory.lengthOfStock(), 1);
	}
	
	/**
	 * checks to see the length of a stock with multiple Items within
	 */
	@Test //Checks to see if the length of multiple stock are correct
	void lengthOfMultipleStock() {
		inventory.addItem(item1);
		inventory.addItem(item3);
		
		assertEquals(inventory.lengthOfStock(), 2);
	}
	
	/**
	 * Checks the position of an added Item
	 */
	@Test //Checks to see if finding the position of an item is possible and correct
	void positionOfItem() {
		inventory.addItem(item1);
		inventory.addItem(item3);
		
		assertEquals(inventory.positionOfItem(item3), 1);
	}
	
	/**
	 * Checks to see if there is not stock
	 */
	@Test //Returns true if there is no inventory
	void hasNoStock() {		
		assertTrue(inventory.hasNoStock());
	}
	
	/**
	 * Checks to see if you can grab a specific Item from the Stock
	 */
	@Test //Makes sure its possible to get items
	void getItem() {
		Item item4 = null;
		
		inventory.addItem(item1);
		
		item4 = inventory.getItem("rice");
		
		assertTrue(item4 != null);		
	}
	
	/**
	 * Checks to see if its possible to get a Perishable Item from the stock
	 */
	@Test //Makes sure its possible to get Perishable items
	void isPerishable() {
		Perishable item = null;
		
		inventory.addItem(item3);
		
		item = inventory.getPerishable("mushroom");
		
		assertTrue(item != null);
	}

}
