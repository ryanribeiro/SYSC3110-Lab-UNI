package addressBook;

import java.io.Serializable;

public class BuddyInfo implements Serializable{

	String name;
	String address;
	String phoneNumber;

	public BuddyInfo(String name, String address, String phoneNumber) {
		this.setName(name);
		this.setAddress(address);
		this.setPhoneNumber(phoneNumber);
	}

	public static void main(String[] args) {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String toString() {
		return ("Name: " + name + " Address: " + address + " Phone number: " + phoneNumber + "\n");
	}

	public boolean equals(BuddyInfo buddy) {
		if (buddy.getAddress().equals(address)) {
			if (buddy.getName().equals(name)) {
				if (buddy.getPhoneNumber().equals(phoneNumber)) {
					return true;
				}
			}
		}
		return false;
	}

	public String exportBuddyInfo() {
		String buddyInfoString;
		buddyInfoString = (name + ":" + address + ":" + phoneNumber);
		return buddyInfoString;
	}
}