package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;

import Objects.DryTruck;
import Objects.FridgeTruck;
import Objects.Item;
import Objects.Perishable;

import org.junit.jupiter.api.Test;

/**
 * Test the Truck Object class
 * 
 * @author Matthew Pini
 *
 */
class TruckTest {
	
	FridgeTruck fridge = new FridgeTruck();
	DryTruck dry = new DryTruck();
	
	Item item1 = new Item("rice", 2.0, 3.0, 225, 300);
	Item item2 = new Item("beans", 4, 6, 450, 525);
	Perishable item3 = new Perishable("mushroom", 2, 4, 200, 325, 10);
	
	/**
	 * Resets the Trucks before each test
	 */
	@Before
	void Before() {
		fridge = new FridgeTruck();
		dry = new DryTruck();
	}
	
	/**
	 * Checks to see if its possible to get the Dry Trucks Capacity
	 */
	@Test
	void dryTruckGetCapacity(){
		assertEquals(dry.getCapacity(), 1000);
	}
	
	/**
	 * Checks to see if its posible to get the Fridge Trucks Capacity
	 */
	@Test
	void fridgeTruckGetCapacity() {
		assertEquals(fridge.getCapacity(), 800);
	}
	
	/**
	 * Checks to see if its possible to get the cost of the Fridge Truck
	 */
	@Test
	void fridgeTruckCost() {
		fridge.addCargo(item3);
		
		assertEquals(fridge.getCost(fridge.getMaxTemp()), 998);
	}
}
