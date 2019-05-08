package dao;

import java.util.List;

import entities.User;
import thrillio.DataStore;

public class UserDao {
	public List<User> getUsers() {
		return DataStore.getUsers();
	}
}
