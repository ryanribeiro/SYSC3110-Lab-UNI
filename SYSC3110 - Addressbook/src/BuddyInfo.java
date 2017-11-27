
public class BuddyInfo {

	String name;
	String address;
	String phoneNumber;
	
	BuddyInfo (String name, String address, String phoneNumber) {
		this.setName(name);
		this.setAddress(address);
		this.setPhoneNumber(phoneNumber);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BuddyInfo Homer = new BuddyInfo("Homer", "Springfield", "613-555-5555");
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

}
