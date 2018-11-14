package mx.infotec.dads.kukulkan.costos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import mx.infotec.dads.kukulkan.costos.ExcelFile;

/**
 * 
 * @author Roberto Villarejo Martínez
 *
 */
public interface ExcelFileRepository extends MongoRepository<ExcelFile, String> {

    /**
     * 
     * @return el ExcelFile más antiguo sin procesar
     */
    public Page<ExcelFile> findByProcessedFalse(Pageable pageable);

}
