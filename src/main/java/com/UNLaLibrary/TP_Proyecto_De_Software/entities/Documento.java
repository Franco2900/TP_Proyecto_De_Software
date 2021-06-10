package com.UNLaLibrary.TP_Proyecto_De_Software.entities;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table (name = "Documento")
public class Documento {
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idDocumento")
    private long idDocumento;
    private String titulo;
	private String descripcion;
    @ManyToOne
    @JoinColumn(name = "materia", referencedColumnName = "idMateria", nullable = false)
	private Materia materia;
	private String profesor;
    @OneToMany(mappedBy = "documento")
    private Set<Review> reviews;
    @Column(name = "hashArchivo")
	private String hash;

    public Documento() {
    }

    public Documento(long idDocumento, String titulo, String descripcion, Materia materia, String profesor, Set<Review> reviews, String hash) {
        this.idDocumento = idDocumento;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.materia = materia;
        this.profesor = profesor;
        this.reviews = reviews;
        this.hash = hash;
    }

    public long getIdDocumento() {
        return this.idDocumento;
    }

    public void setIdDocumento(long idDocumento) {
        this.idDocumento = idDocumento;
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

    public Materia getMateria() {
        return this.materia;
    }

    public void setMateria(Materia materia) {
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

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "{" +
            " idDocumento='" + getIdDocumento() + "'" +
            ", titulo='" + getTitulo() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", materia='" + getMateria() + "'" +
            ", profesor='" + getProfesor() + "'" +
            ", reviews='" + getReviews() + "'" +
            ", hash='" + getHash() + "'" +
            "}";
    }
}
