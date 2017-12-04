package addressBook;

import java.io.*;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

@SuppressWarnings("serial")
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
			e.printStackTrace();
		}
		return newAddressBook;
	}
	
	public void serialSave(String fileName) {
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public AddressBook serialOpen(String fileName) {
		AddressBook createdAddressBook = new AddressBook();
		
		try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			createdAddressBook = (AddressBook) in.readObject();
			in.close();
			fileIn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return createdAddressBook;
	}
	
	public String toXML() {
		String xmlText = "<?xml version = \"1.0\"?>";
		xmlText = xmlText + "<AddressBook>";
		for (BuddyInfo buddy : buddyInfo) {
			xmlText = xmlText + buddy.toXML();
		}
		xmlText = xmlText + "</AddressBook>";
		return xmlText;
	}
	
	public void exportToXmlFile(String fileName) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
			out.write(toXML());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public AddressBook importFromXmlFile(String fileName) {
		File file = new File(fileName);
		AddressBook newAddressBook = new AddressBook();
	
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser s = spf.newSAXParser();
			
			DefaultHandler dh = new DefaultHandler() {
				boolean boolName = false;
				boolean boolAddress = false;
				boolean boolPhoneNumber = false;
				
				String name;
				String address;
				String phoneNumber;
				BuddyInfo newBuddy;
				
				public void startElement(String u, String ln, String qName, Attributes a) {
					System.out.println("START: " + qName);
					if (qName.equalsIgnoreCase("Name")) {
						boolName = true;
					}
					if (qName.equalsIgnoreCase("Address")) {
						boolAddress = true;
					}
					if (qName.equalsIgnoreCase("PhoneNumber")) {
						boolPhoneNumber = true;
					}
				}
				
				public void endElement(String uri, String localName, String qName) {
					System.out.println("END: " + qName);
				}
				
				public void characters(char[] ch, int start, int length) {
					if (boolName) {
						System.out.println("Name: " + new String(ch, start, length));
						boolName = false;
						name = new String(ch, start, length);
					}
					if (boolAddress) {
						System.out.println("Address: " + new String(ch, start, length));
						boolAddress = false;
						address = new String(ch, start, length);
					}
					if (boolPhoneNumber) {
						System.out.println("PhoneNumber: " + new String(ch, start, length));
						boolPhoneNumber = false;
						phoneNumber = new String(ch, start, length);
						newBuddy = new BuddyInfo(name, address, phoneNumber);
						newAddressBook.addBuddy(newBuddy);
					}
				}
			};
			s.parse(file,  dh);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newAddressBook;
	}
	
	public static void main(String[] args) {
		AddressBook testingBook = new AddressBook();
		BuddyInfo test1 = new BuddyInfo("Ryan", "Kanata", "123");
		BuddyInfo test2 = new BuddyInfo("John", "Kanata", "123");
		BuddyInfo test3 = new BuddyInfo("Smith", "Kanata", "123");
		BuddyInfo test4 = new BuddyInfo("Jane", "Kanata", "123");
		BuddyInfo test5 = new BuddyInfo("Doe", "Kanata", "123");
		testingBook.addBuddy(test1);
		testingBook.addBuddy(test2);
		testingBook.addBuddy(test3);
		testingBook.addBuddy(test4);
		testingBook.addBuddy(test5);
		
		System.out.println(testingBook.toXML());
		testingBook.exportToXmlFile("testXML.xml");
		testingBook.importFromXmlFile("testXML.xml");
	}
}
