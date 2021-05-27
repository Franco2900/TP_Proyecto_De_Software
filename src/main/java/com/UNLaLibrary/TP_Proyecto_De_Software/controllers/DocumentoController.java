package com.UNLaLibrary.TP_Proyecto_De_Software.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.UNLaLibrary.TP_Proyecto_De_Software.models.DocumentoModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.services.implementation.DocumentoService;

@Controller
public class DocumentoController {
    @Autowired
    private DocumentoService documentoService;

    // Formulario basico de un documento
    @GetMapping("/agregarDocumento")
    public String formularioDocumento(){
        return "agregarDocumento";
    }

    // Obtengo la informacion del formulario y agrego el documento, por ahora no tiene mensaje de exito
    @PostMapping("/agregarDocumento")
    public String agregarDocumento(@RequestParam(value = "archivoPDF") MultipartFile archivoPDF, 
        @RequestParam("titulo") String titulo, @RequestParam("descripcion") String descripcion) throws NoSuchAlgorithmException, IOException{
        DocumentoModel documentoModel = new DocumentoModel(0L, titulo, descripcion, "Programacion Concurrente", 
        "Hernan Merlino", "Licenciatura en sistemas", "Desarrollo Productivo y Tecnológico", "UNLA", "");
        documentoService.agregarDocumento(documentoModel, archivoPDF);

        return "agregarDocumento";
    }
}
