package UserGUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kevin
 */

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "userlist")
public class UserList implements Serializable {
	@Column(name = "User_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	
	private int User_ID;
	@Column
	private String Password;
	@Column
	private String User_name;
	
	@Column
	private String User_type;
	@Column
	private String Address;
	@Column
	private String Email;
	@Column
	private String Phone;

	public int getUser_ID() {
		return User_ID;
	}

	public void setUser_ID(int user_ID) {
		User_ID = user_ID;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getUser_name() {
		return User_name;
	}

	public void setUser_name(String user_name) {
		User_name = user_name;
	}

	public String getUser_type() {
		return User_type;
	}

	public void setUser_type(String user_type) {
		User_type = user_type;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	// return number of columns in the table
	public int getNumberOfColumns() {
		return 7;
	}

	// return the data in column i
	public String getColumnData(int i) throws Exception {
		if (i == 0)
			return Integer.toString(getUser_ID());
		else if (i == 1)
			return getPassword();
		else if (i == 2)
			return getUser_name();
		else if (i == 3)
			return getUser_type();
		else if (i == 4)
			return getAddress();
		else if (i == 5)
			return getEmail();
		else if (i == 6)
			return getPhone();
		else
			throw new Exception(
					"Error: invalid column index in courselist table");
	}

	// return the name of column i
	public String getColumnName(int i) throws Exception {
		String colName = null;
		if (i == 0)
			colName = "User ID";
		else if (i == 1)
			colName = "Password";
		else if (i == 2)
			colName = "User Name";
		else if (i == 3)
			colName = "User Type";
		else if (i == 4)
			colName = "Address";
		else if (i == 5)
			colName = "Email";
		else if (i == 6)
			colName = "Phone";
		else
			throw new Exception(
					"Access to invalid column number in userlist table");
		return colName;
	}

	// set data column i to value
	public void setColumnData(int i, Object value) throws Exception {
		if (i == 0)
			User_ID = (int) value;
		else if (i == 1)
			Password = (String) value;
		else if (i == 2)
			User_name = (String) value;
		else if (i == 3)
			User_type = (String) value;
		else if (i == 4)
			Address = (String) value;
		else if (i == 5)
			Email = (String) value;
		else if (i == 6)
			Phone = (String) value;
		else
			throw new Exception(
					"Error: invalid column index in userlist table");
	}

	@Override
	public String toString() {
		return "UserList [User_ID=" + User_ID + ", Password=" + Password
				+ ", User_name=" + User_name + ", User_type=" + User_type
				+ ", Address=" + Address + ", Email=" + Email + ", Phone="
				+ Phone + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Address == null) ? 0 : Address.hashCode());
		result = prime * result + ((Email == null) ? 0 : Email.hashCode());
		result = prime * result
				+ ((Password == null) ? 0 : Password.hashCode());
		result = prime * result + ((Phone == null) ? 0 : Phone.hashCode());
		result = prime * result + User_ID;
		result = prime * result
				+ ((User_name == null) ? 0 : User_name.hashCode());
		result = prime * result
				+ ((User_type == null) ? 0 : User_type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserList other = (UserList) obj;
		if (Address == null) {
			if (other.Address != null)
				return false;
		} else if (!Address.equals(other.Address))
			return false;
		if (Email == null) {
			if (other.Email != null)
				return false;
		} else if (!Email.equals(other.Email))
			return false;
		if (Password == null) {
			if (other.Password != null)
				return false;
		} else if (!Password.equals(other.Password))
			return false;
		if (Phone == null) {
			if (other.Phone != null)
				return false;
		} else if (!Phone.equals(other.Phone))
			return false;
		if (User_ID != other.User_ID)
			return false;
		if (User_name == null) {
			if (other.User_name != null)
				return false;
		} else if (!User_name.equals(other.User_name))
			return false;
		if (User_type == null) {
			if (other.User_type != null)
				return false;
		} else if (!User_type.equals(other.User_type))
			return false;
		return true;
	}


}
