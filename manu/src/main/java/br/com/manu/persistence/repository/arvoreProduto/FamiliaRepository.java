package br.com.manu.persistence.repository.arvoreProduto;

import br.com.manu.persistence.entity.arvoreProduto.Familia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FamiliaRepository extends MongoRepository<Familia, String> {
}
