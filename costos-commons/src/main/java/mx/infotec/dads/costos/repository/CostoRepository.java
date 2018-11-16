package mx.infotec.dads.costos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mx.infotec.dads.costos.Costo;

/**
 * 
 * @author Roberto Villarejo Martínez
 *
 */
public interface CostoRepository extends MongoRepository<Costo, String> {

}
