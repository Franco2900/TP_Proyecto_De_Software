package com.UNLaLibrary.TP_Proyecto_De_Software.models;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.UNLaLibrary.TP_Proyecto_De_Software.entities.Materia;

public class UserModel {
	private int id;
	@NotEmpty(message = "El campo Email no puede estar vacio")
	@Email(message = "El email ingreso no es valido")
	private String email;
	@NotEmpty(message = "El campo Username no puede estar vacio")
	private String username;
	@NotEmpty(message = "El campo Password no puede estar vacio")
	@Size(min = 8, message = "El password debe tener almenos 8 caracteres")
	private String password;
	private boolean enabled;
	private UserRoleModel userRole;
	private Set<Materia> materias;
	
	public UserModel() {
		super();
	}

	public UserModel(int id, String email, String username, String password, boolean enabled, UserRoleModel userRole, Set<Materia> materias) {
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
		this.materias = materias;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public UserRoleModel getUserRole() {
		return this.userRole;
	}

	public void setUserRole(UserRoleModel userRole) {
		this.userRole = userRole;
	}

	public Set<Materia> getMaterias() {
		return this.materias;
	}

	public void setMaterias(Set<Materia> materias) {
		this.materias = materias;
	}

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", email='" + getEmail() + "'" +
			", username='" + getUsername() + "'" +
			", password='" + getPassword() + "'" +
			", enabled='" + isEnabled() + "'" +
			", userRole='" + getUserRole() + "'" +
			", materias='" + getMaterias() + "'" +
			"}";
	}
}
