package br.com.manu.persistence.repository;

import br.com.manu.persistence.entity.Cor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CorRepository extends MongoRepository<Cor, String> {
}
