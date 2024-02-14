package br.com.manu.persistence.repository.fornecedor;

import br.com.manu.persistence.entity.fornecedor.Fornecedor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FornecedorRepository extends MongoRepository<Fornecedor, String> {

}
