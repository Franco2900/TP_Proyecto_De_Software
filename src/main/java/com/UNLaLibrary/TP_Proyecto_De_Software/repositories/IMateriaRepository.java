package com.UNLaLibrary.TP_Proyecto_De_Software.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.UNLaLibrary.TP_Proyecto_De_Software.entities.Materia;

@Repository
public interface IMateriaRepository extends JpaRepository<Materia, Long> {
    @Query("SELECT m FROM Materia m GROUP BY m.carrera")
    public abstract List<Materia> findCarreras();

    @Query("SELECT m FROM Materia m GROUP BY m.departamento")
    public abstract List<Materia> findDepartamentos();

    @Query("SELECT m FROM Materia m WHERE m.departamento = (:departamento) GROUP BY m.carrera")
    public abstract List<Materia> findCarrerasByDepartamento(@Param("departamento") String departamento);

    @Query("SELECT m FROM Materia m WHERE m.carrera = (:carrera) AND m.departamento = (:departamento)")
    public abstract List<Materia> findMateriasByCarreraAndDepartamento(@Param("carrera") String carrera, @Param("departamento") String departamento);

    @Query("SELECT m FROM Materia m INNER JOIN m.users u WHERE u.username = (:username)")
    public abstract List<Materia> findMateriasByUser(@Param("username") String username);

    @Query("FROM Materia WHERE id=(:id) ")
    public abstract Materia findMateria(@Param("id") long id);
}
