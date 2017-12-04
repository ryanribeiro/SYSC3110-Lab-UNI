import java.util.*;

public class AddressBook {
	private ArrayList<BuddyInfo> buddyInfo;
	private ArrayList<String> buddyInfoString;
	
	public AddressBook () {
		buddyInfo = new ArrayList<>();
		buddyInfoString = new ArrayList<>();
	}
	
	public void addBuddy(BuddyInfo newBuddy) {
		if (newBuddy != null) {
			buddyInfo.add(newBuddy);
			buddyInfoString.add(newBuddy.toString());
		}
	}
	
	public void removeBuddy(int index) {
		if (index >= 0 && index < buddyInfo.size()) {
			buddyInfo.remove(index);
			buddyInfoString.remove(index);
		}
	}
	
	public ArrayList<String> getString() {
		return buddyInfoString;
	}
	
	public static void main(String[] args) {
		BuddyInfo test = new BuddyInfo("Ryan","Kanata","123");		
		AddressBook testbook = new AddressBook();		
		testbook.addBuddy(test);
		testbook.removeBuddy(0);
	}
}
