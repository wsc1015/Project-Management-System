/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserGUI;

/**
 *
 * @author Kevin
 */
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class UserListService {
	private EntityManager manager;

	public UserListService(EntityManager manager) {
		this.manager = manager;
	}

	public UserList createUser(int user_id, String password, String user_name,String user_type,
			String address, String email, String phone) {
		UserList user = new UserList();
		user.setUser_ID(user_id);
		user.setPassword(password);
		user.setUser_name(user_name);
		user.setUser_type(user_type);
		user.setAddress(address);
		user.setEmail(email);
		user.setPhone(phone);
		manager.persist(user);
		return user;
	}

	// method to read a record
	public UserList readUser(int user_id) {
		UserList user = manager.find(UserList.class, user_id);
		return user;
	}

	// method to read all records
	public List<UserList> readAll() {
		TypedQuery<UserList> query = manager.createQuery(
				"select e from userlist e", UserList.class);
		List<UserList> result = query.getResultList();
		return result;
	}

	// method to update a record
	public UserList updateUser(int user_id, String password,String user_name, String user_type,
			String address, String email, String phone) {
		UserList user = manager.find(UserList.class, user_id);
		if (user != null) {
			user.setPassword(password);
			user.setUser_name(user_name);
			user.setUser_type(user_type);
			user.setAddress(address);
			user.setEmail(email);
			user.setPhone(phone);
		}
		return user;
	}

	// method to delete a record
	public void deleteUser(int user_id) {
		UserList user = manager.find(UserList.class, user_id);
		if (user != null) {
			manager.remove(user);
		}
	}
}
