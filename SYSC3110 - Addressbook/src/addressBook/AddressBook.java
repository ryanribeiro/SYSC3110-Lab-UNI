package addressBook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AddressBook implements Serializable{
	private ArrayList<BuddyInfo> buddyInfo;
	private ArrayList<String> buddyInfoString;

	public AddressBook() {
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

	public BuddyInfo getBuddy(int index) {
		return buddyInfo.get(index);
	}

	public int size() {
		return buddyInfo.size();
	}

	public boolean equals(AddressBook book) {
		int i;
		if (this.size() != book.size()) {
			return false;
		} else {
			for (i = 0; i < this.size(); i++) {
				if (this.getBuddy(i).equals(book.getBuddy(i)) == false) {
					return false;
				}
			}
			return true;
		}
	}

	public void save(String fileName) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
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
	
	public void serialSave(String fileName) {
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(buddyInfo);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public AddressBook serialOpen(String fileName) {
		AddressBook createdAddressBook = new AddressBook();
		ArrayList<BuddyInfo> openedBuddyInfo = new ArrayList<>();
		
		try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			openedBuddyInfo = (ArrayList<BuddyInfo>) in.readObject();
			in.close();
			fileIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (BuddyInfo buddy : openedBuddyInfo) {
			createdAddressBook.addBuddy(buddy);
		}
		return createdAddressBook;
	}

	public static void main(String[] args) {

	}
}
