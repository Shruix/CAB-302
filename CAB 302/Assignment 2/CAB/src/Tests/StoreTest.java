package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import Objects.Store;
import Objects.Item;
import Objects.Stock;

/**
 * Tests for the the Store Object Class
 * 
 * @author Jake Ralph
 *
 */
class StoreTest {
	
	String storeName = "Hello World";
	Stock inventory = new Stock();
	Store store = new Store(inventory, storeName);
	Item item1 = new Item("rice", 2.0, 3.0, 225, 300);
	
	/**
	 * Re-makes the store before every test
	 */
	@Before
	void before() {
		inventory = new Stock();
		store = new Store(inventory, storeName);
	}
	
	/**
	 * Checks to see if its possible to pull the store name from the store
	 */
	@Test //Makes sure its possible to get the store name
	void getStoreName() {
		assertEquals(store.getStoreName(), storeName);
	}
	
	/**
	 * Checks to see if its possible to pull the capital from the store
	 */
	@Test //Makes sure its possible to get the stores currentCapital
	void getStoreCapital() {
		double capital = 100000.00;
		
		assertTrue(store.getStoreCapital() == capital);
		
	}
	
	/**
	 * Checks to see if its possible to get the inventory from the store
	 */
	@Test //Makes sure its possible to get the stores inventory
	void getInventory() {	
		Stock invent = null;
		
		invent = store.getStock();
		
		assertTrue(invent != null);
	}
	
	/**
	 * Checks to see if its possible to give the store capital
	 */
	@Test //Makes sure the store can gain capital
	void giveCapital() {
		double initialCapital;
		double changeCapital;
		
		int itemAmount = 5;
		double itemCost = 200;
		
		initialCapital = store.getStoreCapital();
		
		store.giveCapital(itemAmount, itemCost);
		
		changeCapital = store.getStoreCapital();
		
		assertTrue(changeCapital == initialCapital + (itemAmount * itemCost));
		
	}
	
	/**
	 * Checks to see if its possible to take capital from the store
	 */
	@Test //Makes sure the store can loose capital
	void takeCapital() {
		double initialCapital;
		double changeCapital;
		
		int itemAmount = 5;
		double itemCost= 500;
		
		initialCapital = store.getStoreCapital();
		
		store.takeCapital(itemAmount, itemCost);
		
		changeCapital = store.getStoreCapital();
		
		assertTrue(changeCapital == initialCapital - (itemAmount * itemCost));
		
	}

}
