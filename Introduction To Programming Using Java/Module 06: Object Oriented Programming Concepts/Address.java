//package assignment6;

public class Address {
	private String street;
	private String city;
	private String state;
	private String zip;

	public String toString() {  
		return street + " " + city + " " + state + " " + zip;  
    } 
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String inputState) {
		this.state = inputState;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String inputZip) {
		this.zip = inputZip;
	}
}
