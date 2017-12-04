package tests;

import static org.junit.Assert.*;

import org.junit.After;
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

	@After
	public void testSaveOpen() {
		AddressBook openedBook = new AddressBook();
		testingBook.save("testBook");
		openedBook = openedBook.open("testBook");

		assertEquals("", true, openedBook.equals(testingBook));
	}
}
