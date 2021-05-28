package com.UNLaLibrary.TP_Proyecto_De_Software.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

// Obtiene la direccion donde se guardan los documentos
@ConfigurationProperties(prefix = "documentos")
public class DocumentoStorageProperty {
    private String uploadDirectory;

    public String getUploadDirectory() {
        return this.uploadDirectory;
    }

    public void setUploadDirectory(String uploadDirectory) {
        this.uploadDirectory = uploadDirectory;
    }    
}
