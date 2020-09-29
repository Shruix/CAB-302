package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.List;

import Objects.DryTruck;
import Objects.FridgeTruck;
import Objects.Manifest;
import Objects.Truck;

/**
 * Tests for the Manifest Object Class
 * 
 * @author Jake Ralph
 *
 */
class ManifestTest {
	
	Manifest manifest = new Manifest();
	DryTruck dry = new DryTruck();
	DryTruck dry1 = new DryTruck();
	FridgeTruck fridge = new FridgeTruck();
	
	/**
	 * Resets the manifest every test
	 */
	@Before
	void before() {
		manifest = new Manifest();
	}
	
	/**
	 * Checks to see if the manifest can return the list of trucks
	 */
	@Test
	void returnsList() {
		List<Truck> list = null;
		list = manifest.getManifest();
		assertTrue(list != null);
	}
	
	/**
	 * Checks to see if the manifests can add trucks
	 */
	@Test
	void addTruck() {
		manifest.addTruck(dry);
		assertEquals(manifest.positionOfTruck(dry), 0);
	}
	
	/**
	 * Checks to see if the manifest can remove trucks
	 */
	@Test
	void removeTruck() {
		manifest.addTruck(dry);
		manifest.removeTruck(dry);
		assertFalse(manifest.truckExists(dry));
	}
	
}
