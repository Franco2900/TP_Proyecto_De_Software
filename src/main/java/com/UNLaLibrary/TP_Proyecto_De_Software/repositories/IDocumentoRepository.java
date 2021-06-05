package com.UNLaLibrary.TP_Proyecto_De_Software.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.UNLaLibrary.TP_Proyecto_De_Software.entities.Documento;

@Repository
public interface IDocumentoRepository extends JpaRepository<Documento, Long>{
    
	@Query("FROM Documento order by materia asc ")
	public abstract List<Documento> findTodo();
	
	@Query("FROM Documento WHERE departamento=(:departamento) ")
    public abstract List<Documento> findDepartamento(String departamento);
	
	@Query("FROM Documento WHERE carrera=(:carrera)")
    public abstract List<Documento> findCarrera(String carrera);
	
	@Query("FROM Documento WHERE departamento=(:departamento) and carrera=(:carrera)")
    public abstract List<Documento> findDepartamentoXCarrera(String departamento, String carrera);
	
	@Query("FROM Documento WHERE departamento=(:departamento) and carrera=(:carrera) and materia=(:materia)")
    public abstract List<Documento> findDepartamentoXCarreraXMateria(String departamento, String carrera, String materia);
	
	@Query("FROM Documento WHERE materia=(:materia) ")
    public abstract List<Documento> findMateria(String materia);

	@Query("FROM Documento WHERE id=(:id) ")
	public abstract Documento findDocumento(long id);
	
}
