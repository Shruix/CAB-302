package Functions;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Objects.DryTruck;
import Objects.FridgeTruck;
import Objects.Item;
import Objects.Perishable;
import Objects.Stock;
import Objects.Store;

/**
 * the loadManifest function loads
 * a manifest csv file which pays for the new stock
 * and cost of trucks with the stores capital
 * 
 * @param store The store to be used for the transactions
 * @param filePath the filePath of the csv file used
 * 
 * @author Matthew Pini
 *
 */
public class loadManifest {
	
	public static void loadMani(Store store, String filePath) throws IOException {
		
		Stock inventory = store.getStock();
		
		String[] middle;
		
		List<String[]> manifest = new ArrayList<String[]>();
		
		
		String csvFile = filePath;
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		
		
		FridgeTruck fridge = null;
		DryTruck dry = null;
		int dryAmount = 0;
		
		br = new BufferedReader(new FileReader(csvFile));
		while((line = br.readLine()) != null) {
			middle = line.split(csvSplitBy);
			manifest.add(middle);
		}
		br.close();
		
		for (int i = 0; i < manifest.size(); i++) {
			if (manifest.get(i)[0].contentEquals(">FridgeTruck")) {
				//Create a fridge truck
				fridge = new FridgeTruck();
				dry = null;
				//Get the perishable item for temperature (its in the next row)
				Perishable item = inventory.getPerishable(manifest.get(i + 1)[0]);
				
				double cost = fridge.getCost(item.getMaxTemp());
				
				store.takeCapital(1, cost);

			} else if (manifest.get(i)[0].contentEquals(">DryTruck")) {
				dry = new DryTruck();
				fridge = null;
				
			} else { // if its anything else, its an item
				if (fridge != null && dry == null) {
					if (inventory.getItem(manifest.get(i)[0]) instanceof Perishable) {
						Perishable item = inventory.getPerishable(manifest.get(i)[0]);
						
						store.takeCapital(Integer.parseInt(manifest.get(i)[1]), item.getCost());
						
						item.receiveItem(Integer.parseInt(manifest.get(i)[1]));
					} else {
						Item item = inventory.getItem(manifest.get(i)[0]);
						int numberOfItems = Integer.parseInt(manifest.get(i)[1]); 
						
						store.takeCapital(numberOfItems, item.getCost());
						
					    item.receiveItem(Integer.parseInt(manifest.get(i)[1]));
					}
				}
				if (dry != null && fridge == null) {
					Item item = inventory.getItem(manifest.get(i)[0]);
					
					store.takeCapital(Integer.parseInt(manifest.get(i)[1]), item.getCost());
					
					item.receiveItem(Integer.parseInt(manifest.get(i)[1]));
					
					dryAmount += Integer.parseInt(manifest.get(i)[1]);
					if (i >= manifest.size() - 1) {
						double cost = 750 + (0.25 * dryAmount);
						store.takeCapital(1, cost);
						dryAmount = 0;
					} else {
						if (manifest.get(i + 1)[0].contentEquals(">FridgeTruck") || manifest.get(i + 1)[0].contentEquals(">DryTruck")) {
							double cost = 750 + (0.25 * dryAmount);
							store.takeCapital(1, cost);
							dryAmount = 0;
						}
					}					
				}
			}
		}
	}
}
