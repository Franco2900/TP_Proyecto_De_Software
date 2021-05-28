package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entities.Documento;

@Repository
public interface IDocumentoRepository extends JpaRepository<Documento, Long>{
    
}
