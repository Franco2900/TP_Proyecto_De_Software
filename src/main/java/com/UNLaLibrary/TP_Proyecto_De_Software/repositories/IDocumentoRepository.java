package com.UNLaLibrary.TP_Proyecto_De_Software.repositories;

import com.UNLaLibrary.TP_Proyecto_De_Software.entities.Documento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDocumentoRepository extends JpaRepository<Documento, Long>{
    
}
