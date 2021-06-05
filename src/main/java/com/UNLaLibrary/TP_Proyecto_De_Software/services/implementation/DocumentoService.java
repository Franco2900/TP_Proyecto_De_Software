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

        DocumentoConverter documentoConverter = new DocumentoConverter();
        Documento documento = documentoConverter.modelToEntity(documentoModel);
        System.out.println(documento);
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
        DocumentoConverter documentoConverter = new DocumentoConverter();

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
    
    public List<String> traerMaterias(){
    	List <String> lista = new ArrayList<String>();

    	//Recorro la lista de documentos y añado a la listaCarreras un solo documento por carrera. Despues en la vista uso esos documentos para sacar el nombre de la carrera
    	for(DocumentoModel documento: traerDocumentos() ) { //Recorro la lista de documentos
    				
    		if(lista.isEmpty() ) lista.add(documento.getMateria()); //En la primera vuelta listaCarreras siempre va a estar vacio así que le añado una carrera
    		boolean bandera = false; 								   //bandera para saber si una carrera se repite o no
    				
    		for(String s: lista) { 				  //Recorro la listaCarreras para chequear si ya esta la carrera o no en la lista
    			if(documento.getMateria().equals(s) ){ //Si ya esta la carrera en listaCarrera dejo de chequear 
    				bandera = true;
    				break;
    			}
    		}
    				
    		if(bandera == false) lista.add(documento.getMateria());
    	}
    	return lista;
    }
    public List<String> traerCarreras(){
    	List <String> lista = new ArrayList<String>();
    	
    	//Recorro la lista de documentos y añado a la listaCarreras un solo documento por carrera. Despues en la vista uso esos documentos para sacar el nombre de la carrera
    	for(DocumentoModel documento: traerDocumentos() ) { //Recorro la lista de documentos
    				
    		if(lista.isEmpty() ) lista.add(documento.getCarrera()); //En la primera vuelta listaCarreras siempre va a estar vacio así que le añado una carrera
    		boolean bandera = false; 								   //bandera para saber si una carrera se repite o no
    				
    		for(String s: lista) { 				  //Recorro la listaCarreras para chequear si ya esta la carrera o no en la lista
    			if(documento.getCarrera().equals(s) ){ //Si ya esta la carrera en listaCarrera dejo de chequear 
    				bandera = true;
    				break;
    			}
    		}
    				
    		if(bandera == false) lista.add(documento.getCarrera());
    	}
    	return lista;
    }

    public DocumentoModel traerDocumento(long id) {
    	return documentoConverter.entityToModel(documentoRepository.findDocumento(id) );
    }
    
}
