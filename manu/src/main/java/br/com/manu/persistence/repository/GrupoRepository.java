package br.com.manu.persistence.repository;


import br.com.manu.persistence.entity.Grupo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GrupoRepository extends MongoRepository<Grupo, String> {
}
