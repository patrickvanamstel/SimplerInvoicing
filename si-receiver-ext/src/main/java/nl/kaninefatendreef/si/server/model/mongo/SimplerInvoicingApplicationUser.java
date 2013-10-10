package nl.kaninefatendreef.si.server.model.mongo;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "siapplicationuser")
public class SimplerInvoicingApplicationUser extends nl.kaninefatendreef.si.server.model.SimplerInvoicingApplicationUser{
	
	@Id
	private String id;
	
	@Indexed(unique=true)
	private String username;
	
	private String password;

	public ArrayList<String> roles;

	public String getId() {
		return  id;
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
