package br.com.manu.persistence.repository.arvoreProduto;

import br.com.manu.persistence.entity.arvoreProduto.Cor;
import br.com.manu.persistence.entity.arvoreProduto.Departamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CorRepository extends MongoRepository<Cor, String> {
    @Query("{'descricao': ?0 }")
    List<Cor> findByname(String descricao);
    @Query("{'descricao':{$regex:/^?0/i}}")
    List<Cor> findRegex(String descricao);
}
