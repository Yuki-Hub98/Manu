package br.com.manu.persistence.repository.arvoreProduto;

import br.com.manu.persistence.entity.arvoreProduto.Cor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CorRepository extends MongoRepository<Cor, String> {
}
