package mx.infotec.dads.costos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import mx.infotec.dads.costos.domain.DataFrame;

/**
 * 
 * @author Roberto Villarejo Martínez
 *
 */
public interface DataFrameRepository extends MongoRepository<DataFrame, String> {

    /**
     * 
     * @return el DataFrame más antiguo sin procesar
     */
    public Page<DataFrame> findByProcessedFalse(Pageable pageable);

}
