package entities;

import javax.persistence.*;

@Entity
@Table (name = "usuario")
public class Usuario {
	
	//Atributos
	@Id
	@GeneratedValue
	@Column (name = "id")
	private long id;
	
	@Column (name = "nombre")
	private String nombre;
	
	
	//Constructores
	public Usuario() {};
	
	public Usuario(long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	
	//Getters y Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	//toString
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
