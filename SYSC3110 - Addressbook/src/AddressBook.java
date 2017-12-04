import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
	
//	public StringBuffer exportAddressBook() {
//		StringBuffer addressBookStringBuffer = new StringBuffer();
//		for (BuddyInfo buddy : buddyInfo) {
//			addressBookStringBuffer.append(buddy.exportBuddyInfo() + "%");
//		}
//		return addressBookStringBuffer;
//	}
	
	public void save() {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("addressBook.txt"));
			for (BuddyInfo buddy : buddyInfo) {
				out.write(buddy.exportBuddyInfo());
				out.write(System.getProperty("line.separator"));
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public AddressBook open(String fileName) {
		File file = new File(fileName);
		AddressBook newAddressBook = new AddressBook();
		
		try {
			Scanner scanner = new Scanner(file);
			
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] buddyInfo = line.split(":");
				BuddyInfo buddy = new BuddyInfo(buddyInfo[0], buddyInfo[1], buddyInfo[2]);
				newAddressBook.addBuddy(buddy);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newAddressBook;
	}
	
	public static void main(String[] args) {
		BuddyInfo test = new BuddyInfo("Ryan","Kanata","123");
		BuddyInfo test2 = new BuddyInfo("Dylan","Kanata","123");
		BuddyInfo test3 = new BuddyInfo("Meagan","Kanata","123");
		BuddyInfo test4 = new BuddyInfo("Alex","Kanata","123");
		BuddyInfo test5 = new BuddyInfo("Beverley","Kanata","123");
		AddressBook testbook = new AddressBook();
		AddressBook testOpenBook = new AddressBook();
		testbook.addBuddy(test);
		testbook.addBuddy(test2);
		testbook.addBuddy(test3);
		testbook.addBuddy(test4);
		testbook.addBuddy(test5);
		//System.out.println(testbook.getString());
		testbook.save();
		testOpenBook = testbook.open("addressBook.txt");
		System.out.println(testOpenBook.getString());
	}
}
