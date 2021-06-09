package com.UNLaLibrary.TP_Proyecto_De_Software.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")//, uniqueConstraints=@UniqueConstraint(columnNames= {"user", "userRole_id"})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="email", nullable=false, length=90)
	private String email;
	
	@Column(name="username", unique=true, nullable=false, length=45)
	private String username;
	
	@Column(name="password", nullable=false, length=60)
	private String password;
	
	@Column(name="enabled", nullable=false)
	private boolean enabled;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="userRole", nullable=false)
	private UserRole userRole;

	// Si es un alumno tiene todas las materias de su carrera, si es profesor
	// puede tener mas de una y en distintas universidades/carreras
	@ManyToMany(mappedBy = "users")
	private Set<Materia> materias;
		
	public User() {}


	public User(int id, String email, String username, String password, boolean enabled, UserRole userRole, Set<Materia> materias) {
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

	public UserRole getUserRole() {
		return this.userRole;
	}

	public void setUserRole(UserRole userRole) {
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
