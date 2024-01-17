package br.com.manu.persistence.repository.arvoreProduto;


import br.com.manu.persistence.entity.arvoreProduto.Grupo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GrupoRepository extends MongoRepository<Grupo, String> {
}
