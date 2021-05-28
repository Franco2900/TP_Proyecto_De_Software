package services.implementation;

import services.IDocumentoService;
import models.DocumentoModel;
import property.DocumentoStorageProperty;
import repositories.IDocumentoRepository;
import entities.Documento;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import converters.DocumentoConverter;

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

    public void agregarDocumento(DocumentoModel documentoModel, MultipartFile archivoPDF) throws NoSuchAlgorithmException, IOException{
        documentoModel.setHash();
        guardarDocumento(archivoPDF, documentoModel.getHash());

        DocumentoConverter documentoConverter = new DocumentoConverter();
        Documento documento = documentoConverter.modelToEntity(documentoModel);
        System.out.println(documento);
        documentoRepository.save(documento);
    }

    public void guardarDocumento(MultipartFile archivoPDF, String hash) throws IOException{
        Path direccionDestino = this.docStorageLocation.resolve(hash + ".pdf");
        Files.copy(archivoPDF.getInputStream(), direccionDestino);
    }
}
