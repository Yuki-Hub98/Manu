package br.com.manu.persistence.repository.arvoreProduto;

import br.com.manu.persistence.entity.arvoreProduto.Especificacao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EspecificacaoRespository extends MongoRepository<Especificacao, String> {
}
