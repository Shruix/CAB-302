package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Objects.Item;
import Objects.Perishable;

class ItemTest {
	//Test 0: Declaring the Item
	Item item1 =  new Item("rice", 20, 3, 10, 5);
	Perishable item2;



	// Test 1: Test the name
	@Test
	void itemName() {
		item1.setName("brown rice");
		assertEquals("brown rice", item1.getName());
	}

	// Test 2: quantity
	@Test
	void itemQuantity() {
		item1.receiveItem(20);
		item1.sellItem(10);
		assertEquals(30, item1.getQuantity());
	}

	// Test3: cost and sell price
	@Test
	void costAndSellPrice() {
		item1.setCost(4.0);
		item1.setSellPrice(9.0);
		assertEquals(4.0, item1.getCost());
		assertEquals(9.0, item1.getSellPrice());
	}

	// Test 4: Reorder point and amount
	@Test
	void reorderPointAndAmount() {
		item1.setReorderPoint(10);
		item1.setReorderAmount(15);
		assertEquals(10, item1.getReorderPoint());
		assertEquals(15, item1.getReorderAmount());
	}

	// Test 5: Perishable item
	@Test
	void PerishableMaxTemp() {
		item2 = new Perishable("milk", 10, 1, 4, 5, 10);
		item2.setMaxTemp(3);
		assertEquals(3, item2.getMaxTemp());
	}



}
