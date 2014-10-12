package web.data;

/**
 * Description: The purpose of this class is to be linked with the login page.
 * 				It has a one to one relationship with the login page form fields.
 */
public class User_reg {
	
	private String email;
	private String password;

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email= email;
	}

	public String toString() {
		return "User_reg [email=" + email + ", password=" + password + "]";
	}

}