package mx.infotec.dads.kukulkan.costos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mx.infotec.dads.kukulkan.costos.Costo;

/**
 * 
 * @author Roberto Villarejo Martínez
 *
 */
public interface CostoRepository extends MongoRepository<Costo, String> {

}
