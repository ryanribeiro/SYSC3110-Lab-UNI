import java.util.*;
import javax.swing.*;

public class AddressBook {
	private ArrayList<BuddyInfo> buddyInfo;	
	
	public AddressBook () {
		buddyInfo = new ArrayList<>();
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
}
