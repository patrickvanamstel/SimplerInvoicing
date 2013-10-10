package nl.kaninefatendreef.si.server.model;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=SimplerInvoicingApplicationUserJson.class)
public abstract class SimplerInvoicingApplicationUser {

	public abstract String getId(); 
	public abstract void setId(String id); 
	public abstract String getUsername();
	public abstract void setUsername(String username) ;
	public abstract String getPassword() ;
	public abstract void setPassword(String password) ;
	public abstract ArrayList<String> getRoles() ;
	public abstract void setRoles(ArrayList<String> roles) ;
	
	
	
}
