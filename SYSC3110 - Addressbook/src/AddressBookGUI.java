import java.util.*;
import javax.swing.*;

public class AddressBookGUI {
	private ArrayList<AddressBook> addressBooks;
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu addressBookMenu, buddyInfoMenu;
	private JMenuItem create, save, display, add;
	
	
	public AddressBookGUI () {
		addressBooks = new ArrayList<>();
		initGUI();
	}
	
	public void addAddressBook(AddressBook newAddressBook) {
		if (newAddressBook != null)
			addressBooks.add(newAddressBook);
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
	
	public static void main(String[] args) {
		AddressBookGUI addressBookGUI = new AddressBookGUI();
		AddressBook addressBook = new AddressBook();
		addressBook.addBuddy(new BuddyInfo("Ryan", "Kanata", "613-322-2555"));
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
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 400);
		frame.setLocationRelativeTo(null);		
		
		frame.setJMenuBar(menuBar);
		menuBar.add(addressBookMenu);
		menuBar.add(buddyInfoMenu);
		addressBookMenu.add(create);
		addressBookMenu.add(save);
		addressBookMenu.add(display);
		buddyInfoMenu.add(add);
		
		if (addressBooks.size() == 0) {
			buddyInfoMenu.setVisible(false);
			menuBar.updateUI();
		}
			
		frame.setVisible(true);
	}
}
