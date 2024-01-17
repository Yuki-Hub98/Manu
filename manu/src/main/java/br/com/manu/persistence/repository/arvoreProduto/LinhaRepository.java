package br.com.manu.persistence.repository.arvoreProduto;

import br.com.manu.persistence.entity.arvoreProduto.Linha;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinhaRepository extends MongoRepository<Linha, String> {
}
