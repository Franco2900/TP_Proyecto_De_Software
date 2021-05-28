package converters;

import entities.Documento;
import models.DocumentoModel;

public class DocumentoConverter {

	//De entidad a modelo
	public DocumentoModel entityToModel(Documento documento) {
		return new DocumentoModel(documento.getIdDocumento(), documento.getTitulo(), documento.getDescripcion(), 
            documento.getMateria(), documento.getProfesor(), documento.getCarrera(), documento.getDepartamento(), 
            documento.getUniversidad(), documento.getHash());
	}
	
	
	//De modelo a entidad
	public Documento modelToEntity(DocumentoModel documentoModel) {
		return new Documento(documentoModel.getId(), documentoModel.getTitulo(), documentoModel.getDescripcion(), 
        documentoModel.getMateria(), documentoModel.getProfesor(), documentoModel.getCarrera(), documentoModel.getDepartamento(), 
        documentoModel.getUniversidad(), documentoModel.getHash());
	}
	
}
