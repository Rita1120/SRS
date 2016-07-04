package model;

public class User extends Person {
	private String password;
	
@Override
public String toString() {
	return "User [password=" + password + "]";
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
}
