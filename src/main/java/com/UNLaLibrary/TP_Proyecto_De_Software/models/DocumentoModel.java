package com.UNLaLibrary.TP_Proyecto_De_Software.models;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.util.Date;

public class DocumentoModel {

	//Atributos
	private long id;
	private String titulo;
	private String descripcion;
	private String materia;
	private String profesor;
	private String carrera;
	private String universidad;
	private String hash;
	
	//Constructores
	public DocumentoModel() {}
	
	public DocumentoModel(long id, String titulo, String descripcion, String materia, 
			String profesor, String carrera, String universidad, String hash) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.materia = materia;
		this.profesor = profesor;
		this.carrera = carrera;
		this.universidad = universidad;
		this.hash = hash;
	}

	
	//Getters y Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}

	public String getProfesor() {
		return profesor;
	}
	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}

	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getUniversidad() {
		return universidad;
	}
	public void setUniversidad(String universidad) {
		this.universidad = universidad;
	}

	public String getHash() {
		return hash;
	}

	// Genera un codigo unico para nombrar cada documento, asi no se pueden pisar cuando los guardamos
	public void setHash() throws NoSuchAlgorithmException {
		String transformedName = new StringBuilder().append(this.titulo).append(this.profesor)
                .append(this.descripcion).append(this.universidad).append(this.carrera)
                .append(this.materia).append(new Date().getTime()).toString();
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(transformedName.getBytes(StandardCharsets.UTF_8));
		this.hash = new BigInteger(1, messageDigest.digest()).toString(16);
	}
	
	//toString
	@Override
	public String toString() {
		return "DocumentoModel [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", materia="
				+ materia + ", profesor=" + profesor + ", carrera=" + carrera + ", universidad=" + universidad + "]";
	}
		
}
