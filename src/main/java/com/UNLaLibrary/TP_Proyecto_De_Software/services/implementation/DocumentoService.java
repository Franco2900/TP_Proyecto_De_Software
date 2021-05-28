package com.UNLaLibrary.TP_Proyecto_De_Software.services.implementation;

import com.UNLaLibrary.TP_Proyecto_De_Software.repositories.IDocumentoRepository;
import com.UNLaLibrary.TP_Proyecto_De_Software.services.IDocumentoService;
import com.UNLaLibrary.TP_Proyecto_De_Software.entities.Documento;
import com.UNLaLibrary.TP_Proyecto_De_Software.models.DocumentoModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.property.DocumentoStorageProperty;
import com.UNLaLibrary.TP_Proyecto_De_Software.converters.DocumentoConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentoService implements IDocumentoService{
    @Autowired
    private IDocumentoRepository documentoRepository;
    private final Path docStorageLocation;

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
        List<Documento> documentos = documentoRepository.findAll();
        DocumentoConverter documentoConverter = new DocumentoConverter();

        for(Documento d:documentos){
            documentoModels.add(documentoConverter.entityToModel(d));
        }
        return documentoModels;
    }
}
