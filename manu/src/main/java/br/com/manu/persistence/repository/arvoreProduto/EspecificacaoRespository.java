package br.com.manu.persistence.repository.arvoreProduto;

import br.com.manu.persistence.entity.arvoreProduto.Cor;
import br.com.manu.persistence.entity.arvoreProduto.Especificacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EspecificacaoRespository extends MongoRepository<Especificacao, String> {
    @Query("{'descricao': ?0 }")
    List<Especificacao> findByname(String descricao);
}
