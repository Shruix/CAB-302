package Functions;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Objects.Item;
import Objects.Perishable;
import Objects.Stock;
import Objects.Store;


/**
 * The loadInventory function creates an inventory
 * from a csv file and then creates a store to have the
 * inventory added to.
 * 
 * gives the store a name.
 * 
 * @param storeName name to be given to the store upon creation
 * @param filePath
 * 
 * @author Matthew Pini
 *
 */
public class loadInventory {	
	
	public static Store loadInvent(String storeName, String filePath) throws FileNotFoundException, IOException {
		//Create Store Inventory
		Stock inventory = new Stock();
		//Create Middle man array for passing values to the Inventory
		String[] middle;
		
		//Reader Information
		String csvFile = filePath; // Getting file path
		BufferedReader br = null; //Creating an empty Reader
		String line = ""; //Telling it when the end of a line is
		String csvSplitBy = ","; //Telling it where to split the data
		
		//Storage for item Variables
		String itemName;
		double itemCost;
		double itemPrice;
		int reorderPoint;
		int reorderAmount;
		int temperature;
		
		//Creating the Reader
		br = new BufferedReader(new FileReader(csvFile));
		//Iterating through the csv lines
		while ((line = br.readLine()) != null) {
			middle = line.split(csvSplitBy); //Splitting the current lines into the array
			
			if (middle.length == 6) { // Does item have temperature?
				//Parse Text to proper variables
				itemName = middle[0].toString();
				itemCost = Double.parseDouble(middle[1]);
				itemPrice = Double.parseDouble(middle[2]);
				reorderPoint = Integer.parseInt(middle[3]);
				reorderAmount = Integer.parseInt(middle[4]);
				temperature = Integer.parseInt(middle[5]);
				// try/catch block CSVFormatException ---------------------------------
				
				//Create the perishable item
				Perishable item = new Perishable(itemName, itemCost, itemPrice,
						reorderPoint, reorderAmount, temperature);
				//Add Created Item to inventory
				inventory.addItem(item);
				
			} else if (middle.length == 5) { //If item does not have temperature
				//Parse Text to proper variables
				itemName = middle[0];
				itemCost = Double.parseDouble(middle[1]);
				itemPrice = Double.parseDouble(middle[2]);
				reorderPoint = Integer.parseInt(middle[3]);
				reorderAmount = Integer.parseInt(middle[4]);
				
				//Create the item
				Item item = new Item(itemName, itemCost, itemPrice,
						reorderPoint, reorderAmount);
				//Add item to inventory
				inventory.addItem(item);
			} else {
				//CSVFORMATEXCEPTION GOES HERE
			}
			
		}
		//Close Reader for safety
		br.close();
		
		//Create Store and Pass it through
		return Store.getInstance(inventory, storeName);
	}
	
}
