package com.UNLaLibrary.TP_Proyecto_De_Software.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.UNLaLibrary.TP_Proyecto_De_Software.entities.Documento;
import com.UNLaLibrary.TP_Proyecto_De_Software.models.DocumentoModel;

@Component
public class DocumentoConverter {
	@Autowired
	private MateriaConverter materiaConverter;

	//De entidad a modelo
	public DocumentoModel entityToModel(Documento documento) {
		return new DocumentoModel(documento.getIdDocumento(), documento.getTitulo(), documento.getDescripcion(), 
            materiaConverter.entityToModel(documento.getMateria()), documento.getProfesor(), documento.getReviews(), documento.getHash());
	}
	
	
	//De modelo a entidad
	public Documento modelToEntity(DocumentoModel documentoModel) {
		return new Documento(documentoModel.getId(), documentoModel.getTitulo(), documentoModel.getDescripcion(), 
        materiaConverter.modelToEntity(documentoModel.getMateria()), documentoModel.getProfesor(), documentoModel.getReviews(), documentoModel.getHash());
	}
}
