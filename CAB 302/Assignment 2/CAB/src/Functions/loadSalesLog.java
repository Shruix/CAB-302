package Functions;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Objects.Item;
import Objects.Stock;
import Objects.Store;

/**
 * the loadSalesLog function loads in a given
 * sales log csv to have the cost of the items
 * bought added to the capital of the given store.
 * 
 * @param store The store to be used for the transactions
 * @param filePath the file path for the used sales log
 * 
 * @author Matthew Pini
 *
 */
public class loadSalesLog {
	
	public static void loadSales(Store store, String filePath) throws IOException {
		
		
		String[] middle;
		
		Stock inventory = store.getStock();
		
		String csvFile = filePath;
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		
		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {
			middle = line.split(csvSplitBy);
			Item item = inventory.getItem(middle[0]);
			int itemNumber = Integer.parseInt(middle[1]);
			
			//System.out.println(store.getStoreCapital());
			
			item.sellItem(itemNumber);
			store.giveCapital(itemNumber, item.getSellPrice());
			
			//System.out.println(store.getStoreCapital());
		}
		
		br.close();
		
	}
	
}
