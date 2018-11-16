package mx.infotec.dads.costos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mx.infotec.dads.costos.domain.Authority;

/**
 * Spring Data MongoDB repository for the Authority entity.
 */
public interface AuthorityRepository extends MongoRepository<Authority, String> {
}
