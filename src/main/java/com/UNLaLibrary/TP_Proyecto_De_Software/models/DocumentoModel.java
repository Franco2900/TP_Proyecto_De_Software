package com.UNLaLibrary.TP_Proyecto_De_Software.models;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

import com.UNLaLibrary.TP_Proyecto_De_Software.entities.Review;

public class DocumentoModel {

	//Atributos
	private long id;
	private String titulo;
	private String descripcion;
	private MateriaModel materia;
	private String profesor;
	private Set<Review> reviews;
	private String hash;
	

	public DocumentoModel() {
	}

	public DocumentoModel(long id, String titulo, String descripcion, MateriaModel materia, String profesor, Set<Review> reviews, String hash) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.materia = materia;
		this.profesor = profesor;
		this.reviews = reviews;
		this.hash = hash;
	}

	public long getId() {
		return this.id;
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
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public MateriaModel getMateria() {
		return this.materia;
	}

	public void setMateria(MateriaModel materia) {
		this.materia = materia;
	}

	public String getProfesor() {
		return this.profesor;
	}

	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}

	public Set<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	public String getHash() {
		return this.hash;
	}

	// Genera un codigo unico para nombrar cada documento, asi no se pueden pisar cuando los guardamos
	public void setHash() throws NoSuchAlgorithmException {
		String transformedName = new StringBuilder().append(this.titulo).append(this.profesor)
                .append(this.descripcion).append(this.materia.getUniversidad()).append(this.materia.getDepartamento())
				.append(this.materia.getCarrera()).append(this.materia.getIdMateria()).append(new Date().getTime()).toString();
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(transformedName.getBytes(StandardCharsets.UTF_8));
		this.hash = new BigInteger(1, messageDigest.digest()).toString(16);
	}

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", titulo='" + getTitulo() + "'" +
			", descripcion='" + getDescripcion() + "'" +
			", materia='" + getMateria() + "'" +
			", profesor='" + getProfesor() + "'" +
			", reviews='" + getReviews() + "'" +
			", hash='" + getHash() + "'" +
			"}";
	}
}
