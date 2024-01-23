package br.com.manu.persistence.repository.arvoreProduto;

import br.com.manu.persistence.entity.arvoreProduto.Familia;
import br.com.manu.persistence.entity.arvoreProduto.Linha;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface FamiliaRepository extends MongoRepository<Familia, String> {
    @Query("{'linha': ?0 }, {'descricao': ?0}")
    List<Familia> findByname(String linha, String descricao);
    @Query("{'descricao': { $regex: /^?0/i }}")
    List<Familia> findRegex(String descricao);
}
