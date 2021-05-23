package com.UNLaLibrary.TP_Proyecto_De_Software.models;

public class DocumentoModel {

	//Atributos
	private long id;
	private String descripcion;
	private String materia;
	private String profesor;
	private String carrera;
	private String universidad;
	
	
	//Constructores
	public DocumentoModel() {}
	
	public DocumentoModel(long id, String descripcion, String materia, String profesor, String carrera,
			String universidad) {
		this.id = id;
		this.descripcion = descripcion;
		this.materia = materia;
		this.profesor = profesor;
		this.carrera = carrera;
		this.universidad = universidad;
	}

	
	//Getters y Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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

	
	//toString
	@Override
	public String toString() {
		return "DocumentoModel [id=" + id + ", descripcion=" + descripcion + ", materia=" + materia + ", profesor="
				+ profesor + ", carrera=" + carrera + ", universidad=" + universidad + "]";
	}
		
}
