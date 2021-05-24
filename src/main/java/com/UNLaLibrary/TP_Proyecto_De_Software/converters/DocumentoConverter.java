package com.UNLaLibrary.TP_Proyecto_De_Software.converters;

import com.UNLaLibrary.TP_Proyecto_De_Software.entities.Documento;
import com.UNLaLibrary.TP_Proyecto_De_Software.models.DocumentoModel;

public class DocumentoConverter {

	//De entidad a modelo
	public DocumentoModel entityToModel(Documento documento) {
		return new DocumentoModel(documento.getIdDocumento(), documento.getTitulo(), documento.getDescripcion(), 
            documento.getMateria(), documento.getProfesor(), documento.getCarrera(), documento.getUniversidad(),
            documento.getHash());
	}
	
	
	//De modelo a entidad
	public Documento modelToEntity(DocumentoModel documentoModel) {
		return new Documento(documentoModel.getId(), documentoModel.getTitulo(), documentoModel.getDescripcion(), 
        documentoModel.getMateria(), documentoModel.getProfesor(), documentoModel.getCarrera(), documentoModel.getUniversidad(),
		documentoModel.getHash());
	}
	
}
