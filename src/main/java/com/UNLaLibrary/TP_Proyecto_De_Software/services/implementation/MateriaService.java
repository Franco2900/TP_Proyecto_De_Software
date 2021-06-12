package com.UNLaLibrary.TP_Proyecto_De_Software.services.implementation;

import java.util.List;
import java.util.ArrayList;

import com.UNLaLibrary.TP_Proyecto_De_Software.converters.MateriaConverter;
import com.UNLaLibrary.TP_Proyecto_De_Software.entities.Materia;
import com.UNLaLibrary.TP_Proyecto_De_Software.models.MateriaModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.repositories.IMateriaRepository;
import com.UNLaLibrary.TP_Proyecto_De_Software.services.IMateriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MateriaService implements IMateriaService{
    @Autowired
    private IMateriaRepository materiaRepository;
    @Autowired
    private MateriaConverter materiaConverter;
    
    public MateriaModel traerMateria(long id){
        Materia materia = materiaRepository.findMateria(id);
        MateriaModel materiaModel = materiaConverter.entityToModel(materia);

        return materiaModel;
    }

    public List<MateriaModel> traerMaterias(){
    	List<MateriaModel> materias = new ArrayList<MateriaModel>();

    	for(Materia m: materiaRepository.findAll()){
            materias.add(materiaConverter.entityToModel(m));
        }
    	return materias;
    }

    public List<String> traerCarreras(){
    	List <String> carreras = new ArrayList<String>();
    	
    	for(Materia m: materiaRepository.findCarreras()){
            carreras.add(m.getCarrera());
        }
        return carreras;
    }

    public List<String> traerCarrerasPorDepartamento(String departamento){
        List<String> carreras = new ArrayList<String>();

        for(Materia m: materiaRepository.findCarrerasByDepartamento(departamento)){
            carreras.add(m.getCarrera());
        }
        return carreras;
    }

    public List<MateriaModel> traerMateriasPorCarreraYDepartamento(String carrera, String departamento){
        List<MateriaModel> materias = new ArrayList<MateriaModel>();

        for(Materia m: materiaRepository.findMateriasByCarreraAndDepartamento(carrera, departamento)){
            materias.add(materiaConverter.entityToModel(m));
        }
        return materias;
    }

    public List<MateriaModel> traerMateriasPorUser(String username){
        List<MateriaModel> materias = new ArrayList<MateriaModel>();

        for(Materia m: materiaRepository.findMateriasByUser(username)){
            materias.add(materiaConverter.entityToModel(m));
        }
        return materias;
    }

    public int contarMateriasPorCarrera(String carrera){
        int materias = materiaRepository.countMateriasByCarrera(carrera);
        return materias;
    }
}
