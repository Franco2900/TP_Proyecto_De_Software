package com.UNLaLibrary.TP_Proyecto_De_Software.services.implementation;

import com.UNLaLibrary.TP_Proyecto_De_Software.services.IDocumentoService;
import com.UNLaLibrary.TP_Proyecto_De_Software.models.DocumentoModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.property.DocumentoStorageProperty;
import com.UNLaLibrary.TP_Proyecto_De_Software.repositories.IDocumentoRepository;
import com.UNLaLibrary.TP_Proyecto_De_Software.entities.Documento;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.UNLaLibrary.TP_Proyecto_De_Software.converters.DocumentoConverter;

@Service
public class DocumentoService implements IDocumentoService{
    @Autowired
    private IDocumentoRepository documentoRepository;
    private final Path docStorageLocation;
    @Autowired
    private DocumentoConverter documentoConverter;

    @Autowired
    public DocumentoService(DocumentoStorageProperty documentoStorageProperty) throws IOException {
        this.docStorageLocation = Paths.get(documentoStorageProperty.getUploadDirectory()).toAbsolutePath().normalize();
        Files.createDirectories(this.docStorageLocation);
    }

    // Agrega el documento completo a la base de datos
    public void agregarDocumento(DocumentoModel documentoModel, MultipartFile archivoPDF) throws NoSuchAlgorithmException, IOException{
        documentoModel.setHash();
        guardarDocumento(archivoPDF, documentoModel.getHash());

        Documento documento = documentoConverter.modelToEntity(documentoModel);
        documentoRepository.save(documento);
    }

    // Guarda el pdf con el nombre del hash generado
    public void guardarDocumento(MultipartFile archivoPDF, String hash) throws IOException{
        Path direccionDestino = this.docStorageLocation.resolve(hash + ".pdf");
        Files.copy(archivoPDF.getInputStream(), direccionDestino);
    }

    // Devuelve todos los documentos sin filtrar
    public List<DocumentoModel> traerDocumentos(){
        List<DocumentoModel> documentoModels = new ArrayList<DocumentoModel>();
        List<Documento> documentos = documentoRepository.findTodo();

        for(Documento d:documentos){
            documentoModels.add(documentoConverter.entityToModel(d));
        }
        return documentoModels;
    }

    // Busco el archivo con el hash y devuelvo el stream
    public InputStream descargarDocumento(long id) throws IOException{
        Optional<Documento> documento = documentoRepository.findById(id);
        File archivoPDF = this.docStorageLocation.resolve(documento.get().getHash() + ".pdf").toFile();
        InputStream is = new FileInputStream(archivoPDF);

        return is;
    }
    
    
    public List<DocumentoModel> traerDocumentosPorDepartamento(String dep){
    	List<DocumentoModel> listaDocumentosFiltradosPorDepartamento = new ArrayList<DocumentoModel>();
    	
	    for(Documento documento: documentoRepository.findDepartamento(dep)) {
				listaDocumentosFiltradosPorDepartamento.add(documentoConverter.entityToModel(documento) );
		}
	    
	    return listaDocumentosFiltradosPorDepartamento;
    }

    public List<DocumentoModel> traerDocumentosPorCarrera(String carrera){
    	List<DocumentoModel> listaDocumentosXCarrera = new ArrayList<DocumentoModel>();

    	for(Documento documento: documentoRepository.findCarrera(carrera)) {
    		listaDocumentosXCarrera.add(documentoConverter.entityToModel(documento) );
    	}

    	return listaDocumentosXCarrera;
    };

    public List<DocumentoModel> traerDocumentosPorMateria(String materia){
    	List<DocumentoModel> listaDocumentosXMaterias = new ArrayList<DocumentoModel>();

    	for(Documento documento: documentoRepository.findMateria(materia)) {
    		listaDocumentosXMaterias.add(documentoConverter.entityToModel(documento) );
    	}

    	return listaDocumentosXMaterias;
    };

    public List<DocumentoModel> traerDepartamentoXCarrera(String departamento, String carrera){
    	List<DocumentoModel> listaDepartamentoXCarrera = new ArrayList<DocumentoModel>();
    	
    	for(Documento documento: documentoRepository.findDepartamentoXCarrera(departamento, carrera) ) {
    		listaDepartamentoXCarrera.add(documentoConverter.entityToModel(documento) );
    	}
    	
    	return listaDepartamentoXCarrera;
    }
    
    public List<DocumentoModel> traerDepartamentoXCarreraXMateria(String departamento, String carrera, String materia){
    	List<DocumentoModel> listaDepartamentoXCarrera = new ArrayList<DocumentoModel>();
    	
    	for(Documento documento: documentoRepository.findDepartamentoXCarreraXMateria(departamento, carrera, materia) ) {
    		listaDepartamentoXCarrera.add(documentoConverter.entityToModel(documento) );
    	}
    	
    	return listaDepartamentoXCarrera;
    }
    
    public DocumentoModel traerDocumento(long id) {
    	return documentoConverter.entityToModel(documentoRepository.findDocumento(id) );
    }

    public void eliminarDocumento(long id){
        Documento documento = documentoRepository.findDocumento(id);
        File archivoPDF = this.docStorageLocation.resolve(documento.getHash() + ".pdf").toFile();
        
        if(archivoPDF.delete()){
            documentoRepository.delete(documento);
        }
    }
}
