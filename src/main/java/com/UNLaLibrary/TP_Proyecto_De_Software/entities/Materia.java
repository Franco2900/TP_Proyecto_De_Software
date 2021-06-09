package com.UNLaLibrary.TP_Proyecto_De_Software.entities;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMateria;
    private String nombre;
    private String carrera;
    private String departamento;
    private String universidad;
    @OneToMany(mappedBy = "materia")
    private Set<Documento> documentos;
    @ManyToMany
    @JoinTable(name = "materiaPorUser", 
                joinColumns = @JoinColumn(name = "idMateria"), 
                inverseJoinColumns = @JoinColumn(name = "id"))
    private Set<User> users;

    public Materia() {
    }

    public Materia(long idMateria, String nombre, String carrera, String departamento, String universidad, Set<Documento> documentos, Set<User> users) {
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.carrera = carrera;
        this.departamento = departamento;
        this.universidad = universidad;
        this.documentos = documentos;
        this.users = users;
    }

    public long getIdMateria() {
        return this.idMateria;
    }

    public void setIdMateria(long idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrera() {
        return this.carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getUniversidad() {
        return this.universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public Set<Documento> getDocumentos() {
        return this.documentos;
    }

    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "{" +
            " idMateria='" + getIdMateria() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", carrera='" + getCarrera() + "'" +
            ", departamento='" + getDepartamento() + "'" +
            ", universidad='" + getUniversidad() + "'" +
            ", documentos='" + getDocumentos() + "'" +
            ", users='" + getUsers() + "'" +
            "}";
    }
}
