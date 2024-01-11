package br.com.manu.persistence.repository;

import br.com.manu.persistence.entity.Especificacao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EspecificacaoRespository extends MongoRepository<Especificacao, String> {
}
