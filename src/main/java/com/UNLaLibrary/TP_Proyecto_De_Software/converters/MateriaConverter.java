package com.UNLaLibrary.TP_Proyecto_De_Software.converters;

import com.UNLaLibrary.TP_Proyecto_De_Software.entities.Materia;
import com.UNLaLibrary.TP_Proyecto_De_Software.models.MateriaModel;

import org.springframework.stereotype.Component;

@Component
public class MateriaConverter {
    public MateriaModel entityToModel(Materia materia){
        return new MateriaModel(materia.getIdMateria(), materia.getNombre(), materia.getCarrera(), 
            materia.getDepartamento(), materia.getUniversidad(), materia.getDocumentos(), materia.getUsers());
    }

    public Materia modelToEntity(MateriaModel materiaModel){
        return new Materia(materiaModel.getIdMateria(), materiaModel.getNombre(), materiaModel.getCarrera(), 
            materiaModel.getDepartamento(), materiaModel.getUniversidad(), materiaModel.getDocumentos(), materiaModel.getUsers());
    }
}
