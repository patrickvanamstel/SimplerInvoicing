package nl.kaninefatendreef.si.server.model;

import java.util.ArrayList;



public class SimplerInvoicingApplicationUserJson {

	private String id;
	private String username;
	private String password;
	public ArrayList<String> roles;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public ArrayList<String> getRoles() {
		return roles;
	}

	public void setRoles(ArrayList<String> roles) {
		this.roles = roles;
	} 
	
	
	
}
