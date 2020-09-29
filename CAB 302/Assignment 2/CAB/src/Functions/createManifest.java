package Functions;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import Objects.Item;
import Objects.Perishable;
import Objects.Stock;
import Objects.Store;


/**
 * The createManifest function grabs all items needed to be
 * re-stocked with.
 * 
 * @param store to be accessed for the creation of the manifest.
 * 
 * @author Jake Ralph
 */
public class createManifest {
	
	public static void Manifest(Store store) throws FileNotFoundException {
		
		Stock inventory = store.getStock();
		
		List<Item> dryGoodsToOrder = new ArrayList<Item>();
		List<Item> perishablesToOrder = new ArrayList<Item>();
		
		// Sorting the items into perishables and dry goods
		for (Item item : inventory.getItemList()) {
			if (item.getReorderPoint() >= item.getQuantity()) {
				if (item instanceof Perishable) {
					perishablesToOrder.add(item);
				} else {
					dryGoodsToOrder.add(item);
				}
			}
		}
		
		// Sorting the perishables in order of lowest storage temperature to highest
		perishablesToOrder.sort( (o1, o2) -> Integer.compare( ((Perishable)o1).getMaxTemp(),
															  ((Perishable)o2).getMaxTemp() ) );
		
		
		
		
		// Ordering the least amount of refridgerated trucks needed,
		// and if dry goods trucks will also be needed
		double fridgeTruckCapacity = 800;
		double dryTruckCapacity = 1000;
		int totalPerishables = 0;
		int totalDryGoods = 0;
		
		for (Item item : perishablesToOrder) {
			totalPerishables += item.getReorderAmount();
		}
		for (Item item : dryGoodsToOrder) {
			totalDryGoods += item.getReorderAmount();
		}
		
		int numFridgeTrucks = (int)(Math.ceil(totalPerishables / fridgeTruckCapacity) );
		
		int FTCargoSpace = (int)(numFridgeTrucks * fridgeTruckCapacity);
		int freeCargoSpace = FTCargoSpace - totalPerishables;
		int numDryTrucks = (int)Math.ceil( (totalDryGoods - freeCargoSpace)/dryTruckCapacity );
		
		
		
		
		
		// Creates csv-style List of items to order, now organised by 
		// storage temperature and dry goods.
		List<String[]> output = new ArrayList<String[]>();
		
		for (Item item : perishablesToOrder) {
			String[] middle = new String[2];
			
			middle[0] = item.getName();
			middle[1] = Integer.toString(item.getReorderAmount());
			
			output.add(middle);
		}
		
		for (Item item : dryGoodsToOrder) {
			String[] middle = new String[2];
			
			middle[0] = item.getName();
			middle[1] = Integer.toString(item.getReorderAmount());
			
			output.add(middle);
		}
		
		// Initialising a PrintWriter and StringBuilder to enable the creation
		// of the manifest csv file
		String userHomeFolder = System.getProperty("user.home") + "/Desktop";
		File textFile = new File(userHomeFolder, "Manifest.csv");
		PrintWriter pw = new PrintWriter(textFile);
		StringBuilder sb = new StringBuilder();
		
		// Counters used to track how many trucks are to be ordered.
		int currentLoad = 0;
		int qtyToFit = 0;
		int remainder = 0;
		int FTnum = 0;
		
		// variables used to assist in reading code.
		int itemName = 0;
		int itemQty = 1;
		
		String truckType = ""; // "fridge" or "dry".
		
		// Is a fridge truck required, or is the manifest only dry goods trucks.
		if (numFridgeTrucks > 0) {
			sb.append(">FridgeTruck\n");
			FTnum++;
			truckType = "fridge";
		} else if (numDryTrucks > 0) {
			sb.append(">DryTruck\n");
			truckType = "dry";
		}
		// Iterates over the output list, determining which truck to use and how many
		// items will fit inside, and writes the csv as a string to the StringBuilder
		// 'sb', which will be saved as the manifest.csv file
		for (int i = 0; i < output.size(); i++) {
			currentLoad += Integer.parseInt(output.get(i)[itemQty]); //Add item qty to truck cargo
			if (truckType == "fridge") {
				if (currentLoad > fridgeTruckCapacity) {  //Check if qty puts truck over capacity
					remainder = (currentLoad - (int)fridgeTruckCapacity);  //Determine how much won't fit
					sb.append(output.get(i)[itemName]); //Add the item name to string
					sb.append(',');                     //Add the separator
					qtyToFit = Integer.parseInt(output.get(i)[itemQty]) - remainder; //Determine how much will fit
					sb.append(Integer.toString(qtyToFit));  //Add the qty that will fit
					sb.append('\n');                    //Add the line terminator
					if (FTnum < numFridgeTrucks) {
						sb.append(">FridgeTruck\n");  //New truck order
						FTnum++;
					} else {
						sb.append(">DryTruck\n");     //Fridge or Dry truck as necessary
						truckType = "dry";
					}
					sb.append(output.get(i)[itemName]); //Add the item name
					sb.append(',');                     //Add the separator
					sb.append(Integer.toString(remainder)); //Add the remaining qty of the item
					currentLoad = remainder;            //Reset the load of the current truck
					sb.append('\n');
				} else {     //If the item qty will fit, add the item name and qty as normal
					sb.append(output.get(i)[itemName]);
					sb.append(',');
					sb.append(output.get(i)[itemQty]);
					sb.append('\n');
				}
			}
			else if (truckType == "dry") {   //Repeat process with the Dry truck until finished
				if (currentLoad > dryTruckCapacity) {
					remainder = (currentLoad - (int)dryTruckCapacity);
					sb.append(output.get(i)[itemName]);
					sb.append(',');
					qtyToFit = Integer.parseInt(output.get(i)[itemQty]) - remainder;
					sb.append(Integer.toString(qtyToFit));
					sb.append('\n');
					sb.append(">DryTruck\n");
					sb.append(output.get(i)[itemName]);
					sb.append(',');
					sb.append(Integer.toString(remainder));
					currentLoad = remainder;
					sb.append('\n');
				} else {
					sb.append(output.get(i)[itemName]);
					sb.append(',');
					sb.append(output.get(i)[itemQty]);
					sb.append('\n');
				}
			}
			
		}
			
		
		pw.write(sb.toString());
		
		pw.close();
		
	}
	
}
