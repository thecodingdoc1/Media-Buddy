package managers;

import java.sql.SQLException;
import java.util.List;

import constants.Gender;
import constants.UserType;
import dao.UserDao;
import entities.User;
import util.StringUtil;

public class UserManager { // typically class name ends with manager or service, can be different depending
							// on team
	private static UserManager instance = new UserManager();
	private static UserDao dao = new UserDao();

	private UserManager() {
	} // ensures that can not create an instance of it

	public static UserManager getInstance() {
		return instance; // way to create a singleton, other ways is in Headfirst Design Patterns book
	}

	public User createUser(long id, String email, String password, String firstName, String lastName, Gender gender, UserType userType) {
		User user = new User();
		user.setId(id);
		user.setEmail(email);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setGender(gender);
		user.setUserType(userType);
		
		return user;
		
	}
	
	
	public User getUser(long userId) {
		return dao.getUser(userId);
	}

	public long authenticate(String email, String password) {
		return dao.authenticate(email, StringUtil.encodePassword(password));
		
	}

	public static User createUser(String firstName, String lastName, String email, String password) {
		return dao.createUser(firstName, lastName, email, StringUtil.encodePassword(password));
		
	}
}
