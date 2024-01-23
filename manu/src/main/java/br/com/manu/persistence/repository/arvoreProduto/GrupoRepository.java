package br.com.manu.persistence.repository.arvoreProduto;


import br.com.manu.persistence.entity.arvoreProduto.Familia;
import br.com.manu.persistence.entity.arvoreProduto.Grupo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface GrupoRepository extends MongoRepository<Grupo, String> {
    @Query("{'familia': ?0 }, {'descricao': ?0}")
    List<Grupo> findByname(String familia, String descricao);

    @Query("{'descricao': { $regex: /^?0/i }}")
    List<Grupo> findRegex(String descricao);
}
