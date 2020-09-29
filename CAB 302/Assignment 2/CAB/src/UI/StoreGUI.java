package UI;
import java.awt.GraphicsConfiguration;
import java.awt.*;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import Functions.createManifest;
import Functions.loadInventory;
import Functions.loadManifest;
import Functions.loadSalesLog;
import Objects.Item;
import Objects.Perishable;
import Objects.Store;

/**
 * 
 */

/**
 * @author Jake Ralph
 *
 */
public class StoreGUI extends JFrame implements ActionListener, Runnable {

	/**
	 * @throws HeadlessException
	 */
	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;
	
	private JPanel pnlInv;
	private JPanel pnlFiles;
	private JLabel lblInv;
	private JLabel lblCapital;
	private JLabel lblMani;
	private JLabel lblSalesLogs;
	private JTable tblInv;
	private JButton btnInvLoad;
	private JButton btnManiLoad;
	private JButton btnManiExp;
	private JButton btnSalesLoad;
	private JScrollPane tblScroll;
	private JFileChooser jfcInv;
	private JFileChooser jfcMani;
	private JFileChooser jfcSales;
	private DefaultTableModel model;
	private String filePathInv;
	private String filePathManifest;
	private String filePathSalesLog;
	
	private Store store;

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public StoreGUI(String arg0) throws HeadlessException {
		super(arg0);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	private void layoutFilesPanel() {
		GridBagLayout layout = new GridBagLayout();
		pnlFiles.setLayout(layout);
		//add components to grid
		GridBagConstraints constraints = new GridBagConstraints();
		//Defaults
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weightx = 100;
		constraints.weighty = 100;
		
		constraints.anchor = GridBagConstraints.EAST;
		addToPanel(pnlFiles, lblCapital, constraints,4,0,1,2);
		
		constraints.anchor = GridBagConstraints.CENTER;
		addToPanel(pnlFiles, lblMani, constraints,0,2,3,1);
		addToPanel(pnlFiles, btnManiLoad, constraints,3,2,1,1);
		addToPanel(pnlFiles, btnManiExp, constraints,4,2,1,1);
		
		
		addToPanel(pnlFiles, lblSalesLogs, constraints,0,6,2,1);
		addToPanel(pnlFiles, btnSalesLoad, constraints,3,6,2,1);
		
		constraints.fill = GridBagConstraints.HORIZONTAL;
		//addToPanel(pnlFiles, jfcMani, constraints, 0,3,5,3);
		//addToPanel(pnlFiles, jfcSales, constraints, 0,7,5,3);
		btnManiLoad.setEnabled(false);;
		btnManiExp.setEnabled(false);;
		btnSalesLoad.setEnabled(false);;
		
	} 
	
	
	private void layoutInventoryPanel() {
		GridBagLayout layout = new GridBagLayout();
		pnlInv.setLayout(layout);
		
		GridBagConstraints constraints = new GridBagConstraints();
		// Defaults
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.weightx = 100;
		constraints.weighty = 100;
		
		constraints.anchor = GridBagConstraints.SOUTH;
		addToPanel(pnlInv, lblInv, constraints,0,0,1,1);
		constraints.anchor = GridBagConstraints.EAST;
		
		constraints.fill = GridBagConstraints.BOTH;
		//addToPanel(pnlInv, jfcInv, constraints,0,1,10,10);
		addToPanel(pnlInv, tblScroll, constraints,0,2,10,10);
		
		constraints.fill = GridBagConstraints.NONE;
		addToPanel(pnlInv, btnInvLoad, constraints,1,0,1,1);
		
	}
	
	
	
	/**
	*
	* A convenience method to add a component to given grid bag
	* layout locations. Code due to Cay Horstmann
	*
	* @param c the component to add
	* @param constraints the grid bag constraints to use
	* @param x the x grid position
	* @param y the y grid position
	* @param w the grid width of the component
	* @param h the grid height of the component
	*/
	private void addToPanel(JPanel jp,Component c, GridBagConstraints
		constraints,int x, int y, int w, int h) {
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = w;
		constraints.gridheight = h;
		jp.add(c, constraints);
	}
	
	private JFileChooser createFileChooser(String filterText, String fileType, String message) {
		String path ;
		JFileChooser jfc = new JFileChooser("../");
		jfc.setDialogTitle(message);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				filterText, fileType);
		jfc.setFileFilter(filter);
		return jfc;
	}
	

	
	private JButton createButton(String str) {
		//Create a JButton object and store it in a local var
		JButton btn = new JButton();
		//Set the button text to that passed in str
		btn.setText(str);
		//Add the frame as an actionListener
		btn.addActionListener(this);
		//Return the JButton object
		return btn;
	} 
	
	private JScrollPane createScrollPane(JTable table) {
		JScrollPane scroll = new JScrollPane(table);
		//scroll.hide();
		return scroll;
	}
	
	
	private JLabel createLabel(String str, int fontSize) {
		JLabel lbl = new JLabel(str, JLabel.CENTER);
		lbl.setFont(new Font("Courier New", Font.BOLD, fontSize));
		return lbl;
	}
	
	private void updateCapitalLabel() {
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		
		String capitalString = df.format( store.getStoreCapital() );
		lblCapital.setText("Capital: $"+capitalString);
	}
	
	private JTable createTable() {
		
		String data[][] = new String[0][7];
		
		String column[] = {"Name", "Qty", "Cost",
				"Price", "R.Point", "R.Amount", "Temp" };
		
		model = new DefaultTableModel(data, column);
		JTable tbl = new JTable(model);
		tbl.setBounds(30,40,200,300);
		tbl.disable();
		return tbl;
	}
	
	
	private void fillTable(Store store) {
		int numRows = model.getRowCount();
		for (int i = 0; i < numRows; i++) {
			model.removeRow(0);
		}
		List<Item> itemList = new ArrayList<Item>();
		itemList = store.getStock().getItemList();
		for (Item item : itemList ) {
			if (item instanceof Perishable) {
				model.addRow(new Object[] {
											item.getName(),
											item.getQuantity(),
											item.getCost(),
											item.getSellPrice(),
											item.getReorderPoint(),
											item.getReorderAmount(),
											((Perishable) item).getMaxTemp()
										   });
			} else {
				model.addRow(new Object[] {
											item.getName(),
											item.getQuantity(),
											item.getCost(),
											item.getSellPrice(),
											item.getReorderPoint(),
											item.getReorderAmount()
										   });
			}
		}
	}
	
	
	
	private void createGUI() {
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(1,2));
		//Panel related code
		pnlInv = createPanel(Color.WHITE);
		pnlFiles = createPanel(Color.LIGHT_GRAY);
		
		this.getContentPane().add(pnlInv);
		this.getContentPane().add(pnlFiles);
		
		
		// Inventory panel on the left
		lblInv = createLabel("Inventory", 35);
		btnInvLoad = createButton("Load Inventory");
		jfcInv = createFileChooser("CSV Files", "csv", "Select Inventory file");
		tblInv = createTable();
		tblScroll = createScrollPane(tblInv);
		
		// Panel for loading and exporting manifests
		// and sales logs on the right
		lblCapital = createLabel("Capital: $100000", 20);
		lblMani = createLabel("Manifests", 20);
		lblSalesLogs = createLabel("Sales Logs", 20);
		jfcMani = createFileChooser("CSV Files", "csv", "Select Manifest file");
		jfcSales = createFileChooser("CSV Files", "csv", "Select Sales Log file");
		btnManiLoad = createButton("Load");
		btnManiExp = createButton("Export");
		btnSalesLoad = createButton("Load");
		
		
		
		layoutInventoryPanel();
		
		layoutFilesPanel();
		
		repaint();
		this.setVisible(true);
		
	}
	
	
	
	private JPanel createPanel(Color c) {
		//Create a JPanel object and store it in a local var
		//set the background colour to that passed in c
		//Return the JPanel object
		JPanel jp = new JPanel();
		jp.setBackground(c);
		return jp;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		createGUI();
		int returnValue = jfcInv.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfcInv.getSelectedFile();
			filePathInv = selectedFile.getPath();
		}
		

	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		//Get event source
		Object src=evt.getSource();
		//Consider the alternatives - not all active at once.
		
		if (src==btnSalesLoad) {
			int returnValue = jfcSales.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = jfcSales.getSelectedFile();
				filePathSalesLog = selectedFile.getPath();
			}
			try {
				loadSalesLog.loadSales(store, filePathSalesLog);
				fillTable(store);
				updateCapitalLabel();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if (src==btnManiExp) {
			try {
				createManifest.Manifest(store);
				JOptionPane.showMessageDialog(null, "Manifest successfully exported");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if (src==btnManiLoad) {
			int returnValue = jfcMani.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = jfcMani.getSelectedFile();
				filePathManifest = selectedFile.getPath();
			}
			try {
				loadManifest.loadMani(store, filePathManifest);
				updateCapitalLabel();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fillTable(store);
			
		}
		
		
		if (src==btnInvLoad) {
			try {
				store = loadInventory.loadInvent("SuperSaver", filePathInv);
				updateCapitalLabel();
				tblScroll.setVisible(true);
				btnInvLoad.setEnabled(false);
				btnManiLoad.setEnabled(true);
				btnManiExp.setEnabled(true);
				btnSalesLoad.setEnabled(true);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			fillTable(store);
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.invokeLater(new StoreGUI("SuperSaver"));

	}

}
