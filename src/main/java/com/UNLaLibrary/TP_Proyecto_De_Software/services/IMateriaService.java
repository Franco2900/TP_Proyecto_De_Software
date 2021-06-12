package com.UNLaLibrary.TP_Proyecto_De_Software.services;

import java.util.List;

import com.UNLaLibrary.TP_Proyecto_De_Software.models.MateriaModel;

public interface IMateriaService {
    public MateriaModel traerMateria(long id);
    public List<MateriaModel> traerMaterias();
    public List<String> traerCarreras();
    public List<String> traerCarrerasPorDepartamento(String departamento);
    public List<MateriaModel> traerMateriasPorCarreraYDepartamento(String carrera, String departamento);
    public List<MateriaModel> traerMateriasPorUser(String username);
    public int contarMateriasPorCarrera(String carrera);
}
