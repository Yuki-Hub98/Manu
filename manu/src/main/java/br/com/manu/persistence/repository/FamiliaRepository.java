package br.com.manu.persistence.repository;

import br.com.manu.persistence.entity.Familia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FamiliaRepository extends MongoRepository<Familia, String> {
}
