package br.com.manu.persistence.repository.arvoreProduto;

import br.com.manu.persistence.entity.arvoreProduto.Departamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DepartamentoRepository extends MongoRepository<Departamento, String> {
    @Query("{'descricao': ?0 }")
    List<Departamento> findByname(String valor);

}
