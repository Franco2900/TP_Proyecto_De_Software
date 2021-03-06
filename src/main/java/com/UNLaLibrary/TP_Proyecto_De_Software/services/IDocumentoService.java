package com.UNLaLibrary.TP_Proyecto_De_Software.services;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.UNLaLibrary.TP_Proyecto_De_Software.models.DocumentoModel;

public interface IDocumentoService {
    public void agregarDocumento(DocumentoModel documentoModel, MultipartFile archivoPDF) throws NoSuchAlgorithmException, IOException;
    public List<DocumentoModel> traerDocumentos();
    public InputStream descargarDocumento(long id) throws IOException;
    public List<DocumentoModel> traerDocumentosPorDepartamento(String dep);
    public List<DocumentoModel> traerDocumentosPorCarrera(String carrera);
    public List<DocumentoModel> traerDocumentosPorMateria(String materia);
    public List<DocumentoModel> traerDepartamentoXCarrera(String departamento, String carrera);
    public List<DocumentoModel> traerDepartamentoXCarreraXMateria(String departamento, String carrera, String materia);
    public DocumentoModel traerDocumento(long id);
    public void eliminarDocumento(long id);
}
