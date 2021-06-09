package com.UNLaLibrary.TP_Proyecto_De_Software.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.UNLaLibrary.TP_Proyecto_De_Software.entities.Documento;

@Repository
public interface IDocumentoRepository extends JpaRepository<Documento, Long>{
    
	@Query("SELECT d FROM Documento d INNER JOIN Materia m ON d.materia = m.idMateria ORDER BY m.nombre asc ")
	public abstract List<Documento> findTodo();
	
	@Query("SELECT d FROM Documento d INNER JOIN d.materia m WHERE m.departamento=(:departamento) ")
    public abstract List<Documento> findDepartamento(@Param("departamento") String departamento);
	
	@Query("SELECT d FROM Documento d INNER JOIN d.materia m WHERE m.carrera=(:carrera)")
    public abstract List<Documento> findCarrera(@Param("carrera") String carrera);
	
	@Query("SELECT d FROM Documento d INNER JOIN d.materia m WHERE m.departamento=(:departamento) and m.carrera=(:carrera)")
    public abstract List<Documento> findDepartamentoXCarrera(@Param("departamento") String departamento, @Param("carrera") String carrera);
	
	@Query("SELECT d FROM Documento d INNER JOIN d.materia m WHERE m.departamento=(:departamento) and m.carrera=(:carrera) and m.nombre=(:materia)")
    public abstract List<Documento> findDepartamentoXCarreraXMateria(@Param("departamento") String departamento, @Param("carrera") String carrera, @Param("materia") String materia);
	
	@Query("SELECT d FROM Documento d INNER JOIN d.materia m WHERE m.nombre=(:materia) ")
    public abstract List<Documento> findMateria(@Param("materia") String materia);

	@Query("FROM Documento WHERE id=(:id) ")
	public abstract Documento findDocumento(@Param("id") long id);
	
}
