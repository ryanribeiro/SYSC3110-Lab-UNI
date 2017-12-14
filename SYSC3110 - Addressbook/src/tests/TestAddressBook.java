package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.Before;

import addressBook.AddressBook;
import addressBook.BuddyInfo;

public class TestAddressBook {
	private AddressBook testingBook = new AddressBook();

	@Before
	public void setup() {
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
	}

	@Test
	public void testSaveOpen() {
		String fileName = "testBook.txt";
		AddressBook openedBook = new AddressBook();
		testingBook.save(fileName);
		openedBook = testingBook.open(fileName);

		assertEquals("", true, openedBook.equals(testingBook));
		try {
			Files.deleteIfExists(Paths.get(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSerializedSaveOpen() {
		String fileName = "serialbook.ser";
		AddressBook openedBook = new AddressBook();
		testingBook.serialSave(fileName);
		openedBook = testingBook.serialOpen(fileName);
		
		assertEquals("", true, openedBook.equals(testingBook));
		try {
			Files.deleteIfExists(Paths.get(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testXMLSaveOpen() {
		String fileName = "xmlbook.xml";
		AddressBook openedBook = new AddressBook();
		testingBook.exportToXmlFile(fileName);
		openedBook = testingBook.importFromXmlFile(fileName);
		
		assertEquals("", true, openedBook.equals(testingBook));
		try {
			Files.deleteIfExists(Paths.get(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
