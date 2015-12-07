package defectTracker.model;

import java.util.List;

public class User implements Comparable<User> {
	private int userId;
	private String firstName;
	private String lastName;
	private String email;

	public User(int userId, String firstName, String lastName, String email) {
		this.setUserId(userId);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
	}

	public int compareTo(User user) {

		return firstName.compareTo(user.getFirstName());
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName.trim();
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName.trim();
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {

		return this.firstName.trim() + " " + this.lastName.trim();
	}

	public String getEmail() {
		return email.trim();
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String toString() {

		return getFullName();
	}

	public int getUserIndex(List<User> userList) {

		for (int i = 0; i < userList.size(); i++) {

			if (userList.get(i).getUserId() == getUserId())
				return i;

		}

		return -1;
	}

}
