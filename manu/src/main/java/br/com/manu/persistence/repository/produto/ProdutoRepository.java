package br.com.manu.persistence.repository.produto;

import br.com.manu.persistence.entity.produtos.produto.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdutoRepository extends MongoRepository<Produto, String> {

}
