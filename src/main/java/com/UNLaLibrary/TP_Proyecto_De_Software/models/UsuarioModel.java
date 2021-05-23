package com.UNLaLibrary.TP_Proyecto_De_Software.models;

public class UsuarioModel {

	//Atributos
	private long id;
	private String nombre;
	
	
	//Constructores
	public UsuarioModel() {};
	
	public UsuarioModel(long id, String nombre) {
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
		return "UsuarioModel [id=" + id + ", nombre=" + nombre + "]";
	}
	
}