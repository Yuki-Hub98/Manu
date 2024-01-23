package br.com.manu.persistence.repository.arvoreProduto;

import br.com.manu.persistence.entity.arvoreProduto.Departamento;
import br.com.manu.persistence.entity.arvoreProduto.Linha;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinhaRepository extends MongoRepository<Linha, String> {
    @Query("{'departamento': ?0 }, {'descricao': ?0}")
    List<Linha> findByname(String departamento, String descricao);
    @Query("{'descricao': { $regex: /^?0/i }}")
    List<Linha> findRegex(String descricao);
}
