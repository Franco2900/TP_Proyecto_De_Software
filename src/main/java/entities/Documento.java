package entities;

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
	private String materia;
	private String profesor;
	private String carrera;
	private String departamento;
	private String universidad;
    @Column(name = "hashArchivo")
	private String hash;

    public Documento() {}

    public Documento(long idDocumento, String titulo, String descripcion, String materia, String profesor, String carrera, String departamento, String universidad, String hash) {
        this.idDocumento = idDocumento;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.materia = materia;
        this.profesor = profesor;
        this.carrera = carrera;
        this.departamento = departamento;
        this.universidad = universidad;
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

    public String getMateria() {
        return this.materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getProfesor() {
        return this.profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getCarrera() {
        return this.carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    
    public String getDepartamento() {
    	return departamento;
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

    public String getHash() {
		return hash;
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
            ", carrera='" + getCarrera() + "'" +
            ", departamento='" + getDepartamento() + "'" +
            ", universidad='" + getUniversidad() + "'" +
            ", hash='" + getHash() + "'" +
            "}";
    }
}
