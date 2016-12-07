
package musicx.model;

import java.sql.Timestamp;

public class Users {
	
	private String username;
	private String password;
	private int location_id;
	private String firstname, lastname;
	private Gender gender;
	public enum Gender{
		M, F
	};
	private Timestamp birthdate;
	private int phone_number;
	
	public Users(String username, String password, int location_id, String firstname, String lastname, Gender gender,
			Timestamp birthdate, int phone_number) {
		super();
		this.username = username;
		this.password = password;
		this.location_id = location_id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.birthdate = birthdate;
		this.phone_number = phone_number;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getLocation_id() {
		return location_id;
	}
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Timestamp getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Timestamp birthdate) {
		this.birthdate = birthdate;
	}
	public int getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(int phone_number) {
		this.phone_number = phone_number;
	}
	
	
	

}
