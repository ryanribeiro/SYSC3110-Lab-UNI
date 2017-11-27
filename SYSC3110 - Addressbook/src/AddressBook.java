import java.util.*;
import javax.swing.*;

public class AddressBook extends JFrame{
	private ArrayList<BuddyInfo> buddyInfo;
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu addressBookMenu, buddyInfoMenu;
	private JMenuItem create, save, display, add;
	
	
	public AddressBook () {
		buddyInfo = new ArrayList<BuddyInfo>();
		initGUI();
	}
	
	public void addBuddy(BuddyInfo newBuddy) {
		if (newBuddy != null)
			buddyInfo.add(newBuddy);
	}
	
	public void removeBuddy(int index) {
		if (index >= 0 && index < buddyInfo.size())
			buddyInfo.remove(index);
	}
	
	public static void main(String[] args) {
		BuddyInfo test = new BuddyInfo("Ryan","Kanata","123");		
		AddressBook testbook = new AddressBook();		
		testbook.addBuddy(test);
		testbook.removeBuddy(0);
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
		
		frame.setVisible(true);
	}
}
