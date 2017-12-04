import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class AddressBookGUI implements ActionListener {
	private ArrayList<AddressBook> addressBooks;
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu addressBookMenu, buddyInfoMenu;
	private JMenuItem create, save, display, add;
	private JTextArea jTextArea;
	
	
	public AddressBookGUI () {
		addressBooks = new ArrayList<>();
		initGUI();
	}
	
	public void addAddressBook(AddressBook newAddressBook) {
		if (newAddressBook != null)
			addressBooks.add(newAddressBook);
		for (String temp : newAddressBook.getString()) {
			jTextArea.append(temp);
		}
		jTextArea.updateUI();
		if (addressBooks.size() == 1) {
			buddyInfoMenu.setVisible(true);
			menuBar.updateUI();
		}
	}
	
	public void removeAddressBook(int index) {
		if (index >= 0 && index < addressBooks.size())
			addressBooks.remove(index);
		if (addressBooks.size() == 0) {
			buddyInfoMenu.setVisible(false);
			menuBar.updateUI();
		}
	}
	
	public int size() {
		return addressBooks.size();
	}
	
	public void display() {
		jTextArea.setVisible(true);
	}
	
	public static void main(String[] args) {
		AddressBookGUI addressBookGUI = new AddressBookGUI();
		AddressBook addressBook = new AddressBook();
		addressBook.addBuddy(new BuddyInfo("Ryan", "Kanata", "613-322-2555"));
		addressBook.addBuddy(new BuddyInfo("Dylan", "Kanata", "613-555-2555"));
		addressBookGUI.addAddressBook(addressBook);
	}
	
	private void initGUI() {
		frame = new JFrame();
		menuBar = new JMenuBar();
		addressBookMenu = new JMenu("Address Book");
		buddyInfoMenu = new JMenu("BuddyInfo");
		create = new JMenuItem("Create");
		save = new JMenuItem("Save");
		display = new JMenuItem("Display");
		add = new JMenuItem("Add");
		jTextArea = new JTextArea();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);		
		
		frame.setJMenuBar(menuBar);
		menuBar.add(addressBookMenu);
		menuBar.add(buddyInfoMenu);
		
		create.addActionListener(this);
		save.addActionListener(this);
		display.addActionListener(this);
		add.addActionListener(this);
		
		addressBookMenu.add(create);
		addressBookMenu.add(save);
		addressBookMenu.add(display);
		buddyInfoMenu.add(add);
		
		jTextArea.setEditable(false);
		jTextArea.setLineWrap(true);
		
		if (addressBooks.size() == 0) {
			buddyInfoMenu.setVisible(false);
			menuBar.updateUI();
		} else {
			for (AddressBook aBook : addressBooks ) {
				jTextArea.append(aBook.toString());
			}
		}
		
		frame.getContentPane().add(jTextArea, BorderLayout.CENTER);
		
		jTextArea.setVisible(false);
		frame.setVisible(true);
	}
	
	public void add() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		switch (actionCommand) {
			case "Create": 
				System.out.println("Create");
				break;
			case "Save": 
				System.out.println("Save");
				break;
			case "Display": 
				display();
				break;
			case "Add": 
				System.out.println("Add");
				break;
			
		}
			
	}
}
