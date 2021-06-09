package com.UNLaLibrary.TP_Proyecto_De_Software.entities;

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
    @Column(name = "hashArchivo")
	private String hash;

    public Documento() {
    }

    public Documento(long idDocumento, String titulo, String descripcion, Materia materia, String profesor, String hash) {
        this.idDocumento = idDocumento;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.materia = materia;
        this.profesor = profesor;
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

    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Documento idDocumento(long idDocumento) {
        setIdDocumento(idDocumento);
        return this;
    }

    public Documento titulo(String titulo) {
        setTitulo(titulo);
        return this;
    }

    public Documento descripcion(String descripcion) {
        setDescripcion(descripcion);
        return this;
    }

    public Documento materia(Materia materia) {
        setMateria(materia);
        return this;
    }

    public Documento profesor(String profesor) {
        setProfesor(profesor);
        return this;
    }

    public Documento hash(String hash) {
        setHash(hash);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " idDocumento='" + getIdDocumento() + "'" +
            ", titulo='" + getTitulo() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", materia='" + getMateria() + "'" +
            ", profesor='" + getProfesor() + "'" +
            ", hash='" + getHash() + "'" +
            "}";
    }    
}
