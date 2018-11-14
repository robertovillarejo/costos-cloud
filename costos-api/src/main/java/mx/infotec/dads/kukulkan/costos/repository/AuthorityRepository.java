package mx.infotec.dads.kukulkan.costos.repository;

import mx.infotec.dads.kukulkan.costos.domain.Authority;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Authority entity.
 */
public interface AuthorityRepository extends MongoRepository<Authority, String> {
}
