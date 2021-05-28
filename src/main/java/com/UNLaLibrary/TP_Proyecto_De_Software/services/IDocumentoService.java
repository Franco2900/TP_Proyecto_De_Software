package com.UNLaLibrary.TP_Proyecto_De_Software.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.UNLaLibrary.TP_Proyecto_De_Software.models.DocumentoModel;

import org.springframework.web.multipart.MultipartFile;

public interface IDocumentoService {
    public void agregarDocumento(DocumentoModel documentoModel, MultipartFile archivoPDF) throws NoSuchAlgorithmException, IOException;
    public List<DocumentoModel> traerDocumentos();
}
