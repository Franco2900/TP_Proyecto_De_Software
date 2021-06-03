package com.UNLaLibrary.TP_Proyecto_De_Software.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.UNLaLibrary.TP_Proyecto_De_Software.entities.Documento;

@Repository
public interface IDocumentoRepository extends JpaRepository<Documento, Long>{
    
	@Query("FROM Documento WHERE departamento=(:departamento)")
    public abstract List<Documento> findDepartamento(String departamento);
	
	@Query("FROM Documento WHERE departamento=(:carrera)")
    public abstract List<Documento> findCarrera(String carrera);
	
	@Query("FROM Documento WHERE departamento=(:materia)")
    public abstract List<Documento> findMateria(String materia);
	
}
