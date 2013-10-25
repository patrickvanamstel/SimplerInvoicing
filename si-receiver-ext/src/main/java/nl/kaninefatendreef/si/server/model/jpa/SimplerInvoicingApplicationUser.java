package nl.kaninefatendreef.si.server.model.jpa;

import java.util.ArrayList;

import javax.persistence.Basic;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "SIMPLER_INVOICE_APPLICATION_USER")
public class SimplerInvoicingApplicationUser extends nl.kaninefatendreef.si.server.model.SimplerInvoicingApplicationUser{
	
	@Id
	@GeneratedValue	(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;

	@Basic
	public ArrayList<String> roles;

	public String getId() {
		if (id != null){
			return "" + id;
		}
		return null;
	}
	

	public void setId(String id) {
		if (id != null){
			this.id = new Long(id);
		}
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
